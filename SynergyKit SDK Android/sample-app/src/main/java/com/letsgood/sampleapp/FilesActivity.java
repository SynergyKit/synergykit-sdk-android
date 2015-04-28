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


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.letsgood.sampleapp.model.DemoUser;
import com.letsgood.sampleapp.widgets.CustomProgressDialog;
import com.letsgood.synergykitsdkandroid.Synergykit;
import com.letsgood.synergykitsdkandroid.builders.UriBuilder;
import com.letsgood.synergykitsdkandroid.builders.uri.Resource;
import com.letsgood.synergykitsdkandroid.config.SynergykitConfig;
import com.letsgood.synergykitsdkandroid.listeners.BitmapResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.DeleteResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.FileResponseListener;
import com.letsgood.synergykitsdkandroid.resources.SynergykitError;
import com.letsgood.synergykitsdkandroid.resources.SynergykitFile;
import com.letsgood.synergykitsdkandroid.resources.SynergykitUri;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Marek on 1/13/15.
 */
public class FilesActivity extends ActionBarActivity implements View.OnClickListener {

    /* Attributes */
    private Button uploadFileFromBundleButton;
    private Button getLastOneButton;
    private Button destroyLastOneButton;
    private LinearLayout outputLinearLayout;


    /* On create */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_files);

        //set action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        //find views
        uploadFileFromBundleButton = (Button)findViewById(R.id.buttonUploadFromBundle);
        getLastOneButton = (Button)findViewById(R.id.buttonGetLastOne);
        destroyLastOneButton = (Button)findViewById(R.id.buttonDestroyLastOne);
        outputLinearLayout = (LinearLayout) findViewById(R.id.linearLayoutOutput);

        //set listeners
        uploadFileFromBundleButton.setOnClickListener(this);
        getLastOneButton.setOnClickListener(this);
        destroyLastOneButton.setOnClickListener(this);
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



    /* On click */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonUploadFromBundle:
                uploadFileFromBundle();
                break;

            case R.id.buttonGetLastOne:
                getLastOne();
                break;

            case R.id.buttonDestroyLastOne:
                destroyLastOne();
                break;
        }
    }





    private void printOutput(String message){
        TextView textView = new TextView(getApplicationContext());
        textView.setTextColor(getResources().getColor(R.color.black));

        if(message==null)
            return;

        textView.setText(message);
        outputLinearLayout.addView(textView);

    }




    private void uploadFileFromBundle(){

        //check signed in
        if(Synergykit.getLoggedUser()==null){
            Toast.makeText(getApplicationContext(), "Your're not signed in. Sign in first.", Toast.LENGTH_SHORT).show();
            return;
        }


        Bitmap logoBitmap = generateBitmap(); //generate bitmap

        setEnabled(false);

        //clear output
        outputLinearLayout.removeAllViews();

        //show progress dialog
        final CustomProgressDialog progressDialog =  new CustomProgressDialog(this,"Uploading ...");

        //create file
        Synergykit.createFile(logoBitmap, new FileResponseListener() {
            @Override
            public void doneCallback(int statusCode, SynergykitFile file) {
                printOutput(file.getPath());

                if (!file.getPath().endsWith(".jpg")) {
                    setEnabled(true);
                    progressDialog.dismiss();
                    return;
                }

                //download bitmap
                Synergykit.downloadBitmap(file.getPath(), new BitmapResponseListener() {
                    @Override
                    public void doneCallback(int statusCode, Bitmap bitmap) {

                        ImageView imageView = new ImageView(getApplicationContext());
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                        imageView.setImageBitmap(bitmap);
                        imageView.setLayoutParams(layoutParams);
                        outputLinearLayout.addView(imageView);

                        setEnabled(true);
                        progressDialog.dismiss();
                    }

                    @Override
                    public void errorCallback(int statusCode, SynergykitError errorObject) {
                        printOutput(errorObject.toString());
                        setEnabled(true);
                        progressDialog.dismiss();
                    }
                });
            }

            @Override
            public void errorCallback(int statusCode, SynergykitError errorObject) {
                printOutput(errorObject.toString());
                setEnabled(true);
                progressDialog.dismiss();
            }
        });
    }

    private void getLastOne(){


        //check signed in
        if(Synergykit.getLoggedUser()==null){
            Toast.makeText(getApplicationContext(), "Your're not signed in. Sign in first.", Toast.LENGTH_SHORT).show();
            return;
        }

        setEnabled(false);
        outputLinearLayout.removeAllViews(); //clear output


        //build uri
        SynergykitUri uri = UriBuilder.newInstance()
                                        .setResource(Resource.RESOURCE_FILES)
                                        .setOrderByDesc("createdAt")
                                        .setTop(1)
                                        .build();

        //create config
        SynergykitConfig config = SynergykitConfig.newInstance()
                                                    .setUri(uri)
                                                    .setType(SynergykitFile.class);

        //show progress dialog
        final CustomProgressDialog progressDialog =  new CustomProgressDialog(this,"Fetching ...");

        //get file
        Synergykit.getFile(config, new FileResponseListener() {
            @Override
            public void doneCallback(int statusCode, SynergykitFile file) {

                printOutput(file.getPath());


                if (!file.getPath().endsWith(".jpg")) {
                    setEnabled(true);
                    progressDialog.dismiss();
                    return;
                }

                //download bitmap
                Synergykit.downloadBitmap(file.getPath(), new BitmapResponseListener() {
                    @Override
                    public void doneCallback(int statusCode, Bitmap bitmap) {
                        ImageView imageView = new ImageView(getApplicationContext());
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                        imageView.setImageBitmap(bitmap);
                        imageView.setLayoutParams(layoutParams);
                        outputLinearLayout.addView(imageView);

                        setEnabled(true);
                        progressDialog.dismiss();
                    }

                    @Override
                    public void errorCallback(int statusCode, SynergykitError errorObject) {
                        printOutput(errorObject.toString());
                        setEnabled(true);
                        progressDialog.dismiss();
                    }
                });
            }

            @Override
            public void errorCallback(int statusCode, SynergykitError errorObject) {
                printOutput(errorObject.toString());
                setEnabled(true);
                progressDialog.dismiss();
            }
        });
    }

    private void destroyLastOne(){

        //check signed in
        if(Synergykit.getLoggedUser()==null){
            Toast.makeText(getApplicationContext(), "Your're not signed in. Sign in first.", Toast.LENGTH_SHORT).show();
            return;
        }


        setEnabled(false);

        //clear output
        outputLinearLayout.removeAllViews();

        //build uri
        SynergykitUri uri = UriBuilder.newInstance()
                .setResource(Resource.RESOURCE_FILES)
                .setOrderByDesc("createdAt")
                .setTop(1)
                .build();

        //create config
        SynergykitConfig config = SynergykitConfig.newInstance()
                .setUri(uri)
                .setType(SynergykitFile.class);

        //show progress dialog
        final CustomProgressDialog progressDialog =  new CustomProgressDialog(this,"Destroying ...");

        //get file
        Synergykit.getFile(config, new FileResponseListener() {
            @Override
            public void doneCallback(int statusCode, SynergykitFile file) {

                //delete file
                Synergykit.deleteFile(file.getId(), new DeleteResponseListener() {
                    @Override
                    public void doneCallback(int statusCode) {
                        printOutput("Last file destroyed.");
                        setEnabled(true);
                        progressDialog.dismiss();
                    }

                    @Override
                    public void errorCallback(int statusCode, SynergykitError errorObject) {

                        setEnabled(true);
                        progressDialog.dismiss();
                    }
                }, true);


            }

            @Override
            public void errorCallback(int statusCode, SynergykitError errorObject) {
                printOutput(errorObject.toString());
                setEnabled(true);
                progressDialog.dismiss();
            }
        });
    }


    private void setEnabled(boolean enabled){
        getLastOneButton.setEnabled(enabled);
        destroyLastOneButton.setEnabled(enabled);
        uploadFileFromBundleButton.setEnabled(enabled);

    }


    private Bitmap generateBitmap(){
        Bitmap logoBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        Bitmap outputBitmap = Bitmap.createBitmap(400, logoBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Paint textPaint = new Paint();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateTime = "[" + simpleDateFormat.format(Calendar.getInstance().getTime()) + "]"; // reading local time in the system

        Canvas canvas = new Canvas(outputBitmap);
        canvas.drawBitmap(logoBitmap,0,0,new Paint());

        textPaint.setTextSize(24);
        textPaint.setColor(Color.BLACK);
        textPaint.setStyle(Paint.Style.FILL);
        float textHeight = textPaint.measureText("yY");

        if(Synergykit.getLoggedUser()!=null && ((DemoUser) Synergykit.getLoggedUser()).getName()!=null)
            dateTime += " " + ((DemoUser) Synergykit.getLoggedUser()).getName();

        canvas.drawText(dateTime,logoBitmap.getWidth() + 10, outputBitmap.getHeight() - textHeight, textPaint);

        return outputBitmap;
    }
}
