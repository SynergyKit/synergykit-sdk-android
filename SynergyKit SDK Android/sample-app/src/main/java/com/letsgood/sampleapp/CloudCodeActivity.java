/*
 * Copyright [2015] [Letsgood.com s.r.o.]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.Copyright [2015] [Letsgood.com s.r.o.]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Created by Bc. Pavel Stambrecht for Letsgood.com s.r.o.
 */

package com.letsgood.sampleapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.SpannableString;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.letsgood.sampleapp.addons.PicturePicker;
import com.letsgood.sampleapp.model.CloudCodeDemo;
import com.letsgood.sampleapp.widgets.CustomProgressDialog;
import com.letsgood.synergykitsdkandroid.Synergykit;
import com.letsgood.synergykitsdkandroid.builders.UriBuilder;
import com.letsgood.synergykitsdkandroid.builders.uri.Resource;
import com.letsgood.synergykitsdkandroid.listeners.FileResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.ResponseListener;
import com.letsgood.synergykitsdkandroid.config.SynergykitConfig;
import com.letsgood.synergykitsdkandroid.resources.SynergykitError;
import com.letsgood.synergykitsdkandroid.resources.SynergykitFile;
import com.letsgood.synergykitsdkandroid.resources.SynergykitObject;




/**
 * Created by Marek on 1/14/15.
 */
public class CloudCodeActivity extends ActionBarActivity implements View.OnClickListener{

    /* Attributes */
    private TextView resultTextView;
    private Button selectButton;
    private EditText inputEditText;
    private ImageView faceImageView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloud_code);

        //set action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        //find views
        selectButton = (Button) findViewById(R.id.buttonSelect);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        faceImageView = (ImageView) findViewById(R.id.faceImageView);
        inputEditText = (EditText) findViewById(R.id.inputEditText);

        //set listeners
        selectButton.setOnClickListener(this);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // send action id to manager
        int id = item.getItemId();

        switch (id){
            case android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void selectImage(){

        //check signed in
        if(Synergykit.getLoggedUser()==null){
            Toast.makeText(getApplicationContext(),"Your're not signed in. Sign in first.",Toast.LENGTH_SHORT).show();
            return;
        }

        //check values
        if(inputEditText.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),"No name was written. Write it first.",Toast.LENGTH_SHORT).show();
            return;
        }

        pickLogoFromGallery();
    }

    private void pickLogoFromGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 6563);
    }

    private void upload(Bitmap b){

        if(b==null)
            return;


        //show progress dialog
        final CustomProgressDialog pd = new CustomProgressDialog(this, "Uploading...");

        //create file
        Synergykit.createFile(b, new FileResponseListener() {

            @Override
            public void errorCallback(int statusCode, SynergykitError errorObject) {
                Toast.makeText(CloudCodeActivity.this, errorObject.toString(), Toast.LENGTH_SHORT).show();
                pd.dismiss();
            }

            @Override
            public void doneCallback(int statusCode, SynergykitFile object) {

                CloudCodeDemo ccd = new CloudCodeDemo("face-recognition");
                ccd.setPath(object.getPath());
                ccd.setName(inputEditText.getText().toString());


                Synergykit.invokeCloudCode(ccd,CloudCodeDemo.class,new ResponseListener() {
                    @Override
                    public void doneCallback(int statusCode, SynergykitObject object) {
                        pd.dismiss();
                        CloudCodeDemo ccd = (CloudCodeDemo) object;
                        SpannableString spannableString = new SpannableString(ccd.getName()+ "\n"
                                                                              + "gender: " + ccd.getGender() + " (" + ccd.getGenderConfidence() + " %)\n"
                                                                              + "age: " + ccd.getAge() + " +/-" + ccd.getAgeRange() + "\n"
                                                                              + "glass: " + ccd.getGlass() + "\n"
                                                                              + "race: " + ccd.getRace() + " (" + ccd.getRaceConfidence() + " %)"+"\n"
                                                                              + "smiling:" + ccd.getSmiling() + " %");

                        resultTextView.setText(spannableString);
                        resultTextView.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void errorCallback(int statusCode, SynergykitError errorObject) {
                        pd.dismiss();
                        Toast.makeText(CloudCodeActivity.this, errorObject.toString(), Toast.LENGTH_SHORT).show();

                    }
                },false);



            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 6563 && data != null && data.getData() != null) {

            Bitmap faceBitmap;
            faceBitmap = PicturePicker.getInstance().getBitmap(data.getData(), CloudCodeActivity.this);

            if(faceBitmap!=null && faceBitmap.getWidth()>800){
                faceBitmap = resizedBitmap(faceBitmap,800);
            }

            resultTextView.setVisibility(View.INVISIBLE);
            faceImageView.setImageDrawable(new BitmapDrawable(getResources(), faceBitmap));
            upload(faceBitmap);
        }
    }

    public Bitmap resizedBitmap(Bitmap bm, int newWidth) {
        int width = bm.getWidth();
        int height = bm.getHeight();

        float scale = ((float) newWidth) / width;

        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scale, scale);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
        return resizedBitmap;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonSelect:
                selectImage();
                break;
        }
    }
}
