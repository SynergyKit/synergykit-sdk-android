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
import com.letsgood.synergykitsdkandroid.listeners.BatchResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.ResponseListener;
import com.letsgood.synergykitsdkandroid.resources.SynergykitBatchItem;
import com.letsgood.synergykitsdkandroid.resources.SynergykitBatchResponse;
import com.letsgood.synergykitsdkandroid.resources.SynergykitEndpoint;
import com.letsgood.synergykitsdkandroid.resources.SynergykitError;
import com.letsgood.synergykitsdkandroid.resources.SynergykitObject;
import com.letsgood.synergykitsdkandroid.resources.SynergykitUri;


/**
 * Created by Marek on 1/13/15.
 */
public class BatchActivity extends ActionBarActivity implements View.OnClickListener {

    /* Constants */
    private static final String COLLECTION_DEMO_OBJECTS = "demo-objects";
    private static final String BATCH_ID = "my-batch-name";

    /* Attributes */
    private Button batchButton;
    private LinearLayout outputLinearLayout;
    private EditText inputEditText;

    /* On create */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batch);

        //set action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        //find views
        batchButton =(Button) findViewById(R.id.buttonBatch);
        outputLinearLayout = (LinearLayout) findViewById(R.id.linearLayoutOutput);
        inputEditText = (EditText) findViewById(R.id.inputEditText);

        //set listeners
        batchButton.setOnClickListener(this);
        printOutput("For more information about actions in batch request watch source code.");
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonBatch:
                postBatch();
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

    private void postBatch(){

        //check signed in
        if(Synergykit.getLoggedUser()==null){
            Toast.makeText(getApplicationContext(),"Your're not signed in. Sign in first.",Toast.LENGTH_SHORT).show();
            return;
        }

        //input value check
        if(inputEditText.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "No inserted text. Insert text first", Toast.LENGTH_SHORT).show();
            return;
        }

        batchButton.setEnabled(false);
        outputLinearLayout.removeAllViews();

        //build uri
        SynergykitUri uri = UriBuilder
                                .newInstance()
                                .setResource(Resource.RESOURCE_DATA)
                                .setCollection(COLLECTION_DEMO_OBJECTS)
                                .setTop(1)
                                .build();

        //create config
        SynergykitConfig config = SynergykitConfig
                                        .newInstance()
                                        .setUri(uri)
                                        .setType(DemoObject.class);


        //show progress dialog
        final CustomProgressDialog progressDialog =  new CustomProgressDialog(this,"Posting ...");

        //get record
        Synergykit.getRecord(config, new ResponseListener() {
            @Override
            public void doneCallback(int statusCode, SynergykitObject object) {
                DemoObject originalObject = (DemoObject) object;
                DemoObject postObject = new DemoObject();
                DemoObject putObject = originalObject;
                DemoObject patchObject = new DemoObject();

                //post object
                postObject.setText(inputEditText.getText().toString());

                //put object
                putObject.setText(inputEditText.getText().toString());

                //patch object
                patchObject.setId(putObject.getId());
                patchObject.setText("another string");
                patchObject.setVersion(putObject.getVersion()+1);


                //init batch
                Synergykit.initBatch(BATCH_ID);


                SynergykitEndpoint batchEndpoint0 = UriBuilder
                        .newInstance()
                        .setResource(Resource.RESOURCE_DATA)
                        .setCollection(COLLECTION_DEMO_OBJECTS)
                        .buildEndpoint();

                SynergykitBatchItem batchItem0 = new SynergykitBatchItem(Synergykit.POST, batchEndpoint0, postObject);
                Synergykit.getBatch(BATCH_ID).add(batchItem0);

                //Batch item 1
                SynergykitEndpoint batchEndpoint1 = UriBuilder
                        .newInstance()
                        .setResource(Resource.RESOURCE_DATA)
                        .setCollection(COLLECTION_DEMO_OBJECTS)
                        .setRecordId(putObject.getId())
                        .buildEndpoint();

                SynergykitBatchItem batchItem1 = new SynergykitBatchItem(Synergykit.PUT, batchEndpoint1, putObject);
                Synergykit.getBatch(BATCH_ID).add(batchItem1);

                //Batch item 2
                SynergykitEndpoint batchEndpoint2 = UriBuilder
                        .newInstance()
                        .setResource(Resource.RESOURCE_DATA)
                        .setCollection(COLLECTION_DEMO_OBJECTS)
                        .setRecordId(patchObject.getId())
                        .buildEndpoint();


                SynergykitBatchItem batchItem2 = new SynergykitBatchItem(Synergykit.PATCH, batchEndpoint2, patchObject);
                Synergykit.getBatch(BATCH_ID).add(batchItem2);

                //Batch item 3
                SynergykitEndpoint batchEndpoint3 = UriBuilder
                        .newInstance()
                        .setResource(Resource.RESOURCE_DATA)
                        .setCollection(COLLECTION_DEMO_OBJECTS)
                        .setRecordId(putObject.getId())
                        .buildEndpoint();

                SynergykitBatchItem batchItem3 = new SynergykitBatchItem(Synergykit.DELETE, batchEndpoint3);
                Synergykit.getBatch(BATCH_ID).add(batchItem3);

                //Batch item 4
                SynergykitEndpoint batchEndpoint4 = UriBuilder
                        .newInstance()
                        .setResource(Resource.RESOURCE_DATA)
                        .setCollection(COLLECTION_DEMO_OBJECTS)
                        .setRecordId(putObject.getId())
                        .buildEndpoint();


                SynergykitBatchItem batchItem4 = new SynergykitBatchItem(Synergykit.PUT, batchEndpoint4, putObject);
                Synergykit.getBatch(BATCH_ID).add(batchItem4);

                //send batch
                Synergykit.sendBatch(BATCH_ID, new BatchResponseListener() {
                    @Override
                    public void doneCallback(int statusCode, SynergykitBatchResponse[] batchResponse) {



                        for (int i = 0; batchResponse != null && i < batchResponse.length; i++) {
                           String jSon =Synergykit.getGson().toJson(batchResponse[i].getBody());
                            if(batchResponse[i].getStatusCode() == 200) {
                                DemoObject demoObject =  Synergykit.getGson().fromJson(jSon,DemoObject.class);
                                printOutput(Integer.toString(batchResponse[i].getStatusCode()) + " " + demoObject.getText());
                            }else {
                                if(jSon!=null){
                                    SynergykitError error = Synergykit.getGson().fromJson(jSon,SynergykitError.class);
                                    printOutput(Integer.toString(batchResponse[i].getStatusCode()) + " " + error.getMessage());

                                }else{
                                    printOutput(Integer.toString(batchResponse[i].getStatusCode()) +  " -" );

                                }

                            }
                        }

                        batchButton.setEnabled(true);
                        progressDialog.dismiss();
                    }

                    @Override
                    public void errorCallback(int statusCode, SynergykitError errorObject) {
                        printOutput(errorObject.toString());
                        batchButton.setEnabled(true);
                        progressDialog.dismiss();
                    }
                }, false);


            }

            @Override
            public void errorCallback(int statusCode, SynergykitError errorObject) {
                printOutput("Getting failed.");
                batchButton.setEnabled(true);
            }
        });


    }
}
