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

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.letsgood.sampleapp.widgets.CustomProgressDialog;
import com.letsgood.synergykitsdkandroid.Synergykit;
import com.letsgood.synergykitsdkandroid.listeners.NotificationResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.PlatformResponseListener;
import com.letsgood.synergykitsdkandroid.log.SynergykitLog;
import com.letsgood.synergykitsdkandroid.request.SynergykitRequest;
import com.letsgood.synergykitsdkandroid.resources.SynergykitError;
import com.letsgood.synergykitsdkandroid.resources.SynergykitNotification;
import com.letsgood.synergykitsdkandroid.resources.SynergykitPlatform;

import java.io.IOException;


/**
 * Created by Marek on 1/13/15.
 */
public class NotificationsActivity  extends ActionBarActivity implements View.OnClickListener {

    /* Constants */
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private static final  String SENDER_ID = "475333245233";


    /* Attributes */
    private Button sendButton;
    private LinearLayout outputLinearLayout;
    private EditText inputEditText;
    private GoogleCloudMessaging gcm;
    private String regid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        //set action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        //find views
        sendButton =(Button) findViewById(R.id.buttonSend);
        outputLinearLayout = (LinearLayout) findViewById(R.id.linearLayoutOutput);
        inputEditText = (EditText) findViewById(R.id.inputEditText);

        //set listeners
        sendButton.setOnClickListener(this);


        //check signed in
        if(Synergykit.getLoggedUser()==null){
               return;
        }

        // Check device for Play Services APK.
        if (checkPlayServices() ) {
            gcm = GoogleCloudMessaging.getInstance(this);
            regid = null;

            if(Synergykit.getLoggedUser().getPlatforms()!=null) {
                for (SynergykitPlatform p : Synergykit.getLoggedUser().getPlatforms()) {

                    if (p.getPlatformName().equals("android"))
                        regid = p.getRegistrationId();

                }
            }

            if (regid == null || regid.isEmpty())
                registerInBackground();
            else
                sendButton.setEnabled(true);

        } else {
            SynergykitLog.print("No valid Google Play Services APK found.");
        }
    }


    // You need to do the Play Services APK check here too.
    @Override
    protected void onResume() {
        super.onResume();
        checkPlayServices();
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


    private void sendNotification(){

        //check signed in
        if(Synergykit.getLoggedUser()==null){
            Toast.makeText(getApplicationContext(), "Your're not signed in. Sign in first.", Toast.LENGTH_SHORT).show();
            return;
        }

        //check values
        if(inputEditText.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),"No text was written. Write it first.",Toast.LENGTH_SHORT).show();
            return;
        }


        sendButton.setEnabled(false);

        //create notification
        SynergykitNotification notification = new SynergykitNotification();
        notification.setAlert(inputEditText.getText().toString());
        notification.addUserId(Synergykit.getLoggedUser().getId());

        outputLinearLayout.removeAllViews(); //clear output

        //show progress dialog
        final CustomProgressDialog progressDialog =  new CustomProgressDialog(this,"Sending ...");

        //send notification
        Synergykit.sendNotification(notification, new NotificationResponseListener() {
            @Override
            public void doneCallback(int statusCode) {
                progressDialog.dismiss();
                sendButton.setEnabled(true);
            }

            @Override
            public void errorCallback(int statusCode, SynergykitError errorObject) {
                printOutput(errorObject.toString());
                sendButton.setEnabled(true);
                progressDialog.dismiss();
            }
        }, true);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonSend:
                sendNotification();
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


    /**
     * Check the device to make sure it has the Google Play Services APK. If
     * it doesn't, display a dialog that allows users to download the APK from
     * the Google Play Store or enable it in the device's system settings.
     */
    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                SynergykitLog.print("This device is not supported.");
                finish();
            }
            return false;
        }
        return true;
    }


    /**
     * Registers the application with GCM servers asynchronously.
     *
     * Stores the registration ID and app versionCode in the application's
     * shared preferences.
     */
    private void registerInBackground() {
        printOutput("Registring registration ID...");

        Synergykit.synergylize(new SynergykitRequest() {
            @Override
            protected Object doInBackground(Void... params) {
                try {
                    if (gcm == null) {
                        gcm = GoogleCloudMessaging.getInstance(getApplicationContext());
                    }

                    regid = gcm.register(SENDER_ID);

                } catch (IOException ex) {
                    printOutput(ex.toString());
                }

                return regid;
            }

            @Override
            protected void onPostExecute(Object object) {
                // You should send the registration ID to your server over HTTP,
                // so it can use GCM/HTTP or CCS to send messages to your app.
                // The request to your server should be authenticated if your app
                // is using accounts.
                printOutput("Device registered, registration ID=" + (String) object);

                SynergykitPlatform platform = new SynergykitPlatform((String) object);
                printOutput("Storing registration ID...");

                SynergykitLog.print(Synergykit.getSessionToken());

                Synergykit.addPlatform(platform, new PlatformResponseListener() {
                    @Override
                    public void doneCallback(int statusCode, SynergykitPlatform platform) {
                        printOutput("Registration ID stored");
                        sendButton.setEnabled(true);
                    }

                    @Override
                    public void errorCallback(int statusCode, SynergykitError errorObject) {
                        printOutput("Registration ID storing failed");
                        printOutput(errorObject.toString());

                    }
                }, true);
            }
        }, false);

    }

}
