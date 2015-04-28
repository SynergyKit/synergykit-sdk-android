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


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.letsgood.synergykitsdkandroid.builders.UriBuilder;
import com.letsgood.synergykitsdkandroid.errors.Errors;
import com.letsgood.synergykitsdkandroid.builders.uri.Resource;
import com.letsgood.synergykitsdkandroid.config.SynergykitConfig;
import com.letsgood.synergykitsdkandroid.interfaces.IFiles;
import com.letsgood.synergykitsdkandroid.listeners.BitmapResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.BytesResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.DeleteResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.FileResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.FilesResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.ResponseListener;
import com.letsgood.synergykitsdkandroid.log.SynergykitLog;
import com.letsgood.synergykitsdkandroid.request.FileRequestDownload;
import com.letsgood.synergykitsdkandroid.request.FileRequestGet;
import com.letsgood.synergykitsdkandroid.request.FileRequestPatch;
import com.letsgood.synergykitsdkandroid.request.FileRequestPost;
import com.letsgood.synergykitsdkandroid.request.FileRequestPut;
import com.letsgood.synergykitsdkandroid.request.FilesRequestGet;
import com.letsgood.synergykitsdkandroid.request.RequestDelete;
import com.letsgood.synergykitsdkandroid.resources.SynergykitError;
import com.letsgood.synergykitsdkandroid.resources.SynergykitFile;
import com.letsgood.synergykitsdkandroid.resources.SynergykitUri;

import java.io.ByteArrayOutputStream;

public class Files implements IFiles {

    /* Constants */
    private static final int TOP = 100;


    /* Get file */
    @Override
    public void getFile(SynergykitConfig config, final FileResponseListener listener) {
        FileRequestGet request = new FileRequestGet();
        request.setConfig(config);
        request.setListener(listener);
        Synergykit.synergylize(request, config.isParallelMode());
    }

    /* Get file */
    @Override
    public void getFile(String fileId,final FileResponseListener listener, boolean parallelMode) {

        SynergykitUri synergyKitUri = UriBuilder
                                        .newInstance()
                                        .setResource(Resource.RESOURCE_FILES)
                                        .setRecordId(fileId)
                                        .build();

        SynergykitConfig config = SynergykitConfig
                                        .newInstance()
                                        .setParallelMode(parallelMode)
                                        .setType(SynergykitFile.class)
                                        .setUri(synergyKitUri);

        getFile(config,listener);
    }

    /* Get files */
    @Override
    public void getFiles(SynergykitConfig config,final FilesResponseListener listener) {
        FilesRequestGet request = new FilesRequestGet();
        request.setConfig(config);
        request.setListener(listener);
        Synergykit.synergylize(request, config.isParallelMode());
    }

    /* Get files */
    @Override
    public void getFiles(FilesResponseListener listener, boolean parallelMode) {

        SynergykitUri synergyKitUri = UriBuilder
                                        .newInstance()
                                        .setResource(Resource.RESOURCE_FILES)
                                        .setTop(TOP)
                                        .build();

        SynergykitConfig config = SynergykitConfig
                                        .newInstance()
                                        .setParallelMode(parallelMode)
                                        .setType(SynergykitFile[].class)
                                        .setUri(synergyKitUri);

        getFiles(config, listener);
    }

