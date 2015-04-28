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

package com.letsgood.synergykitsdkandroid.requestmethods;



import android.util.Base64;

import com.letsgood.synergykitsdkandroid.Synergykit;
import com.letsgood.synergykitsdkandroid.addons.GsonWrapper;
import com.letsgood.synergykitsdkandroid.errors.Errors;
import com.letsgood.synergykitsdkandroid.log.SynergykitLog;
import com.letsgood.synergykitsdkandroid.resources.SynergykitUri;

import org.apache.http.HttpStatus;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Put extends RequestMethod{
	
	/* Constants */
	protected static final String REQUEST_METHOD = "PUT";
	
	/* Attributes */
	private Object object = null;
	private DataOutputStream dataOutputStream = null;
	
	/* Constructor */
	public Put(final SynergykitUri uri,final Object object, final String sessionToken, final boolean sessionTokenRequired) {
        super(uri,sessionToken,sessionTokenRequired);
        this.object = object;
    }
	
	/* Execute */
	@Override
	public BufferedReader execute() {
		String jSon = null;
		String uri = null;
		
		//init check
		if(!Synergykit.isInit()){
			SynergykitLog.print(Errors.MSG_SK_NOT_INITIALIZED);
			
			statusCode = Errors.SC_SK_NOT_INITIALIZED;
			return null;
		}
		
		//URI check
		uri = getUri().toString();
		
		if(uri==null){
			statusCode = Errors.SC_URI_NOT_VALID;
			return null;
		}


        //session token check
        if(sessionTokenRequired && sessionToken==null){
            statusCode = Errors.SC_NO_SESSION_TOKEN;
            return null;
        }
			
		try {
			url = new URL(uri); // init url
			
			httpURLConnection = (HttpURLConnection) url.openConnection(); //open connection
			httpURLConnection.setConnectTimeout(CONNECT_TIMEOUT); //set connect timeout
			httpURLConnection.setReadTimeout(READ_TIMEOUT); //set read timeout
			httpURLConnection.setRequestMethod(REQUEST_METHOD); //set method
			httpURLConnection.addRequestProperty(PROPERTY_USER_AGENT, PROPERTY_USER_AGENT_VALUE); //set property accept
			httpURLConnection.addRequestProperty(PROPERTY_ACCEPT, ACCEPT_APPLICATION_VALUE); //set property accept
            httpURLConnection.addRequestProperty(PROPERTY_CONTENT_TYPE,ACCEPT_APPLICATION_VALUE);
            httpURLConnection.setDoInput(true);
			httpURLConnection.setDoOutput(true);
			
			httpURLConnection.addRequestProperty(PROPERTY_AUTHORIZATION, "Basic " 
												 + Base64.encodeToString(
                    (Synergykit.getTenant() + ":" + Synergykit.getApplicationKey()).getBytes(),
                    Base64.NO_WRAP)); //set authorization

            if(Synergykit.getSessionToken()!=null)
                httpURLConnection.addRequestProperty(PROPERTY_SESSION_TOKEN, Synergykit.getSessionToken());

			httpURLConnection.connect();
			
			
			
			//write data
			if(object!=null){
				jSon = GsonWrapper.getGson().toJson(object);
				dataOutputStream = new DataOutputStream( httpURLConnection.getOutputStream());
				dataOutputStream.write(jSon.getBytes(CHARSET));
				dataOutputStream.flush();
				dataOutputStream.close();
				
				
			}
			
			statusCode = httpURLConnection.getResponseCode(); //get status code

			//read stream
			if(statusCode>=HttpURLConnection.HTTP_OK && statusCode<HttpURLConnection.HTTP_MULT_CHOICE){
				return readStream(httpURLConnection.getInputStream());
			}else{
				return readStream(httpURLConnection.getErrorStream());
			}
			
		} catch (Exception e) {
            statusCode = HttpStatus.SC_SERVICE_UNAVAILABLE;
			e.printStackTrace();
			return null;
		}
		
	}
}
