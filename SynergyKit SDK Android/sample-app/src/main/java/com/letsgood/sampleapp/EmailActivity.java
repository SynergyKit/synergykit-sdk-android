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

import com.letsgood.sampleapp.model.DemoEmail;
import com.letsgood.sampleapp.widgets.CustomProgressDialog;
import com.letsgood.synergykitsdkandroid.Synergykit;
import com.letsgood.synergykitsdkandroid.listeners.EmailResponseListener;
import com.letsgood.synergykitsdkandroid.resources.SynergykitError;


/**
 * Created by Marek on 1/13/15.
 */
public class EmailActivity extends ActionBarActivity implements View.OnClickListener {


    /* Attributes */
    private Button sendButton;
    private LinearLayout outputLinearLayout;
    private EditText inputEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

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


    private void sendEmail(){

        //check signed in
        if(Synergykit.getLoggedUser()==null){
            Toast.makeText(getApplicationContext(),"Your're not signed in. Sign in first.",Toast.LENGTH_SHORT).show();
            return;
        }

        //check values
        if(inputEditText.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),"No text was written. Write it first.",Toast.LENGTH_SHORT).show();
            return;
        }

        sendButton.setEnabled(false);

        //create email
        DemoEmail email = new DemoEmail();
        email.setEmail(Synergykit.getLoggedUser().getEmail());
        email.setText(inputEditText.getText().toString());
        email.setSubject("SynergyKit Sample App Demo Email");

        //clear output
        outputLinearLayout.removeAllViews();

        //show progress dialog
        final CustomProgressDialog progressDialog =  new CustomProgressDialog(this,"Sending ...");

        //send email
        Synergykit.sendEmail(DemoEmail.EMAIL_ID, email, new EmailResponseListener() {
            @Override
            public void doneCallback(int statusCode) {
                printOutput("Email was send.");
                sendButton.setEnabled(true);
                progressDialog.dismiss();

            }

            @Override
            public void errorCallback(int statusCode, SynergykitError errorObject) {
                printOutput("Sending failed");
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
                sendEmail();
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
}
