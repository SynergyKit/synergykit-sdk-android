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

package com.letsgood.synergykitsdkandroid.builders;


import android.net.ParseException;

import com.letsgood.synergykitsdkandroid.addons.GsonWrapper;
import com.letsgood.synergykitsdkandroid.errors.Errors;
import com.letsgood.synergykitsdkandroid.resources.SynergykitError;
import com.letsgood.synergykitsdkandroid.resources.SynergykitObject;

import org.apache.http.HttpStatus;

import java.io.BufferedReader;
import java.lang.reflect.Type;

public class ResultObjectBuilder {
	
	/* Build base object */
	public static SynergykitObject buildObject(int statusCode,BufferedReader data,Type type){
		
		// Param check
		if(data == null || statusCode!= HttpStatus.SC_OK)
			return null;
		
		
		// Build object
		try {	
			return (SynergykitObject) GsonWrapper.getGson().fromJson(data, type);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	/* Build base objects */
	public static SynergykitObject[] buildObjects(int statusCode, BufferedReader data, Type type){
		SynergykitObject[] baseObjects;
		
		// Param check
		if(data == null || statusCode!=HttpStatus.SC_OK)
			return null;
		
		// Build objects
		try {
			baseObjects = (SynergykitObject[]) GsonWrapper.getGson().fromJson(data, type);
			
			return baseObjects;
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/* Build error object */
	public static SynergykitError buildError(int statusCode, BufferedReader data){
		SynergykitError errorObject;
		
		// Param check
		if(data == null)
			return null;
		
		
		// Build error
		try {
			errorObject =  (SynergykitError) GsonWrapper.getGson().fromJson(data, SynergykitError.class);
			errorObject.setStatusCode(statusCode);
			return errorObject;
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/* Build error object */
	public static SynergykitError buildError(int statusCode){
		SynergykitError error = new SynergykitError();
		
		error.setStatusCode(statusCode); //set status code
		
		switch (statusCode) {
		
		case Errors.SC_NO_OBJECT:
			error.setMessage(Errors.MSG_NO_OBJECT);
			break;
			
		case Errors.SC_URI_NOT_VALID:
			error.setMessage(Errors.MSG_URI_NOT_VALID);
			break;

        case Errors.SC_NO_SESSION_TOKEN:
            error.setMessage(Errors.MSG_NO_SESSION_TOKEN);
            break;
			
		case Errors.SC_SK_NOT_INITIALIZED:
			error.setMessage(Errors.MSG_SK_NOT_INITIALIZED);
			break;

        case HttpStatus.SC_SERVICE_UNAVAILABLE:
            error.setMessage(Errors.MSG_SERVICE_UNAVAILABLE);
            break;

		default:
			error.setMessage(Errors.MSG_UNSPECIFIED_ERROR);
			break;
		}


		
		
		return error;
	}
}
