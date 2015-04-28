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

package com.letsgood.synergykitsdkandroid.request;

import com.letsgood.synergykitsdkandroid.Synergykit;
import com.letsgood.synergykitsdkandroid.errors.Errors;
import com.letsgood.synergykitsdkandroid.listeners.BytesResponseListener;
import com.letsgood.synergykitsdkandroid.log.SynergykitLog;
import com.letsgood.synergykitsdkandroid.config.SynergykitConfig;
import com.letsgood.synergykitsdkandroid.resources.SynergykitError;
import com.letsgood.synergykitsdkandroid.resources.SynergykitResponse;

import org.apache.http.HttpStatus;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;

public class FileRequestDownload extends SynergykitRequest {

	/* Attributes */
	private SynergykitConfig config;
	private BytesResponseListener listener;
	 
	/* Config setter */
	public void setConfig(SynergykitConfig config){
		this.config = config;
	}
	
	/* Listener setter */
	public void setListener(BytesResponseListener listener){
		this.listener =listener;
	}
	

	
	@Override
	protected Object doInBackground(Void... params) {
		ResponseDataHolder dataHolder = null;
		SynergykitResponse response = null;
		ByteArrayOutputStream byteArrayOutputStream;
		byte[] buffer = null;
		int readInt = 0;
		
		
		//do request
		response = getFile(config.getUri(), Synergykit.getSessionToken(),true);
		
		//manage response
		if(response.getStatusCode()>= HttpURLConnection.HTTP_OK && response.getStatusCode()<HttpURLConnection.HTTP_MULT_CHOICE){
			
			byteArrayOutputStream = new ByteArrayOutputStream(); //init output stream
			buffer = new byte[1000];
			
			
			try {
				
				//convert to bytes
				while(response.getInputStream()!=null && (readInt = response.getInputStream().read(buffer)) != -1){
					byteArrayOutputStream.write(buffer,0,readInt);
				}
				
				
				dataHolder = new ResponseDataHolder(); //init
				dataHolder.statusCode = response.getStatusCode(); //set status code
				dataHolder.data = byteArrayOutputStream.toByteArray(); //convert to byte array
				
			} catch (IOException e) {
				e.printStackTrace();
				dataHolder = new ResponseDataHolder(); //init
				dataHolder.statusCode = Errors.SC_UNSPECIFIED_ERROR; //set status code
				dataHolder.errorObject = new SynergykitError(Errors.SC_UNSPECIFIED_ERROR, Errors.MSG_UNSPECIFIED_ERROR);
			}
			
		}else{
			dataHolder = manageResponseToObject(response, config.getType());
		}
		
		
		
		
		return dataHolder;
	}

	@Override
	protected void onPostExecute(Object object) {
		ResponseDataHolder dataHolder = (ResponseDataHolder) object;
		
		//null listener 
		if(listener==null){
			SynergykitLog.print(Errors.MSG_NO_CALLBACK_LISTENER);
			
			return;
		}	
		
		if(dataHolder.statusCode>= HttpStatus.SC_OK && dataHolder.statusCode < HttpStatus.SC_MULTIPLE_CHOICES){
			listener.doneCallback(dataHolder.statusCode, dataHolder.data);
		}else{
			listener.errorCallback(dataHolder.statusCode, dataHolder.errorObject);
		}
		
	}

}
