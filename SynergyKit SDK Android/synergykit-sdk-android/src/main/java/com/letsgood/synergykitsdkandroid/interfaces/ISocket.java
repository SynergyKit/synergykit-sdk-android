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

package com.letsgood.synergykitsdkandroid.interfaces;


import android.support.annotation.Nullable;

import com.letsgood.synergykitsdkandroid.listeners.SocketEventListener;
import com.letsgood.synergykitsdkandroid.listeners.SocketStateListener;
import com.letsgood.synergykitsdkandroid.resources.SynergykitSocketFilter;

/**
 * Created by Letsgood.com - Pavel Stambrecht on 11. 3. 2015.
 */
public interface ISocket {
    public boolean initSocket();
    public boolean isSocketInitialized();
    public boolean isSocketConnected();

    public void connectSocket();
    public void connectSocket(@Nullable SocketStateListener listener);
    public void disconnectSocket();

    public void onSocket(String eventName, SocketEventListener listener);
    public void onSocket(String eventName, @Nullable String collectionName, SocketEventListener listener);
    public void onSocket(String eventName, @Nullable String collectionName, @Nullable SynergykitSocketFilter filter, SocketEventListener listener);

    public void offSocket(String eventName, SocketEventListener listener);
    public void offSocket(String eventName, @Nullable String collectionName, SocketEventListener listener);
    public void offSocket(String eventName, @Nullable String collectionName, @Nullable SynergykitSocketFilter filter, SocketEventListener listener);

    public void emitViaSocket(String eventName, Object object);



}
