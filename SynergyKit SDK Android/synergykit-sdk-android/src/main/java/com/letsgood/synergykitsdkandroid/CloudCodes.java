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
import com.letsgood.synergykitsdkandroid.interfaces.ICloudCodes;
import com.letsgood.synergykitsdkandroid.listeners.ResponseListener;
import com.letsgood.synergykitsdkandroid.log.SynergykitLog;
import com.letsgood.synergykitsdkandroid.request.RecordRequestPost;
import com.letsgood.synergykitsdkandroid.config.SynergykitConfig;
import com.letsgood.synergykitsdkandroid.resources.SynergykitCloudCode;
import com.letsgood.synergykitsdkandroid.resources.SynergykitError;
import com.letsgood.synergykitsdkandroid.resources.SynergykitUri;

import java.lang.reflect.Type;

/**
 * Created by Marek on 1/14/15.
 */
public class CloudCodes implements ICloudCodes {

    @Override
    public void invokeCloudCode(SynergykitCloudCode cloudCodeObject, Type type, ResponseListener listener, boolean parallelMode) {
            RecordRequestPost request = new RecordRequestPost();
            SynergykitConfig config = null;
            SynergykitUri uri = null;

            //Object check
            if(cloudCodeObject==null || type == null){
                //Log
                SynergykitLog.print(Errors.MSG_NO_OBJECT);

                //error callback
                if(listener!=null)
                    listener.errorCallback(Errors.SC_NO_OBJECT, new SynergykitError(Errors.SC_NO_OBJECT, Errors.MSG_NO_OBJECT));
                else if(Synergykit.isDebugModeEnabled())
                    SynergykitLog.print(Errors.MSG_NO_CALLBACK_LISTENER);

                return;
            }

            uri = UriBuilder.newInstance()
                        .setResource(Resource.RESOURCE_FUNCTIONS)
                        .setFunctionId(cloudCodeObject.getCloudCodeName())
                        .build();

            config = SynergykitConfig.newInstance()
                                        .setParallelMode(parallelMode)
                                        .setType(type)
                                        .setUri(uri);

            //set request
            request.setConfig(config);
            request.setListener(listener);
            request.setObject(cloudCodeObject);

            //execute
            Synergykit.synergylize(request, config.isParallelMode());


    }


}
