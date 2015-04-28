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

package com.letsgood.sampleapp.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.letsgood.sampleapp.R;
import com.letsgood.sampleapp.model.DemoMessage;


/**
 * Created by Letsgood.com - Pavel Stambrecht on 25. 3. 2015.
 */
public class MessageAdapter extends ArrayAdapter<DemoMessage> {

    /* Constructor */
    public MessageAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        DemoMessage message = getItem(position);

        if(message==null)
            return convertView;


        if(message.getType()== DemoMessage.TYPE_MY_MSG) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_chat_message_left, parent, false);

            //message
            TextView messageTextView = (TextView) convertView.findViewById(R.id.messageTextView);
            messageTextView.setText(message.getText());

            //letters
            TextView userLetterTextView = (TextView) convertView.findViewById(R.id.userLetterTextView);
            userLetterTextView.setText("-");

            if(message.getName()!=null || !message.getName().isEmpty()){
                String[] nameParts = message.getName().split(" ");
                String userLetters = new String();

                for(int i=0; nameParts!=null && i<nameParts.length; i++){

                    if(nameParts[i]!=null &&nameParts[i].length()>1){
                        userLetters+=String.valueOf(nameParts[i].charAt(0));
                    }
                }

                if(!userLetters.isEmpty()){
                    userLetterTextView.setText(userLetters);
                }
            }


        }else if (message.getType()== DemoMessage.TYPE_STATE) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_chat_message_center, parent, false);
            TextView messageTextView = (TextView) convertView.findViewById(R.id.messageTextView);
            messageTextView.setText(message.getName() + " " + message.getText());




        }else if (message.getType()== DemoMessage.TYPE_STATE_LOCAL) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_chat_message_center, parent, false);
            TextView messageTextView = (TextView) convertView.findViewById(R.id.messageTextView);
            messageTextView.setText(message.getText());




        }else {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_chat_message_right, parent, false);

            //message
            TextView messageTextView = (TextView) convertView.findViewById(R.id.messageTextView);
            messageTextView.setText(message.getText());

            //letters
            TextView userLetterTextView = (TextView) convertView.findViewById(R.id.userLetterTextView);
            userLetterTextView.setText("");

            if(message.getName()!=null || !message.getName().isEmpty()){
                String[] nameParts = message.getName().split(" ");
                String userLetters = new String();

                for(int i=0; nameParts!=null && i<nameParts.length; i++){

                    if(nameParts[i]!=null &&nameParts[i].length()>1){
                        userLetters+=String.valueOf(nameParts[i].charAt(0));
                    }
                }

                if(!userLetters.isEmpty()){
                    userLetterTextView.setText(userLetters);
                }
            }
        }
        return convertView;
    }
}
