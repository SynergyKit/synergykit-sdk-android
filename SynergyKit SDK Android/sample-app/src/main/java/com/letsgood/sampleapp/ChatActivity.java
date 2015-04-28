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
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.letsgood.sampleapp.adapters.MessageAdapter;
import com.letsgood.sampleapp.model.DemoMessage;
import com.letsgood.sampleapp.model.DemoUser;
import com.letsgood.synergykitsdkandroid.Synergykit;
import com.letsgood.synergykitsdkandroid.addons.GsonWrapper;

import com.letsgood.synergykitsdkandroid.listeners.ResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.SocketEventListener;
import com.letsgood.synergykitsdkandroid.listeners.SocketStateListener;
import com.letsgood.synergykitsdkandroid.log.SynergykitLog;
import com.letsgood.synergykitsdkandroid.resources.SynergykitError;
import com.letsgood.synergykitsdkandroid.resources.SynergykitObject;


import org.json.JSONObject;

/**
 * Created by Letsgood.com - Pavel Stambrecht on 11. 3. 2015.
 */
public class ChatActivity extends ActionBarActivity implements TextWatcher, View.OnKeyListener {



    private Button sendButton = null;
    private ListView messageListView = null;
    private EditText messageEditText = null;
    private TextView typingTextView = null;
    boolean nextTyping = false;
    MessageAdapter adapter;
    private Thread typingThread = null;
    boolean isTyping = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        sendButton = (Button) findViewById(R.id.buttonSend);
        messageListView = (ListView) findViewById(R.id.messageListView);
        messageEditText = (EditText) findViewById(R.id.messageEditText);
        typingTextView = (TextView )findViewById(R.id.typingTextView);

        adapter = new MessageAdapter(getBaseContext(),R.layout.item_chat_message_right);
        messageListView.setAdapter(adapter);

        if(Synergykit.getLoggedUser()==null){
            final DemoMessage message = new DemoMessage();
            message.setText("You're not signed in. Sign in first.");

            message.setType(DemoMessage.TYPE_STATE_LOCAL);

            adapter.add(message);
            adapter.notifyDataSetChanged();

            return;

        }else{
            Synergykit.onSocket("created", "messages", new CreatedMessagesListener());
            Synergykit.onSocket("user_state", new UserStateListener());
            Synergykit.onSocket("typing", new TypingListener());
            Synergykit.connectSocket(new ConnectionStateListener());
        }

