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

import com.letsgood.sampleapp.model.DemoObject;
import com.letsgood.sampleapp.model.DemoUser;
import com.letsgood.sampleapp.widgets.CustomProgressDialog;
import com.letsgood.synergykitsdkandroid.Synergykit;
import com.letsgood.synergykitsdkandroid.addons.GsonWrapper;
import com.letsgood.synergykitsdkandroid.builders.UriBuilder;
import com.letsgood.synergykitsdkandroid.builders.uri.Resource;
import com.letsgood.synergykitsdkandroid.config.SynergykitConfig;
import com.letsgood.synergykitsdkandroid.listeners.BitmapResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.FileResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.FilesResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.RecordsResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.ResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.UserResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.UsersResponseListener;
import com.letsgood.synergykitsdkandroid.resources.SynergykitError;
import com.letsgood.synergykitsdkandroid.resources.SynergykitFile;
import com.letsgood.synergykitsdkandroid.resources.SynergykitObject;
import com.letsgood.synergykitsdkandroid.resources.SynergykitUri;
import com.letsgood.synergykitsdkandroid.resources.SynergykitUser;

/**
 * Created by Marek on 1/13/15.
 */
public class QueriesActivity extends ActionBarActivity implements View.OnClickListener {

    /* Constants */
    private static final String COLLECTION_DEMO_OBJECTS = "demo-objects";

    /* Attributes */
    private Button findLastUserButton;
    private Button fiveEmailsButton;
    private Button findLastRecordButton;
    private Button fiveTextsButton;
    private Button findLastFileButton;
    private Button fiveFilesButton;
    private LinearLayout outputLinearLayout;


