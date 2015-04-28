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

package com.letsgood.synergykitsdkandroid.config;

import com.google.gson.annotations.Expose;
import com.letsgood.synergykitsdkandroid.resources.SynergykitUri;

import java.io.Serializable;
import java.lang.reflect.Type;

public class SynergykitConfig implements Serializable {
    /* Constants */
    public static final String BASE_SYNERGYKIT_URL = "https://%s.api.synergykit.com";
    public static final String API_SYNERGYKIT_URL = BASE_SYNERGYKIT_URL + "/v2.1";
    public static final String SOCKET_SYNERGYKIT_URL = BASE_SYNERGYKIT_URL;


    /* Attributes */
    @Expose
	private boolean parallelMode = false;
    @Expose
	private SynergykitUri synergyKITUri = null;
    @Expose
	private Type type = null;

    /* New instance*/
    public static SynergykitConfig newInstance(){
        return new SynergykitConfig();
    }

	/* Parallel mode getter */
	public boolean isParallelMode() {
		return parallelMode;
	}
	
	/* Parallel mode setter */
	public SynergykitConfig setParallelMode(boolean parallelMode) {
		this.parallelMode = parallelMode;
        return this;
	}
	
	/* Uri getter */
	public SynergykitUri getUri() {
		return synergyKITUri;
	}
	
	/* Uri setter */
	public SynergykitConfig setUri(SynergykitUri synergyKITUri) {

        this.synergyKITUri = synergyKITUri;
        return this;
	}
	
	/* Valid getter */
	public boolean isValid(){
		
		//Uri
		if(synergyKITUri==null || synergyKITUri.toString().length()==0)
			return false;
		
		return true;
	}

	/* Type getter */
	public Type getType() {
		return type;
	}

	/* Type setter */
	public SynergykitConfig setType(Type type) {
		this.type = type;
        return this;
	}
}
