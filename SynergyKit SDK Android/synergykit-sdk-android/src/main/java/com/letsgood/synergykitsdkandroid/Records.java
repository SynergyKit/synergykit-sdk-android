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

package com.letsgood.synergykitsdkandroid;





import com.letsgood.synergykitsdkandroid.builders.UriBuilder;
import com.letsgood.synergykitsdkandroid.errors.Errors;
import com.letsgood.synergykitsdkandroid.builders.uri.Resource;
import com.letsgood.synergykitsdkandroid.config.SynergykitConfig;
import com.letsgood.synergykitsdkandroid.interfaces.IRecords;
import com.letsgood.synergykitsdkandroid.listeners.DeleteResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.RecordsResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.ResponseListener;
import com.letsgood.synergykitsdkandroid.log.SynergykitLog;
import com.letsgood.synergykitsdkandroid.request.RecordRequestGet;
import com.letsgood.synergykitsdkandroid.request.RecordRequestPatch;
import com.letsgood.synergykitsdkandroid.request.RecordRequestPost;
import com.letsgood.synergykitsdkandroid.request.RecordRequestPut;
import com.letsgood.synergykitsdkandroid.request.RecordsRequestGet;
import com.letsgood.synergykitsdkandroid.request.RequestDelete;
import com.letsgood.synergykitsdkandroid.resources.SynergykitError;
import com.letsgood.synergykitsdkandroid.resources.SynergykitObject;

import java.lang.reflect.Type;

public class Records implements IRecords {
	
	/* Constants */
	private static final int TOP = 100;

	/* Get record*/
	@Override
	public void getRecord(SynergykitConfig config, ResponseListener listener) {
		RecordRequestGet request = new RecordRequestGet();
		request.setConfig(config);
		request.setListener(listener); 
		Synergykit.synergylize(request, config.isParallelMode());
	}
	
	/* Get record */
	@Override
	public void getRecord(String collectionUrl, String recordId, Type type, ResponseListener listener, boolean parallelMode) {
		SynergykitConfig config = Synergykit.getConfig();

		//Uri builder
		UriBuilder uriBuilder = new UriBuilder()
								.setResource(Resource.RESOURCE_DATA)
								.setCollection(collectionUrl)
								.setRecordId(recordId);
		
		//set config
		config.setUri(uriBuilder.build());
		config.setParallelMode(parallelMode);
		config.setType(type);

		//redirect
		this.getRecord(config, listener);
		
		
		
	}

	/* Records getter */
	@Override
	public void getRecords(SynergykitConfig config,	RecordsResponseListener listener) {
		RecordsRequestGet request = new RecordsRequestGet();
		request.setConfig(config);
		request.setListener(listener);
		Synergykit.synergylize(request, config.isParallelMode());
		
	}

	/* Records getter */
	@Override
	public void getRecords(String collectionUrl, Type type,	RecordsResponseListener listener, boolean parallelMode) {
		SynergykitConfig config = new SynergykitConfig();

		//Uri builder
		UriBuilder uriBuilder = new UriBuilder()
								.setResource(Resource.RESOURCE_DATA)
								.setCollection(collectionUrl)
								.setTop(TOP);
		
		//set config
		config.setUri(uriBuilder.build());
		config.setParallelMode(parallelMode);
		config.setType(type);
		

		//redirect
		this.getRecords(config, listener);
		
	}

	/* Record creater */
	@Override
	public void createRecord(String collectionUrl, SynergykitObject object,	ResponseListener listener, boolean parallelMode) {
		SynergykitConfig config = new SynergykitConfig();
		RecordRequestPost request = new RecordRequestPost();

		//Object check
		if(object == null){
			//Log
			SynergykitLog.print(Errors.MSG_NO_OBJECT);
			
			//error callback
			if(listener!=null)
				listener.errorCallback(Errors.SC_NO_OBJECT, new SynergykitError(Errors.SC_NO_OBJECT, Errors.MSG_NO_OBJECT));
			else if(Synergykit.isDebugModeEnabled())
				SynergykitLog.print(Errors.MSG_NO_CALLBACK_LISTENER);
			
			return;
		}
		
		
		
		//Uri builder
		UriBuilder uriBuilder = new UriBuilder()
								.setResource(Resource.RESOURCE_DATA)
								.setCollection(collectionUrl);
		
		//set config
		config.setUri(uriBuilder.build());
		config.setParallelMode(parallelMode);
		config.setType(object.getClass());
		
		
		//set request
		request.setConfig(config);
		request.setListener(listener);
		request.setObject(object);
		
		//execute
		Synergykit.synergylize(request, parallelMode);
		
	}

