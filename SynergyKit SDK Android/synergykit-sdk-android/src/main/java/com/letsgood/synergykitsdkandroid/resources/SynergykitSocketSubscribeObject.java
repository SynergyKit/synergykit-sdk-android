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

package com.letsgood.synergykitsdkandroid.resources;


import com.google.gson.annotations.Expose;
import com.letsgood.synergykitsdkandroid.Synergykit;

/**
 * Created by Letsgood.com - Pavel Stambrecht on 11. 3. 2015.
 */
public class SynergykitSocketSubscribeObject {


    /* Attributes */
    @Expose
    private String tenant;
    @Expose
    private String key;
    @Expose
    private String token;
    @Expose
    private String collectionName;
    @Expose
    private String eventName;
    @Expose
    private SynergykitSocketFilter filter;


    public SynergykitSocketSubscribeObject(){
        this.tenant= Synergykit.getTenant();
        this.key= Synergykit.getApplicationKey();
        this.token = Synergykit.getSessionToken();

    }


    public String getTenant() {
        return tenant;
    }

    public String getKey() {
        return key;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collection) {
        this.collectionName = collection;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    /* Filter setter*/
    public void setFilter(SynergykitSocketFilter filter){
       this.filter = filter;
    }

    /* Filter getter */
    public SynergykitSocketFilter getFilter(){
        return filter;
    }
}
