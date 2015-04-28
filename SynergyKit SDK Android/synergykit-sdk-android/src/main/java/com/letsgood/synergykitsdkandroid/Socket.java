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

package com.letsgood.synergykitsdkandroid;

import android.support.annotation.Nullable;


import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.google.gson.annotations.Expose;
import com.letsgood.synergykitsdkandroid.addons.GsonWrapper;
import com.letsgood.synergykitsdkandroid.builders.UriBuilder;
import com.letsgood.synergykitsdkandroid.errors.Errors;
import com.letsgood.synergykitsdkandroid.interfaces.ISocket;
import com.letsgood.synergykitsdkandroid.listeners.SocketEventListener;
import com.letsgood.synergykitsdkandroid.listeners.SocketStateListener;
import com.letsgood.synergykitsdkandroid.log.SynergykitLog;
import com.letsgood.synergykitsdkandroid.resources.SynergykitSocketFilter;
import com.letsgood.synergykitsdkandroid.resources.SynergykitSocketSubscribeObject;

import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Letsgood.com - Pavel Stambrecht on 11. 3. 2015.
 */
public class Socket implements ISocket {

    /* Constants */
    public static final String STATE_CONNECTED = "Socket connected";
    public static final String STATE_DISCONNECTED = "Socket disonnected";
    public static final String STATE_RECONNECTED = "Socket reconnected";
    public static final String STATE_SUBSCRIBED = "Socket subscribed";
    public static final String STATE_UNSUBSCRIBED = "Socket unsubscribed";
    public static final String STATE_CALLED = "Socket called";
    private static final String STATE_UNAUTHORIZED = "Socket unauthorized";
    private static final String EVENT_CONNECTED = "connect";
    private static final String EVENT_DISCONNECTED = "disconnect";
    private static final String EVENT_RECONNECTED = "reconnect";
    private static final String EVENT_SUBSCRIBED = "subscribed";
    private static final String EVENT_UNSUBSCRIBED = "unsubscribed";
    private static final String EVENT_UNAUTHORIZED = "unauthorized";
    private static final String EMIT_SUBSCRIBE = "subscribe";
    private static final String EMIT_UNSUBSCRIBE = "unsubscribe";
    private static final String EMIT_SPEAK = "speak";

    /* Attributes */
    private com.github.nkzawa.socketio.client.Socket socket = null;
    private List<SocketListenerItem> socketListenerItems = new LinkedList<>();
    private final StateListener stateListener = new StateListener();

    /* Init socket*/
    @Override
    public boolean initSocket() {
        UriBuilder uriBuilder = new UriBuilder();

        try {
              socket = IO.socket(uriBuilder.getSocketUrl().toString());
            return true;
        } catch (URISyntaxException e) {
            SynergykitLog.print(Errors.MSG_SOCKET_INIT_FAILED);
            e.printStackTrace();
            return false;
        }
    }

    /* In socket initialized*/
    @Override
    public boolean isSocketInitialized() {

        if(socket!=null)
            return true;

        return false;
    }

    /* Is socket connected */
    @Override
    public boolean isSocketConnected() {

        if(socket!=null && socket.connected())
            return true;

        return false;
    }


    /* Is prepared*/
    private boolean isPrepared(){
        //init check
        if(!Synergykit.isInit()){
            SynergykitLog.print(Errors.MSG_SK_NOT_INITIALIZED);
            return false;
        }

        //init check
        if(!isSocketInitialized()){
            SynergykitLog.print(Errors.MSG_SOCKET_NOT_INITED);
            return false;
        }

        return true;
    }

    /* Connect socket */
    @Override
    public void connectSocket() {
        this.connectSocket(null);
    }
    /* Connect socket */
    @Override
    public void connectSocket( @Nullable SocketStateListener listener) {

        if(!isPrepared())
            return;  //prepared check

        stateListener.setSocketStateListener(listener); //set socket state listener
        stateListener.setEnabled(true); //set base listeners
        this.setBufferedListeners(); //set buffered listeners

     socket.connect();
    }

    /* Disconnect socket */
    @Override
    public void disconnectSocket() {
        if(!isPrepared())
            return;  //prepared check


        for(int i=0; socketListenerItems !=null && i < socketListenerItems.size(); i++){
            unsubscribe(socketListenerItems.get(i));
        } //unsubcribe

         unsetBufferedListeners(); //unset buffer listener

        if(socketListenerItems!=null)
             socketListenerItems.clear(); // clear buffer

        stateListener.setEnabled(false); //unset base listeners

        if(socket!=null && socket.connected()){
            socket.disconnect(); //disconnect
            SynergykitLog.print(STATE_DISCONNECTED); //state disconnected
        }

        if(stateListener.getSocketStateListener()!=null)
            stateListener.getSocketStateListener().disconnected();
    }

