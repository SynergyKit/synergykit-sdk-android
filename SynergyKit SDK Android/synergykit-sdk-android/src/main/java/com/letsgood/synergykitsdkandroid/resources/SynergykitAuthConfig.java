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

import java.io.Serializable;

public class SynergykitAuthConfig implements Serializable {
	
	/* Attributes */
	private String tenant = null;
	private String applicationKey = null;
    private String token = null;
    private SynergykitUser user = null;
	
	/* Tenant getter */
	public String getTenant() {
		return tenant;
	}

	/* Tenant setter */
	public void setTenant(String tenant) {
		this.tenant = tenant;
	}

	/* Application key getter */
	public String getApplicationKey() {
		return applicationKey;
	}

	/* Application key setter */
	public void setApplicationKey(String applicationKey) {
		this.applicationKey = applicationKey;
	}

    /* Token getter */
    public String getToken() {
        return token;
    }

    /* Token setter */
    public void setToken(String token) {
        this.token = token;
    }

    /* User getter */
    public SynergykitUser getUser() {
        return user;
    }

    /* User setter */
    public void setUser(SynergykitUser user) {
        this.user = user;
    }
}