    /* On create */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queries);

        //set action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        //find views
        findLastUserButton = (Button) findViewById(R.id.buttonFindLastUser);
        fiveEmailsButton = (Button) findViewById(R.id.button5Emails);
        findLastRecordButton = (Button) findViewById(R.id.buttonFindLastRecord);
        fiveTextsButton = (Button) findViewById(R.id.button5Texts);
        findLastFileButton = (Button) findViewById(R.id.buttonFindLastFile);
        fiveFilesButton = (Button) findViewById(R.id.button5Files);
        outputLinearLayout = (LinearLayout) findViewById(R.id.linearLayoutOutput);

        //set listeners
        findLastUserButton.setOnClickListener(this);
        fiveEmailsButton.setOnClickListener(this);
        findLastRecordButton.setOnClickListener(this);
        fiveTextsButton.setOnClickListener(this);
        findLastFileButton.setOnClickListener(this);
        fiveFilesButton.setOnClickListener(this);

        //print info
        printOutput("Review source code for more information about what's happening.");

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
            case R.id.buttonFindLastUser:
                findLastUser();
                break;

            case R.id.button5Emails:
                getFiveEmails();
                break;
            case R.id.buttonFindLastRecord:
                findLastRecord();
                break;

            case R.id.button5Texts:
                getFiveTexts();
                break;
            case R.id.buttonFindLastFile:
                findLastFile();
                break;

            case R.id.button5Files:
                getFiveFiles();
                break;
        }
    }

    private void findLastUser(){
        setEnabled(false);

        //clear output
        outputLinearLayout.removeAllViews();

        //build uri
        SynergykitUri synergyKitUri = UriBuilder
                .newInstance()
                .setResource(Resource.RESOURCE_USERS)
                .setOrderByDesc("createdAt")
                .setTop(1)
                .build();

        //create config
        SynergykitConfig config = SynergykitConfig
                .newInstance()
                .setParallelMode(false)
                .setType(DemoUser.class)
                .setUri(synergyKitUri);

        //show progress dialog
        final CustomProgressDialog progressDialog =  new CustomProgressDialog(this,"Finding ...");

        //get user
        Synergykit.getUser(config, new UserResponseListener() {
            @Override
            public void doneCallback(int statusCode, SynergykitUser user) {

                if(((DemoUser) user).getName()==null || ((DemoUser) user).getName().isEmpty())
                    printOutput(((DemoUser) user).getEmail());
                else
                    printOutput(((DemoUser) user).getEmail() + " (" + ((DemoUser) user).getName() + ")");

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

    private void getFiveEmails(){
        setEnabled(false);

        //clear output
        outputLinearLayout.removeAllViews();

        //build uri
        SynergykitUri synergyKitUri = UriBuilder
                .newInstance()
                .setResource(Resource.RESOURCE_USERS)
                .setTop(5)
                .setOrderByDesc("createdAt")
                .build();

        //create config
        SynergykitConfig config = SynergykitConfig
                .newInstance()
                .setParallelMode(false)
                .setType(DemoUser[].class)
                .setUri(synergyKitUri);

        //show progress dialog
        final CustomProgressDialog progressDialog =  new CustomProgressDialog(this,"Getting ...");

        //get users
        Synergykit.getUsers(config, new UsersResponseListener() {
            @Override
            public void doneCallback(int statusCode, SynergykitUser[] users) {

                for (int i = 0; users != null && i < users.length; i++)
                    if(((DemoUser) users[i]).getEmail()==null || ((DemoUser) users[i]).getEmail().isEmpty())
                      printOutput("-");
                    else
                      printOutput(((DemoUser) users[i]).getEmail());

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


    private void findLastRecord(){
        setEnabled(false);

        //clear output
        outputLinearLayout.removeAllViews();

        //build uri
        SynergykitUri synergyKitUri = UriBuilder
                .newInstance()
                .setResource(Resource.RESOURCE_DATA)
                .setCollection(COLLECTION_DEMO_OBJECTS)
                .setOrderByDesc("createdAt")
                .setTop(1)
                .build();

        //create config
        SynergykitConfig config = SynergykitConfig
                .newInstance()
                .setParallelMode(false)
                .setType(DemoObject.class)
                .setUri(synergyKitUri);

        //show progress dialog
        final CustomProgressDialog progressDialog =  new CustomProgressDialog(this,"Finding ...");

        //get record
        Synergykit.getRecord(config, new ResponseListener() {
            @Override
            public void doneCallback(int statusCode, SynergykitObject object) {
                printOutput(((DemoObject) object).getText());
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

    private void getFiveTexts(){
        setEnabled(false);

        //clear output
        outputLinearLayout.removeAllViews();

        //build uri
        SynergykitUri synergyKitUri = UriBuilder
                .newInstance()
                .setResource(Resource.RESOURCE_DATA)
                .setCollection(COLLECTION_DEMO_OBJECTS)
                .setOrderByDesc("createdAt")
                .setTop(5)
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


                for (int i = 0; objects != null && i < objects.length; i++)
                    if(((DemoObject) objects[i]).getText()==null || ((DemoObject) objects[i]).getText().isEmpty())
                        printOutput("-");
                    else
                        printOutput(((DemoObject) objects[i]).getText());
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

    private void findLastFile(){
        setEnabled(false);

        //clear output
        outputLinearLayout.removeAllViews();

        //build uri
        SynergykitUri synergyKitUri = UriBuilder
                .newInstance()
                .setResource(Resource.RESOURCE_FILES)
                .setOrderByDesc("createdAt")
                .setTop(1)
                .build();

        //create config
        SynergykitConfig config = SynergykitConfig
                .newInstance()
                .setParallelMode(false)
                .setType(SynergykitFile.class)
                .setUri(synergyKitUri);

        //show progress dialog
        final CustomProgressDialog progressDialog =  new CustomProgressDialog(this,"Finding ...");

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

                Synergykit.downloadBitmap(file.getPath(), new BitmapResponseListener() {
                    @Override
                    public void doneCallback(int statusCode, Bitmap bitmap) {
                        ImageView imageView = new ImageView(getApplicationContext());
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(android.app.ActionBar.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
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

    private void getFiveFiles(){
        setEnabled(false);

        //clear output
        outputLinearLayout.removeAllViews();

        //build uri
        SynergykitUri synergyKitUri = UriBuilder
                .newInstance()
                .setResource(Resource.RESOURCE_FILES)
                .setOrderByDesc("size")
                .setTop(5)
                .build();

        //create config
        SynergykitConfig config = SynergykitConfig
                .newInstance()
                .setParallelMode(false)
                .setType(SynergykitFile[].class)
                .setUri(synergyKitUri);

        //show progress dialog
        final CustomProgressDialog progressDialog =  new CustomProgressDialog(this,"Getting ...");

        //get files
        Synergykit.getFiles(config, new FilesResponseListener() {
            @Override
            public void doneCallback(int statusCode, SynergykitFile[] files) {

                for (int i = 0; files != null && i < files.length; i++)
                    printOutput(Long.toString(files[i].getSize()/1024) + " Kb");

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


    private void printOutput(String message){
        TextView textView = new TextView(getApplicationContext());
        textView.setTextColor(getResources().getColor(R.color.black));

        if(message==null)
            return;

        textView.setText(message);
        outputLinearLayout.addView(textView);

    }


    private void setEnabled(boolean enabled){
        findLastUserButton.setEnabled(enabled);
        fiveEmailsButton.setEnabled(enabled);
        findLastRecordButton.setEnabled(enabled);
        fiveTextsButton.setEnabled(enabled);
        findLastFileButton.setEnabled(enabled);
        fiveFilesButton.setEnabled(enabled);
    }

}