    /* On socket */
    @Override
    public void onSocket(String eventName, SocketEventListener listener) {
        this.onSocket(eventName,null,null,listener);
    }

    /* On socket */
    @Override
    public void onSocket(String eventName, @Nullable String collectionName, SocketEventListener listener) {
        this.onSocket(eventName,collectionName,null,listener);
    }

    /* On socket */
    @Override
    public void onSocket(String eventName, @Nullable String collectionName, @Nullable SynergykitSocketFilter filter, SocketEventListener listener) {
        SocketListenerItem socketListenerItem = null;


       if(!isPrepared())
            return;  //prepared check

        socketListenerItem  = new SocketListenerItem(eventName, collectionName,filter, listener); //init socket item

        //on and buffer
        if(isSocketConnected()){
            socket.on(socketListenerItem.getEvent(), socketListenerItem.getCallListener());
            socket.on(EVENT_SUBSCRIBED + "_" + socketListenerItem.getEvent(), socketListenerItem.getSubscribedListener());
            socket.on(EVENT_UNSUBSCRIBED + "_" + socketListenerItem.getEvent(), socketListenerItem.getUnsubscribedListener());
            socket.emit(EMIT_SUBSCRIBE, GsonWrapper.getGson().toJson(socketListenerItem.getSubscribeObject()));
        }

        socketListenerItems.add(socketListenerItem);
    }

    /* Off Socket */
    @Override
    public void offSocket(String eventName, SocketEventListener listener) {
        this.offSocket(eventName, null, null, listener);
    }

    /* Off Socket */
    @Override
    public void offSocket(String eventName,@Nullable String collectionName, SocketEventListener listener) {
        this.offSocket(eventName,collectionName,null,listener);
    }

    /* Off Socket */
    @Override
    public void offSocket(String eventName,@Nullable String collectionName,@Nullable SynergykitSocketFilter filter, SocketEventListener listener) {
        SocketListenerItem socketListenerItem = null;
        SocketListenerItem selectedSocketListenerItem=null;

        if(!isPrepared())
            return;  //prepared check

        //init socketItem
        socketListenerItem = new SocketListenerItem(eventName,collectionName,filter,listener);


        //off listener if buffered
        if(isSocketConnected()){
            for(int i=0; socketListenerItems!=null && i<socketListenerItems.size(); i++){
                selectedSocketListenerItem = socketListenerItems.get(i);
                if(selectedSocketListenerItem!=null && selectedSocketListenerItem.equals(socketListenerItem)){

                    // off
                    if(selectedSocketListenerItem.getCallListener()!=null)
                        socket.off(selectedSocketListenerItem.getEvent(),selectedSocketListenerItem.getCallListener());
                    else
                        socket.off(selectedSocketListenerItem.getEvent());

                    //call unsubscribed
                    if(selectedSocketListenerItem.getUnsubscribedListener()!=null)
                        selectedSocketListenerItem.getUnsubscribedListener().call();

                }
            }
        }
    }

    /* Subscribe listener */
    private void subscribe(SocketListenerItem socketListenerItem){

        if (socketListenerItem==null)
            throw new IllegalArgumentException();


        if(!isPrepared())
            return;  //prepared check

        socket.emit(EMIT_SUBSCRIBE,GsonWrapper.getGson().toJson(socketListenerItem.getSubscribeObject()));
    }

    /* Unsubscribe listener */
    private void unsubscribe(SocketListenerItem socketListenerItem){

        if (socketListenerItem==null)
            throw new IllegalArgumentException();


        if(!isPrepared())
            return;  //prepared check

        socket.emit(EMIT_UNSUBSCRIBE,GsonWrapper.getGson().toJson(socketListenerItem.getSubscribeObject()));
    }


    /* Emit via socket */
    @Override
    public void emitViaSocket(String event, Object object) {
        SocketSpeakItem socketSpeakItem = null;


        if(!isPrepared())
            return;  //prepared check

        socketSpeakItem = new SocketSpeakItem(event,object);

        socket.emit(EMIT_SPEAK,GsonWrapper.getGson().toJson(socketSpeakItem));
    }