        //send button listener
        sendButton.setOnClickListener(new SendButtonOnClickListener());
        messageEditText.setOnKeyListener(this);
        messageEditText.addTextChangedListener(this);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // send action id to manager
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {

      if(Synergykit.getLoggedUser()!=null){
          DemoMessage message = new DemoMessage();
          message.setName(((DemoUser) Synergykit.getLoggedUser()).getName());
          message.setText("leaved");
          message.setUserId(((DemoUser) Synergykit.getLoggedUser()).getId());
          Synergykit.emitViaSocket("user_state", message);

           Synergykit.disconnectSocket();
      }
       super.onDestroy();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        DemoMessage message = new DemoMessage();
        message.setName(((DemoUser) Synergykit.getLoggedUser()).getName());
        message.setUserId(((DemoUser) Synergykit.getLoggedUser()).getId());
        message.setText("is typing");



        Synergykit.emitViaSocket("typing", message);
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {

            if (Synergykit.getLoggedUser()!=null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)
            {
                sendMessage(v);
            }
            return false;

    }

    /** **************************************************************** */
    private class SendButtonOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            sendMessage(v);
        }
    }

    private void sendMessage(View v){
        DemoMessage message = new DemoMessage();
        message.setName(((DemoUser) Synergykit.getLoggedUser()).getName());
        message.setUserId(Synergykit.getLoggedUser().getId());
        message.setText(messageEditText.getText().toString());

        if (message.getText() == null || message.getText().isEmpty())
            return;

        messageEditText.setText("");

        message.setText(message.getText());

        Synergykit.createRecord("messages", message, new ResponseListener() {
            @Override
            public void doneCallback(int statusCode, SynergykitObject object) {

            }

            @Override
            public void errorCallback(int statusCode, SynergykitError errorObject) {
                SynergykitLog.print(errorObject.toString());
            }
        }, false);
    }

    /** **************************************************************** */
    private class ConnectionStateListener implements SocketStateListener {

        @Override
        public void connected() {
            final DemoMessage message = new DemoMessage();
            message.setName(((DemoUser) Synergykit.getLoggedUser()).getName());
            message.setUserId(((DemoUser) Synergykit.getLoggedUser()).getId());
            message.setText("Connected");

            message.setType(DemoMessage.TYPE_STATE_LOCAL);


            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter.add(message);
                    adapter.notifyDataSetChanged();
                }
            });
        }

        @Override
        public void disconnected() {
            final DemoMessage message = new DemoMessage();
            message.setName(((DemoUser) Synergykit.getLoggedUser()).getName());
            message.setUserId(((DemoUser) Synergykit.getLoggedUser()).getId());
            message.setText("Disconnected");

            message.setType(DemoMessage.TYPE_STATE_LOCAL);


            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter.add(message);
                    adapter.notifyDataSetChanged();
                }
            });
        }

        @Override
        public void reconnected() {
            final DemoMessage message = new DemoMessage();
            message.setName(((DemoUser) Synergykit.getLoggedUser()).getName());
            message.setUserId(((DemoUser) Synergykit.getLoggedUser()).getId());
            message.setText("Reconnected");

            message.setType(DemoMessage.TYPE_STATE_LOCAL);


            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter.add(message);
                    adapter.notifyDataSetChanged();
                }
            });
        }

        @Override
        public void unauthorized() {
            final DemoMessage message = new DemoMessage();
            message.setName(((DemoUser) Synergykit.getLoggedUser()).getName());
            message.setUserId(((DemoUser) Synergykit.getLoggedUser()).getId());
            message.setText("Unauthorized");

            message.setType(DemoMessage.TYPE_STATE_LOCAL);


            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter.add(message);
                    adapter.notifyDataSetChanged();
                }
            });
        }
    }

    /** **************************************************************** */
    private class CreatedMessagesListener implements SocketEventListener {
        @Override
        public void call(Object... args) {

            JSONObject jsonObject = (JSONObject) args[0];
            String data = null;

                data = jsonObject.toString();
                final DemoMessage message = GsonWrapper.getGson().fromJson(data, DemoMessage.class);



                if(((DemoUser) Synergykit.getLoggedUser()).getId().equals(message.getUserId()))
                    message.setType(DemoMessage.TYPE_MY_MSG);
                else
                    message.setType(DemoMessage.TYPE_INCOMMING_MSG);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.add(message);
                        adapter.notifyDataSetChanged();
                    }
                });




        }

        @Override
        public void subscribed() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    sendButton.setEnabled(true);
                    messageEditText.setEnabled(true);
                    DemoMessage message = new DemoMessage();
                    message.setName(((DemoUser) Synergykit.getLoggedUser()).getName());
                    message.setUserId(((DemoUser) Synergykit.getLoggedUser()).getId());
                    message.setText("joined");

                    Synergykit.emitViaSocket("user_state", message);

                }
            });
        }

        @Override
        public void unsubscribed() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    sendButton.setEnabled(false);
                    messageEditText.setEnabled(false);
                }
            });

            DemoMessage message = new DemoMessage();
            message.setName(((DemoUser) Synergykit.getLoggedUser()).getName());
            message.setUserId(((DemoUser) Synergykit.getLoggedUser()).getId());
            message.setText("leaved");

            Synergykit.emitViaSocket("user_state", message);
        }

    }


    /** **************************************************************** */
    private class UserStateListener implements SocketEventListener{
        @Override
        public void call(Object... args) {

                String data =((JSONObject) args[0]).toString();

                final DemoMessage message = GsonWrapper.getGson().fromJson(data, DemoMessage.class);

                message.setType(DemoMessage.TYPE_STATE);


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.add(message);
                        adapter.notifyDataSetChanged();
                    }
                });


        }

        @Override
        public void subscribed() {
        }

        @Override
        public void unsubscribed() {
            sendButton.setEnabled(false);
            sendButton.setEnabled(false);
        }
    }

    /** **************************************************************** */
    private class TypingListener implements SocketEventListener{
        @Override
        public void call(final Object... args) {


            if(isTyping==true)
                return;

            setTyping(true);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    String data =((JSONObject) args[0]).toString();
                    final DemoMessage message = GsonWrapper.getGson().fromJson(data, DemoMessage.class);

                    if(((DemoUser) Synergykit.getLoggedUser()).getId().equals(message.getUserId()))
                        return;

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            typingTextView.setText(message.getName() +" "+ message.getText());
                            expand(typingTextView);

                        }
                    });

                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        setTyping(false);
                        e.printStackTrace();
                    }


                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            setTyping(false);
                            collapse(typingTextView);
                            typingTextView.setText("");

                        }
                    });
                }
            }).start();

        }

        @Override
        public void subscribed() {}

        @Override
        public void unsubscribed() {}
    }

    /** **************************************************************** */
    private synchronized void setTyping(boolean typing){
        isTyping = typing;
    }

    public static void expand(final View v) {
        v.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        final int targetHeight = v.getMeasuredHeight();

        v.getLayoutParams().height = 0;
        v.setVisibility(View.VISIBLE);
        Animation a = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().height = interpolatedTime == 1
                        ? ViewGroup.LayoutParams.WRAP_CONTENT
                        : (int)(targetHeight * interpolatedTime);
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration((int)(targetHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }

    public static void collapse(final View v) {
        final int initialHeight = v.getMeasuredHeight();

        Animation a = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if(interpolatedTime == 1){
                    v.setVisibility(View.GONE);
                }else{
                    v.getLayoutParams().height = initialHeight - (int)(initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration((int)(initialHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }

}
