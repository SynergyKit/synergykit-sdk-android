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

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.google.android.gms.internal.ne;
import com.letsgood.sampleapp.model.DemoObject;
import com.letsgood.synergykitsdkandroid.Synergykit;
import com.letsgood.synergykitsdkandroid.builders.UriBuilder;
import com.letsgood.synergykitsdkandroid.builders.uri.Filter;
import com.letsgood.synergykitsdkandroid.builders.uri.InLineCount;
import com.letsgood.synergykitsdkandroid.builders.uri.OrderBy;
import com.letsgood.synergykitsdkandroid.builders.uri.Resource;
import com.letsgood.synergykitsdkandroid.builders.uri.Select;
import com.letsgood.synergykitsdkandroid.builders.uri.Skip;
import com.letsgood.synergykitsdkandroid.builders.uri.Top;
import com.letsgood.synergykitsdkandroid.config.SynergykitConfig;
import com.letsgood.synergykitsdkandroid.listeners.BatchResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.DeleteResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.EmailResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.FileResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.FilesResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.NotificationResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.PlatformResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.RecordsResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.ResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.UserResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.UsersResponseListener;
import com.letsgood.synergykitsdkandroid.log.SynergykitLog;
import com.letsgood.synergykitsdkandroid.resources.SynergykitBatchItem;
import com.letsgood.synergykitsdkandroid.resources.SynergykitBatchResponse;
import com.letsgood.synergykitsdkandroid.resources.SynergykitCloudCode;
import com.letsgood.synergykitsdkandroid.resources.SynergykitEmail;
import com.letsgood.synergykitsdkandroid.resources.SynergykitEndpoint;
import com.letsgood.synergykitsdkandroid.resources.SynergykitError;
import com.letsgood.synergykitsdkandroid.resources.SynergykitFile;
import com.letsgood.synergykitsdkandroid.resources.SynergykitNotification;
import com.letsgood.synergykitsdkandroid.resources.SynergykitObject;
import com.letsgood.synergykitsdkandroid.resources.SynergykitPlatform;
import com.letsgood.synergykitsdkandroid.resources.SynergykitUri;
import com.letsgood.synergykitsdkandroid.resources.SynergykitUser;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import java.util.Arrays;
import java.util.LinkedList;

import io.fabric.sdk.android.Fabric;


/**
 * Created by Marek on 1/13/15.
 */
public class SynergyKitApplication extends Application {

    /* Constants */
    private static final String APPLICATION_TENANT = "synergykit-sample-app"; //Application tenant from SynergyKit
    private static final String APPLICATION_KEY = "7cbb9eed-17dd-4f75-a7bd-c92f2f6faef9"; //Application key from SynergyKit


    @Override
    public void onCreate() {
        super.onCreate();


        if(!Synergykit.isInit()) {
            Synergykit.init(APPLICATION_TENANT, APPLICATION_KEY); //SynergyKit initialization
            Synergykit.setDebugModeEnabled(BuildConfig.DEBUG);    //Enable debug mode (Default is disabled)
        }
    }
}