     /* Buffered listeners setter */
    private void setBufferedListeners(){
        SocketListenerItem socketListenerItem = null;


        /* Set buffered listeners */
        for(int i=0; socketListenerItems !=null && i < socketListenerItems.size();i++ ) {

            socketListenerItem = socketListenerItems.get(i);

            if(socketListenerItem==null)
                continue;

            socket.on(socketListenerItem.getEvent(), socketListenerItem.getCallListener());
            socket.on(EVENT_SUBSCRIBED + "_" + socketListenerItem.getEvent(), socketListenerItem.getSubscribedListener());
            socket.on(EVENT_UNSUBSCRIBED + "_" + socketListenerItem.getEvent(), socketListenerItem.getUnsubscribedListener());

        }
    }

    /* Buffered listeners setter */
    private void unsetBufferedListeners(){
        SocketListenerItem socketListenerItem = null;


        /* Set buffered listeners */
        for(int i=0; socketListenerItems !=null && i < socketListenerItems.size();i++ ) {

            socketListenerItem = socketListenerItems.get(i);

            if(socketListenerItem==null)
                continue;

            socket.off(socketListenerItem.getEvent(), socketListenerItem.getCallListener());
            socket.off(EVENT_SUBSCRIBED + "_" + socketListenerItem.getEvent(), socketListenerItem.getSubscribedListener());
            socket.off(EVENT_UNSUBSCRIBED + "_" + socketListenerItem.getEvent(), socketListenerItem.getUnsubscribedListener());

        }
    }




    //----------------------------------------------------------------------------------------------
    private class StateListener{

        /* Attributes */
        private SocketStateListener socketStateListener = null;

        /* Connected listener */
        private final Emitter.Listener connectedListener = new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                for(int i=0; socketListenerItems !=null && i < socketListenerItems.size(); i++){
                    subscribe(socketListenerItems.get(i));
                }

                SynergykitLog.print(STATE_CONNECTED);