	/* Record updater */
	@Override
	public void updateRecord(String collectionUrl, SynergykitObject object, ResponseListener listener,	boolean parallelMode) {
		SynergykitConfig config = new SynergykitConfig();
		RecordRequestPut request = new RecordRequestPut();
		
		//Object check
		if(object == null){
			//Log
			SynergykitLog.print(Errors.MSG_NO_OBJECT);
			
			//error callback
			if(listener!=null)
				listener.errorCallback(Errors.SC_NO_OBJECT, new SynergykitError(Errors.SC_NO_OBJECT, Errors.MSG_NO_OBJECT));
			else if(Synergykit.isDebugModeEnabled())
				SynergykitLog.print(Errors.MSG_NO_CALLBACK_LISTENER);
			
			return;
		}
		
		//Uri builder
		UriBuilder uriBuilder = new UriBuilder()
								.setResource(Resource.RESOURCE_DATA)
								.setCollection(collectionUrl)
								.setRecordId(object.getId());
		
		//set config
		config.setUri(uriBuilder.build());
		config.setParallelMode(parallelMode);
		config.setType(object.getClass());
		
		
		//set request
		request.setConfig(config);
		request.setListener(listener);
		request.setObject(object);
		
		//execute
		Synergykit.synergylize(request, parallelMode);
		
	}

    @Override
    public void patchRecord(String collectionUrl, SynergykitObject object, ResponseListener listener, boolean parallelMode) {
        SynergykitConfig config = new SynergykitConfig();
        RecordRequestPatch request = new RecordRequestPatch();

        //Object check
        if(object == null){
            //Log
            SynergykitLog.print(Errors.MSG_NO_OBJECT);

            //error callback
            if(listener!=null)
                listener.errorCallback(Errors.SC_NO_OBJECT, new SynergykitError(Errors.SC_NO_OBJECT, Errors.MSG_NO_OBJECT));
            else if(Synergykit.isDebugModeEnabled())
                SynergykitLog.print(Errors.MSG_NO_CALLBACK_LISTENER);

            return;
        }

        //Uri builder
        UriBuilder uriBuilder = new UriBuilder()
                .setResource(Resource.RESOURCE_DATA)
                .setCollection(collectionUrl)
                .setRecordId(object.getId());

        //set config
        config.setUri(uriBuilder.build());
        config.setParallelMode(parallelMode);
        config.setType(object.getClass());


        //set request
        request.setConfig(config);
        request.setListener(listener);
        request.setObject(object);

        //execute
        Synergykit.synergylize(request, parallelMode);
    }

    /* Record deleter */
	@Override
	public void deleteRecord(String collectionUrl, String recordId,	DeleteResponseListener listener, boolean parallelMode) {
		SynergykitConfig config = new SynergykitConfig();
		RequestDelete request = new RequestDelete();
		
		//Object check
		if(collectionUrl==null || recordId == null){
			//Log
			SynergykitLog.print(Errors.MSG_NULL_ARGUMENTS_OR_EMPTY);
			
			//error callback
			if(listener!=null)
				listener.errorCallback(Errors.SC_NULL_ARGUMENTS_OR_EMPTY, new SynergykitError(Errors.SC_NULL_ARGUMENTS_OR_EMPTY, Errors.MSG_NULL_ARGUMENTS_OR_EMPTY));
			else if(Synergykit.isDebugModeEnabled())
				SynergykitLog.print(Errors.MSG_NO_CALLBACK_LISTENER);
			
			return;
		}
		
		//Uri builder
		UriBuilder uriBuilder = new UriBuilder()
								.setResource(Resource.RESOURCE_DATA)
								.setCollection(collectionUrl)
								.setRecordId(recordId);
		
		//set config
		config.setUri(uriBuilder.build());
		config.setParallelMode(parallelMode);		
		
		//set request
		request.setConfig(config);
		request.setListener(listener);
		
		//execute
		Synergykit.synergylize(request, parallelMode);
		
	}

    /* Add access */
    @Override
    public void addAccess(String collectionUrl, String recordId, String userId, ResponseListener listener, boolean parallelMode) {

    }

    /* Remove access  */
    @Override
    public void removeAccess(String collectionUrl, String recordId, String userId, ResponseListener listener, boolean parallelMode) {

    }

}
