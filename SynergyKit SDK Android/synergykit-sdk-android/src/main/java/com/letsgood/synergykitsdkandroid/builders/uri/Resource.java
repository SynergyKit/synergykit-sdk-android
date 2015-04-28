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

package com.letsgood.synergykitsdkandroid.builders.uri;

import com.letsgood.synergykitsdkandroid.errors.Errors;
import com.letsgood.synergykitsdkandroid.log.SynergykitLog;

public class Resource {
	
	/* Constructor */
	public static final String RESOURCE_COLLECTIONS = "collections";
	public static final String RESOURCE_DATA = "data";
    public static final String RESOURCE_BATCH = "batch";
	public static final String RESOURCE_FILES = "files";
	public static final String RESOURCE_LOGS = "logs";
	public static final String RESOURCE_USERS = "users";
    public static final String RESOURCE_USERS_PLATFORMS = "users/me/platforms";
    public static final String RESOURCE_USER_LOGIN = "users/login";
	public static final String RESOURCE_EMAIL = "mail";
	public static final String RESOURCE_NOTIFICATION = "users/notification";
    public static final String RESOURCE_FUNCTIONS = "functions";
    public static final String RESOURCE_USERS_ROLES = "users/%s/roles";

	/* Attributes */
	private String resource = null;
	
	/* Resource setter */
	public void setResource(String resource){	
		this.resource = resource;		
	}
	
	
	/* Resource getter */
	public String getResource(){
		
		//null check
		if(resource==null || resource.length()==0){
			SynergykitLog.print(Errors.MSG_NULL_ARGUMENTS_OR_EMPTY);
		}
		
		return resource;
	}
}

