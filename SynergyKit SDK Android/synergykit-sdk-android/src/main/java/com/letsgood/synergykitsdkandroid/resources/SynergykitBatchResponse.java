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
import com.letsgood.synergykitsdkandroid.addons.GsonWrapper;

import java.io.Serializable;

/**
 * Created by Letsgood.com - Pavel Stambrecht on 23. 2. 2015.
 */
public class SynergykitBatchResponse<T> extends SynergykitObject implements Serializable {

    /*Attributes*/
    @Expose
    private int id;

    @Expose
    private String status;

    @Expose
    private int statusCode;

    @Expose
    private T body;

    /* Status Code getter */
    public int getStatusCode() {
        return statusCode;
    }

    /* Status Code setter */
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    /* Body getter */
    public T getBody() {
        return body;
    }

    /* Body setter */
    public void setBody(T body) {
        this.body = body;
    }

    /* Status getter */
    public String getStatus() {
        return status;
    }

    /* Status setter */
    public void setStatus(String status) {
        this.status = status;
    }

    /* Id getter */
    public int getBatchItemId() {
        return id;
    }

    /* Id setter */
    public void setBatchItemId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        String tmpStatus, tmpBody;

        //status null check
        if(status==null)
            tmpStatus=new String("null");
        else
            tmpStatus=status;

        //body null check
        if(body==null)
            tmpBody=new String("null");
        else
            tmpBody = GsonWrapper.getGson().toJson(body);

        return "Id: " + Integer.toString(id) + ", StatusCode: " + Integer.toString(statusCode) +  ", Status: " + tmpStatus +  ", Body: " + tmpBody;
    }
}
