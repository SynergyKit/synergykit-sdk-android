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

public class SynergykitError implements Serializable {
	
	/* Attributes */
    @Expose
	private int statusCode;
    @Expose
    private String message;

    
    /* Constructor */
    public SynergykitError(int statusCode, String message){
    	
    	this.statusCode=statusCode; //set status code
    	
    	//set message
    	if(message!=null)
    		this.message = message;
    	else
    		this.message=new String();    	
    }
    
    /* Constructor */
    public SynergykitError(){
    	this(0, new String());
    }
    
    /* Status code getter */
    public int getStatusCode() {
        return statusCode;
    }

    /* Status code setter */
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
    
    /* Status code in string getter */
    public String getStatusCodeString(){
    	return Integer.toString(statusCode);
    }

    /* Message getter */
    public String getMessage() {
        return message;
    }

    /* Message setter */
    public void setMessage(String message) {
        this.message = message;
    }

    /* To String */
    public String toString(){
    	return "Status code: " + statusCode + ", Message: " + message;
    }
}