    /* Update file */
    @Override
    public void updateFile(SynergykitFile file, final FileResponseListener listener, boolean parallelMode) {
        SynergykitConfig config = new SynergykitConfig();
        FileRequestPut request = new FileRequestPut();

        //Object check
        if(file == null){
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
                .setResource(Resource.RESOURCE_FILES)
                .setRecordId(file.getId());

        //set config
        config.setUri(uriBuilder.build());
        config.setParallelMode(parallelMode);
        config.setType(file.getClass());


        //set request
        request.setConfig(config);
        request.setListener(listener);
        request.setObject(file);

        //execute
        Synergykit.synergylize(request, parallelMode);
    }

    /* Patch file */
    @Override
    public void patchFile(SynergykitFile file, final FileResponseListener listener, boolean parallelMode) {
        SynergykitConfig config = new SynergykitConfig();
        FileRequestPatch request = new FileRequestPatch();

        //Object check
        if(file == null){
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
                .setResource(Resource.RESOURCE_FILES)
                .setRecordId(file.getId());

        //set config
        config.setUri(uriBuilder.build());
        config.setParallelMode(parallelMode);
        config.setType(file.getClass());


        //set request
        request.setConfig(config);
        request.setListener(listener);
        request.setObject(file);

        //execute
        Synergykit.synergylize(request, parallelMode);
    }

    /* Delete file */
    @Override
    public void deleteFile(String fileId, final DeleteResponseListener listener, boolean parallelMode) {
        SynergykitConfig config = new SynergykitConfig();
        RequestDelete request = new RequestDelete();


        //Object check
        if(fileId == null){
            //Log
            SynergykitLog.print(Errors.MSG_NO_OBJECT);

            //error callback
            if(listener!=null)
                listener.errorCallback(Errors.SC_NO_OBJECT, new SynergykitError(Errors.SC_NO_OBJECT, Errors.MSG_NO_OBJECT));
            else if(Synergykit.isDebugModeEnabled())
                SynergykitLog.print(Errors.MSG_NO_CALLBACK_LISTENER);

            return;
        }


        SynergykitUri synergyKitUri = UriBuilder.newInstance()
                                                .setResource(Resource.RESOURCE_FILES)
                                                .setRecordId(fileId)
                                                .build();


        config = SynergykitConfig.newInstance()
                                          .setType(SynergykitFile.class)
                                           .setUri(synergyKitUri)
                                           .setParallelMode(parallelMode);


        //set request
        request.setConfig(config);
        request.setListener(listener);

        //execute
        Synergykit.synergylize(request, parallelMode);
    }


    /* Create file */
	@Override
	public void createFile(byte[] data, FileResponseListener listener) {
		SynergykitConfig config = new SynergykitConfig();
		FileRequestPost request = new FileRequestPost();


        //Object check
        if(data == null){
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
								.setResource(Resource.RESOURCE_FILES);
		
		//set config
		config.setUri(uriBuilder.build());
		config.setParallelMode(true);
		config.setType(SynergykitFile.class);
		
		
		//set request
		request.setConfig(config);
		request.setListener(listener);
		request.setData(data);
		
		//execute
		Synergykit.synergylize(request, true);
		
	}

	/* Upload bitmap */
	@Override
	public void createFile(Bitmap bitmap, FileResponseListener listener) {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		
		//Object check
		if(bitmap == null){
			//Log
			SynergykitLog.print(Errors.MSG_NO_OBJECT);
			
			//error callback
			if(listener!=null)
				listener.errorCallback(Errors.SC_NO_OBJECT, new SynergykitError(Errors.SC_NO_OBJECT, Errors.MSG_NO_OBJECT));
			else if(Synergykit.isDebugModeEnabled())
				SynergykitLog.print(Errors.MSG_NO_CALLBACK_LISTENER);
			
			return;
		}
		
		bitmap.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);

		
		this.createFile(byteArrayOutputStream.toByteArray(), listener); //create file
		
		
	}
	
	/* Dowload Bitmap */
	@Override
	public void downloadBitmap(String uri, final BitmapResponseListener listener) {
		
		//Download file
		Synergykit.downloadFile(uri, new BytesResponseListener() {

            //Error callback
            @Override
            public void errorCallback(int statusCode, SynergykitError errorObject) {
                listener.errorCallback(statusCode, errorObject);
            }

            //Done callback
            @Override
            public void doneCallback(int statusCode, byte[] data) {
                Bitmap bitmap = null;

                //build bitmap
                if (data != null) {
                    bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                }


                listener.doneCallback(statusCode, bitmap);
            }
        });
		
	}

	/* Download file */
	@Override
	public void downloadFile(String uri, BytesResponseListener listener) {
		FileRequestDownload request = new FileRequestDownload();
		SynergykitConfig config = Synergykit.getConfig();
				
		
		//Uri builder
		SynergykitUri synergyKITUri = new SynergykitUri(uri);
		
		//set config
		config.setUri(synergyKITUri);
		config.setParallelMode(true);

		request.setConfig(config);
		request.setListener(listener); 
		Synergykit.synergylize(request, config.isParallelMode());
	}

    @Override
    public void addAccess(String collectionUrl, String recordId, String userId, ResponseListener listener, boolean parallelMode) {

    }

    @Override
    public void removeAccess(String collectionUrl, String recordId, String userId, ResponseListener listener, boolean parallelMode) {

    }


}
