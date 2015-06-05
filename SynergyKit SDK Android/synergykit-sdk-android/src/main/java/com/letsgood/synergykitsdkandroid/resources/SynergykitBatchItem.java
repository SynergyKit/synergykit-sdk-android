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

/**
 * Created by Letsgood.com - Pavel Stambrecht on 23. 2. 2015.
 */
public class SynergykitBatchItem implements Serializable {

    /* Attributes */
    @Expose
    private String method;
    @Expose
    private String endpoint;
    @Expose
    private int id;

    @Expose
    private Object body;


    /* Constructor */
    public SynergykitBatchItem(String method, SynergykitEndpoint endpoint){
        this(0,method,endpoint,null);
    }

    public SynergykitBatchItem(String method, SynergykitEndpoint endpoint, Object body){
        this(0,method,endpoint,body);
    }

    public SynergykitBatchItem(int id, String method, SynergykitEndpoint endpoint){
        this(id,method,endpoint,null);
    }

    public SynergykitBatchItem(int id, String method, SynergykitEndpoint endpoint, Object body){
        this.method = method;

        if(endpoint==null){
            throw new NullPointerException();
        }

        this.endpoint = endpoint.toString();
        this.id = id;
        this.body = body;
    }

    /* Method getter */
    public String getMethod() {
        return method;
    }

    /* Method setter */
    public void setMethod(String method) {
        this.method = method;
    }

    /* Path getter */
    public String getEndpoint() {
        return this.endpoint;
    }

    /* Path setter */
    public void setEndpoint(SynergykitEndpoint endpoint) {
        if(endpoint==null){
            throw new NullPointerException();
        }

        this.endpoint = endpoint.toString();
    }

    /* Id getter */
    public int getId() {
        return id;
    }

    /* Id setter */
    public void setId(int id) {
        this.id = id;
    }

    /* Body getter */
    public Object getBody() {
        return body;
    }

    /* Body setter */
    public void setBody(Object body) {
        this.body = body;
    }

}