                if(socketStateListener!=null)
                    socketStateListener.connected();

            }
        };

        /* Disconnected listener */
        private final Emitter.Listener disconnectedListener = new Emitter.Listener() {
            @Override
            public void call(Object... args) {

                unsetBufferedListeners(); //unset buffered listeners

                SynergykitLog.print(STATE_DISCONNECTED);

                if(socketStateListener!=null)
                    socketStateListener.disconnected();
            }
        };

        /* Reconnected listener */
        private final Emitter.Listener reconnectedListener = new Emitter.Listener() {
            @Override
            public void call(Object... args) {

                setBufferedListeners(); //set buffured listeners

                SynergykitLog.print(STATE_RECONNECTED);

                if(socketStateListener!=null)
                    socketStateListener.reconnected();
            }
        };

        /* Unauthorized */
        private final Emitter.Listener unauthorizedListener = new Emitter.Listener() {
            @Override
            public void call(Object... args) {

                SynergykitLog.print(STATE_UNAUTHORIZED);

                if(socketStateListener!=null)
                    socketStateListener.unauthorized();
            }
        };

        /* Socket state listener setter*/
        public void setSocketStateListener(@Nullable SocketStateListener socketStateListener) {
            this.socketStateListener = socketStateListener;
        }

        /* Socket state listener getter */
        public SocketStateListener getSocketStateListener(){
            return this.socketStateListener;
        }

        /* Connected listener getter*/
        public Emitter.Listener getConnectedListener() {
            return connectedListener;
        }

        /* Disconnected listener getter*/
        public Emitter.Listener getDisconnectedListener() {
            return disconnectedListener;
        }

        /* Reconnected listener getter*/
        public Emitter.Listener getReconnectedListener() {
            return reconnectedListener;
        }

        /* Enabled setter */
        public void setEnabled(boolean enabled){

            if(!isSocketInitialized())
                return;

            if(enabled){
                socket.on(EVENT_CONNECTED,connectedListener); //on connected
                socket.on(EVENT_RECONNECTED,reconnectedListener); //on reconnected
                socket.on(EVENT_DISCONNECTED,disconnectedListener); //on disconnected
                socket.on(EVENT_UNAUTHORIZED,unauthorizedListener); //on unauthorized
            }else{
                socket.off(EVENT_CONNECTED,connectedListener); //on connected
                socket.off(EVENT_RECONNECTED,reconnectedListener); //on reconnected
                socket.off(EVENT_DISCONNECTED,disconnectedListener); //on disconnected
                socket.off(EVENT_UNAUTHORIZED,unauthorizedListener); //on unauthorized
            }


        }
    }
    //----------------------------------------------------------------------------------------------

    private class SocketSpeakItem{

        /* Attributes */
        @Expose
        private Object message;

        @Expose
        private String tenant =null;

        @Expose
        private String token = null;

        @Expose
        private String eventName = null;

        /* Constructor */
        public SocketSpeakItem(String eventName,Object message) {

            this.message = message;
            this.eventName = eventName;
            this.tenant = Synergykit.getTenant();
            this.token = Synergykit.getSessionToken();

        }
    }
    //----------------------------------------------------------------------------------------------
    /* Socket listener */
    private class SocketListenerItem{

        /* Attributes */
        protected SynergykitSocketSubscribeObject subscribeObject;
        private SocketEventListener socketListener = null;
        private Emitter.Listener callListener;
        private Emitter.Listener subscribedListener;
        private Emitter.Listener unsubscribedListener;


        /* Constructor */
        public SocketListenerItem(String eventName, @Nullable String collectionName,@Nullable SynergykitSocketFilter filter,SocketEventListener listener){
            this.subscribeObject = new SynergykitSocketSubscribeObject();
            this.subscribeObject.setEventName(eventName);
            this.subscribeObject.setCollectionName(collectionName);
            this.subscribeObject.setFilter(filter);

            this.socketListener = listener;
            this.callListener = initCallListener(listener);
            this.subscribedListener = initSubscribedListener(listener);
            this.unsubscribedListener = initUnsubscribedListener(listener);
        }


        /* Call listener getter */
        public Emitter.Listener getCallListener() {
            return callListener;
        }

        /* Subscribed listener getter */
        public Emitter.Listener getSubscribedListener() {
            return subscribedListener;
        }

        /* Unsubscribed listener getter */
        public Emitter.Listener getUnsubscribedListener() {
            return unsubscribedListener;
        }


        /* Socket subscribe object getter */
        public SynergykitSocketSubscribeObject getSubscribeObject() {
            return subscribeObject;
        }



        /* Event getter */
        public String getEvent() {
            String event = subscribeObject.getEventName();

            if(subscribeObject.getCollectionName()!=null)
                event+="_" + subscribeObject.getCollectionName();

            if(subscribeObject.getFilter()!=null)
                event+="_" + subscribeObject.getFilter().getName();

            return event;
        }


        /* Init call listener */
        private Emitter.Listener initCallListener(final SocketEventListener listener){
            return new Emitter.Listener() {
                @Override
                public void call(Object... args) {

                    SynergykitLog.print(getEvent() + ": " + STATE_CALLED); //print state

                    //call listener
                    if(listener!=null)
                        listener.call(args);
                    else
                        SynergykitLog.print(getEvent() + ": " + Errors.MSG_NO_CALLBACK_LISTENER);

                }
            };
        }


        /* Init subscribed listener */
        private Emitter.Listener initSubscribedListener(final SocketEventListener listener){
            return new Emitter.Listener() {
                @Override
                public void call(Object... args) {

                    SynergykitLog.print(getEvent() + ": " + STATE_SUBSCRIBED); //print state

                    //subscribed listener
                    if(listener!=null)
                        listener.subscribed();
                    else
                        SynergykitLog.print(getEvent() + ": " + Errors.MSG_NO_CALLBACK_LISTENER);
                }
            };
        }


        /* Init subscribed listener */
        private Emitter.Listener initUnsubscribedListener(final SocketEventListener listener){
            return new Emitter.Listener() {
                @Override
                public void call(Object... args) {

                    SynergykitLog.print(getEvent() + ": " + STATE_UNSUBSCRIBED); //print state

                    //unsubscribed listener
                    if(listener!=null)
                        listener.unsubscribed();
                    else
                        SynergykitLog.print(getEvent() + ": " + Errors.MSG_NO_CALLBACK_LISTENER);


                }
            };
        }



        /* Equal */
        @Override
        public boolean equals(Object o) {
            SocketListenerItem socketListenerItem;


            if(o==null || !(o instanceof SocketListenerItem))
                return false;

            socketListenerItem = (SocketListenerItem) o;

            if(this.subscribeObject!=null && socketListenerItem.subscribeObject==null)
                return false;

            if(this.subscribeObject==null && socketListenerItem.subscribeObject!=null)
                return false;

            if(!this.subscribeObject.equals(socketListenerItem.subscribeObject))
                return false;


            if(this.socketListener!=null && socketListenerItem.socketListener==null)
                return false;

            if(this.socketListener==null &&  socketListenerItem.socketListener!=null)
                return false;

            if(this.socketListener != socketListenerItem.socketListener)
                return false;


            return true;
        }
    }
}
