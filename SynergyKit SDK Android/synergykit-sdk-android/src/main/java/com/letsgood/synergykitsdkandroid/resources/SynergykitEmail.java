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

public class SynergykitEmail extends SynergykitObject implements Serializable {
	
	/* Attributes */
    @Expose
	protected String email;
    @Expose
	protected String subject;
    @Expose
    protected String from;

    /* New Instance */
    public static SynergykitEmail newInstace(){
        return new SynergykitEmail();
    }

	/* Email getter */
	public String getEmail() {
		return email;
	}

	/* Email setter */
	public SynergykitEmail setEmail(String email) {
		this.email = email;
        return this;
	}

	/* Subject getter */
	public String getSubject() {
		return subject;
	}

	/* Subject setter */
	public SynergykitEmail setSubject(String subject) {
		this.subject = subject;
        return this;
	}

    public String getFrom() {
        return from;
    }

    public SynergykitEmail setFrom(String from) {
        this.from = from;
        return this;
    }
}