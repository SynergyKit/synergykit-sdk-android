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

import java.io.Serializable;
import java.util.LinkedList;

public class SynergykitNotification extends SynergykitObject implements Serializable {


	/* Attributes */
    @Expose
	private LinkedList<String> userIds = new LinkedList<String>();
    @Expose
	private String alert = null;
    @Expose
    private String payload = null;


    /* New instance */
    public static SynergykitNotification newInstance(){
        return new SynergykitNotification();
    }

	/* Alert getter */
	public String getAlert() {
		return alert;
	}

	/* Alert setter */
	public SynergykitNotification setAlert(String alert) {
		this.alert = alert;
        return this;
	}

	/* User ids getter */
	public LinkedList<String> getUserIds() {
		return userIds;
	}

	/* User ids setter */
	public SynergykitNotification setUserIds(LinkedList<String> userIds) {
		this.userIds = userIds;

		// if null
		if (this.userIds == null) {
			this.userIds = new LinkedList<String>();
		}

        return this;
	}

	/* Add user id */
	public SynergykitNotification addUserId(String userId){
		if(userId!=null && !this.userIds.contains(userId))
			this.userIds.add(userId);

        return this;
	}

	/* Remove user id */
	public void removeUserId(String userId){
		if(userId!=null)
			this.userIds.remove(userId);
	}

    /* Payload getter */
    public String getPayload() {
        return payload;
    }

    /* Payload setter */
    public SynergykitNotification setPayload(String payload) {
        this.payload = payload;
        return this;
    }
}
