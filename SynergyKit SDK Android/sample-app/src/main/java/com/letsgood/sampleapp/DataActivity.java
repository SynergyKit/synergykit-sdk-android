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


import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.letsgood.sampleapp.model.DemoObject;
import com.letsgood.sampleapp.widgets.CustomProgressDialog;
import com.letsgood.synergykitsdkandroid.Synergykit;
import com.letsgood.synergykitsdkandroid.builders.UriBuilder;
import com.letsgood.synergykitsdkandroid.builders.uri.Resource;
import com.letsgood.synergykitsdkandroid.config.SynergykitConfig;
import com.letsgood.synergykitsdkandroid.listeners.RecordsResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.ResponseListener;
import com.letsgood.synergykitsdkandroid.resources.SynergykitError;
import com.letsgood.synergykitsdkandroid.resources.SynergykitObject;
import com.letsgood.synergykitsdkandroid.resources.SynergykitUri;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 * Created by Marek on 1/13/15.
 */
public class DataActivity extends ActionBarActivity implements View.OnClickListener {

    /* Constants */
    private static final String COLLECTION_DEMO_OBJECTS = "demo-objects";

    /* Attributes */
    private Button postDataButton;
    private Button getDataButton;
    private LinearLayout outputLinearLayout;
    private EditText inputEditText;


    /* On create */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        //set action bat
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        //find views
        postDataButton =(Button) findViewById(R.id.buttonPostData);
        getDataButton =(Button) findViewById(R.id.buttonGetData);
        outputLinearLayout = (LinearLayout) findViewById(R.id.linearLayoutOutput);
        inputEditText = (EditText) findViewById(R.id.inputEditText);

        //set listeners
        postDataButton.setOnClickListener(this);
        getDataButton.setOnClickListener(this);

        //print info
        printOutput("At first post some data.");

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
            case R.id.buttonPostData:
                postData();
                break;

            case R.id.buttonGetData:
                getData();
                break;
        }
    }



    private void postData(){

        //check values
        if(inputEditText.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),"No inserted text. Insert text first",Toast.LENGTH_SHORT).show();
            return;
        }

        postDataButton.setEnabled(false);
        getDataButton.setEnabled(false);

        //create object
        final DemoObject demoObject = new DemoObject();
        demoObject.setText(inputEditText.getText().toString());

        //clear output
        outputLinearLayout.removeAllViews();

        //show progress dialog
        final CustomProgressDialog progressDialog =  new CustomProgressDialog(this,"Posting ...");

        //create record
        Synergykit.createRecord(COLLECTION_DEMO_OBJECTS, demoObject, new ResponseListener() {
            @Override
            public void doneCallback(int statusCode, SynergykitObject object) {
                GregorianCalendar gc = new GregorianCalendar();
                gc.setTimeInMillis(object.getCreatedAt());
                SimpleDateFormat fmt = new SimpleDateFormat("MM/dd HH:mm:ss");



                printOutput(fmt.format(gc.getTime())+" " + demoObject.getText());
                postDataButton.setEnabled(true);
                getDataButton.setEnabled(true);
                inputEditText.getText().clear();
                progressDialog.dismiss();
            }

            @Override
            public void errorCallback(int statusCode, SynergykitError errorObject) {
                printOutput(errorObject.toString());
                postDataButton.setEnabled(true);
                getDataButton.setEnabled(true);
                progressDialog.dismiss();
            }
        }, false);

    }

    private void getData(){
        postDataButton.setEnabled(false);
        getDataButton.setEnabled(false);

        //clear output
        outputLinearLayout.removeAllViews();

        //build uri
        SynergykitUri synergyKitUri = UriBuilder
                                        .newInstance()
                                        .setResource(Resource.RESOURCE_DATA)
                                        .setCollection(COLLECTION_DEMO_OBJECTS)
                                        .setOrderByDesc("createdAt")
                                        .setTop(10)
                                        .build();

        //create config
        SynergykitConfig config = SynergykitConfig
                                    .newInstance()
                                    .setParallelMode(false)
                                    .setType(DemoObject[].class)
                                    .setUri(synergyKitUri);


        //show progress dialog
        final CustomProgressDialog progressDialog =  new CustomProgressDialog(this,"Getting ...");

        //get records
        Synergykit.getRecords(config, new RecordsResponseListener() {
            @Override
            public void doneCallback(int statusCode, SynergykitObject[] objects) {

                for (int i = 0; i < objects.length; i++){
                    GregorianCalendar gc = new GregorianCalendar();
                    gc.setTimeInMillis(objects[i].getCreatedAt());
                    SimpleDateFormat fmt = new SimpleDateFormat("MM/dd HH:mm:ss");
                    printOutput(fmt.format(gc.getTime())+" " + ((DemoObject)objects[i]).getText());
                }
                postDataButton.setEnabled(true);
                getDataButton.setEnabled(true);
                progressDialog.dismiss();
            }

            @Override
            public void errorCallback(int statusCode, SynergykitError errorObject) {
                printOutput("Getting failed");
                printOutput(errorObject.toString());
                postDataButton.setEnabled(true);
                getDataButton.setEnabled(true);
                progressDialog.dismiss();
            }
        });
    }

    private void printOutput(String message){
        TextView textView = new TextView(getApplicationContext());
        textView.setTextColor(getResources().getColor(R.color.black));

        if(message==null)
            return;

        textView.setText(message);

        outputLinearLayout.addView(textView);


    }




}
