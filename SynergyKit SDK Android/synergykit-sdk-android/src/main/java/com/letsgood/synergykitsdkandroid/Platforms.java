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

import com.letsgood.synergykitsdkandroid.builders.UriBuilder;
import com.letsgood.synergykitsdkandroid.errors.Errors;
import com.letsgood.synergykitsdkandroid.builders.uri.Resource;
import com.letsgood.synergykitsdkandroid.config.SynergykitConfig;
import com.letsgood.synergykitsdkandroid.interfaces.IPlatforms;
import com.letsgood.synergykitsdkandroid.listeners.DeleteResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.PlatformResponseListener;
import com.letsgood.synergykitsdkandroid.log.SynergykitLog;
import com.letsgood.synergykitsdkandroid.request.PlatformRequestGet;
import com.letsgood.synergykitsdkandroid.request.PlatformRequestPost;
import com.letsgood.synergykitsdkandroid.request.PlatformRequestPut;
import com.letsgood.synergykitsdkandroid.request.RequestDelete;
import com.letsgood.synergykitsdkandroid.resources.SynergykitError;
import com.letsgood.synergykitsdkandroid.resources.SynergykitPlatform;

/**
 * Created by Letsgood.com - Pavel Stambrecht on 27. 4. 2015.
 */
public class Platforms implements IPlatforms {


    /* Add platform */
    @Override
    public void addPlatform(SynergykitPlatform platform, PlatformResponseListener listener, boolean parallelMode) {
        SynergykitConfig config = new SynergykitConfig();
        PlatformRequestPost request = new PlatformRequestPost();

        //User check
        if(platform == null){
            //Log
            SynergykitLog.print(Errors.MSG_NO_OBJECT);


            //error callback
            if(listener!=null)
                listener.errorCallback(Errors.SC_NO_OBJECT, new SynergykitError(Errors.SC_NO_OBJECT, Errors.MSG_NO_OBJECT));
            else if(Synergykit.isDebugModeEnabled())
                SynergykitLog.print(Errors.MSG_NO_CALLBACK_LISTENER);

            return;
        }

        //Uri builder
        UriBuilder uriBuilder = new UriBuilder()
                .setResource(Resource.RESOURCE_USERS_PLATFORMS);

        //set config
        config.setUri(uriBuilder.build());
        config.setParallelMode(parallelMode);
        config.setType(platform.getClass());


        //set request
        request.setConfig(config);
        request.setListener(listener);
        request.setObject(platform);

        //execute
        Synergykit.synergylize(request, parallelMode);
    }

    /* Update platform */
    @Override
    public void updatePlatform(SynergykitPlatform platform, PlatformResponseListener listener, boolean parallelMode) {
        SynergykitConfig config = new SynergykitConfig();
        PlatformRequestPut request = new PlatformRequestPut();

        //User check
        if(platform == null){
            //Log
            SynergykitLog.print(Errors.MSG_NO_OBJECT);

            //error callback
            if(listener!=null)
                listener.errorCallback(Errors.SC_NO_OBJECT, new SynergykitError(Errors.SC_NO_OBJECT, Errors.MSG_NO_OBJECT));
            else if(Synergykit.isDebugModeEnabled())
                SynergykitLog.print(Errors.MSG_NO_CALLBACK_LISTENER);

            return;
        }

        //Uri builder
        UriBuilder uriBuilder = new UriBuilder()
                .setResource(Resource.RESOURCE_USERS_PLATFORMS)
                .setRecordId(platform.getId());

        //set config
        config.setUri(uriBuilder.build());
        config.setParallelMode(parallelMode);
        config.setType(platform.getClass());


        //set request
        request.setConfig(config);
        request.setListener(listener);
        request.setObject(platform);

        //execute
        Synergykit.synergylize(request, parallelMode);
    }

    /* Delete platform */
    @Override
    public void deletePlatform(String platformId, DeleteResponseListener listener, boolean parallelMode) {
        SynergykitConfig config = new SynergykitConfig();
        RequestDelete request = new RequestDelete();

        //Object check
        if(platformId == null){
            //Log
            SynergykitLog.print(Errors.MSG_NULL_ARGUMENTS_OR_EMPTY);

            //error callback
            if(listener!=null)
                listener.errorCallback(Errors.SC_NULL_ARGUMENTS_OR_EMPTY, new SynergykitError(Errors.SC_NULL_ARGUMENTS_OR_EMPTY, Errors.MSG_NULL_ARGUMENTS_OR_EMPTY));
            else if(Synergykit.isDebugModeEnabled())
                SynergykitLog.print(Errors.MSG_NO_CALLBACK_LISTENER);

            return;
        }

        //Uri builder
        UriBuilder uriBuilder = new UriBuilder()
                .setResource(Resource.RESOURCE_USERS_PLATFORMS)
                .setRecordId(platformId);

        //set config
        config.setUri(uriBuilder.build());
        config.setParallelMode(parallelMode);

        //set request
        request.setConfig(config);
        request.setListener(listener);

        //execute
        Synergykit.synergylize(request, parallelMode);
    }

    /* Get platform */
    @Override
    public void getPlatform(String platformId, PlatformResponseListener listener, boolean parallelMode) {
        PlatformRequestGet request = new PlatformRequestGet();

        //Object check
        if(platformId == null){
            //Log
            SynergykitLog.print(Errors.MSG_NULL_ARGUMENTS_OR_EMPTY);

            //error callback
            if(listener!=null)
                listener.errorCallback(Errors.SC_NULL_ARGUMENTS_OR_EMPTY, new SynergykitError(Errors.SC_NULL_ARGUMENTS_OR_EMPTY, Errors.MSG_NULL_ARGUMENTS_OR_EMPTY));
            else if(Synergykit.isDebugModeEnabled())
                SynergykitLog.print(Errors.MSG_NO_CALLBACK_LISTENER);

            return;
        }


        //Uri builder
        UriBuilder uriBuilder = new UriBuilder()
                .setResource(Resource.RESOURCE_USERS_PLATFORMS)
                .setRecordId(platformId);

        SynergykitConfig config = new SynergykitConfig();
        config.setType(SynergykitPlatform.class);
        config.setParallelMode(parallelMode);
        config.setUri(uriBuilder.build());

        request.setConfig(config);
        request.setListener(listener);
        Synergykit.synergylize(request, config.isParallelMode());
    }
}
