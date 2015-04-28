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

public class SynergykitPlatform extends SynergykitObject implements Serializable {

    /* Constants */
    protected static final String PLATFORM = "android";


    /* Attributes */
    @Expose
	private String platformName = PLATFORM;
    @Expose
	private String registrationId;

    public SynergykitPlatform(String registrationId){

        if(registrationId==null){
            throw new NullPointerException();
        }

        this.registrationId = registrationId;
    }

	/* Name getter */
	public String getPlatformName() {
		return platformName;
	}

	/* Registration ID getter */
	public String getRegistrationId() {
		return registrationId;
	}

	/* Registration ID setter */
	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}
}
