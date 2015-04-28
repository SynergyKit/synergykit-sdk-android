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



import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.annotation.Nullable;

import com.letsgood.synergykitsdkandroid.errors.Errors;
import com.letsgood.synergykitsdkandroid.cache.Cache;
import com.letsgood.synergykitsdkandroid.config.SynergykitConfig;
import com.letsgood.synergykitsdkandroid.interfaces.IAuthorization;
import com.letsgood.synergykitsdkandroid.interfaces.IBatches;
import com.letsgood.synergykitsdkandroid.interfaces.ICache;
import com.letsgood.synergykitsdkandroid.interfaces.ICloudCodes;
import com.letsgood.synergykitsdkandroid.interfaces.IFiles;
import com.letsgood.synergykitsdkandroid.interfaces.INotifications;
import com.letsgood.synergykitsdkandroid.interfaces.IPlatforms;
import com.letsgood.synergykitsdkandroid.interfaces.IRecords;
import com.letsgood.synergykitsdkandroid.interfaces.ISocket;
import com.letsgood.synergykitsdkandroid.interfaces.ISynergyKitSdk;
import com.letsgood.synergykitsdkandroid.interfaces.IUsers;
import com.letsgood.synergykitsdkandroid.listeners.BatchResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.BitmapResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.BytesResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.DeleteResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.EmailResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.FileResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.FilesResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.NotificationResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.PlatformResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.PlatformsResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.RecordsResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.ResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.SocketEventListener;
import com.letsgood.synergykitsdkandroid.listeners.SocketStateListener;
import com.letsgood.synergykitsdkandroid.listeners.UserResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.UsersResponseListener;
import com.letsgood.synergykitsdkandroid.log.SynergykitLog;
import com.letsgood.synergykitsdkandroid.request.SynergykitRequest;
import com.letsgood.synergykitsdkandroid.resources.SynergykitAnonymousAuthData;
import com.letsgood.synergykitsdkandroid.resources.SynergykitAuthConfig;
import com.letsgood.synergykitsdkandroid.resources.SynergykitBatchItem;
import com.letsgood.synergykitsdkandroid.resources.SynergykitCloudCode;
import com.letsgood.synergykitsdkandroid.resources.SynergykitEmail;
import com.letsgood.synergykitsdkandroid.resources.SynergykitFacebookAuthData;
import com.letsgood.synergykitsdkandroid.resources.SynergykitFile;
import com.letsgood.synergykitsdkandroid.resources.SynergykitGoogleAuthData;
import com.letsgood.synergykitsdkandroid.resources.SynergykitNotification;
import com.letsgood.synergykitsdkandroid.resources.SynergykitObject;
import com.letsgood.synergykitsdkandroid.resources.SynergykitPlatform;
import com.letsgood.synergykitsdkandroid.resources.SynergykitSocketFilter;
import com.letsgood.synergykitsdkandroid.resources.SynergykitTwitterAuthData;
import com.letsgood.synergykitsdkandroid.resources.SynergykitUser;

import java.lang.reflect.Type;
import java.util.LinkedList;

public class SynergykitSdk implements ISynergyKitSdk, IRecords, IUsers, INotifications, ICache, IAuthorization, IFiles, ICloudCodes, IBatches,ISocket,IPlatforms{
	
	/* Attributes */
	private static SynergykitSdk instance = null;
	private SynergykitAuthConfig authConfig = new SynergykitAuthConfig();
	private SynergykitConfig config = new SynergykitConfig();
	private IRecords records = new Records();
	private IUsers users = new Users();
	private INotifications notifications = new Notifications();
	private IAuthorization authorization = new Authorization();
	private ICache cache = new Cache();
	private IFiles files = new Files();
    private ICloudCodes cloudCode = new CloudCodes();
    private IBatches batches = new Batches();
	private ISocket socket = new Socket();
    private IPlatforms platforms = new Platforms();

	//---------------------------------------------------------------------------------------
	/* Instance static getter */
	public static SynergykitSdk getInstance(){
		
		if(instance == null )
			instance = new SynergykitSdk();
		
		return instance;
	}
	
	//---------------------------------------------------------------------------------------
	/* Init */
	@Override
	public void init(String tenant, String applicationKey) {
		this.setTenant(tenant);
		this.setApplicationKey(applicationKey);
        this.initSocket();
	}

	/* Reset */
	@Override
	public void reset() {
		authConfig = new SynergykitAuthConfig();
	}

	
	/* Tenant setter */
	@Override
	public void setTenant(String tenant) {
		authConfig.setTenant(tenant);
	}

	/* Tenant getter */
	@Override
	public String getTenant() {
		return authConfig.getTenant();
	}

	/* Application key setter */
	@Override
	public void setApplicationKey(String applicationKey) {		
		authConfig.setApplicationKey(applicationKey);
		
	}

	/* Application key getter */
	@Override
	public String getApplicationKey() {
		return authConfig.getApplicationKey();
	}

    /* Token getter */
    @Override
    public String getToken() {
        return authConfig.getToken();
    }

    /* Token setter */
    @Override
    public void setToken(String token) {
        authConfig.setToken(token);
    }

    /* Logged user setter */
    @Override
    public SynergykitUser getLoggedUser() {
        return authConfig.getUser();
    }

    /* Logged user getter */
    @Override
    public void setLoggedUser(SynergykitUser user) {
        authConfig.setUser(user);
    }

    /* Is init */
	@Override
	public boolean isInit() {
		
		if(authConfig.getApplicationKey() == null 
		   || authConfig.getApplicationKey().isEmpty() == true
		   || authConfig.getTenant()==null
		   || authConfig.getTenant().isEmpty() ==true)
		   
		   return false;
		   
		   
		return true;
	}
	
	/*Config setter */ 
	@Override
	public void setConfig(SynergykitConfig config) {
		this.config = config;
	}

	/* Config getter */
	@Override
	public SynergykitConfig getConfig() {
		
		if(config==null){
			//Log
			SynergykitLog.print(Errors.MSG_NO_CONFIG);
		}
		
		return config;
	}
	
	/* Synergylize */
	@Override
	public void synergylize(SynergykitRequest request, boolean parallelMode) {
		
		if(request==null){

			//Log
			SynergykitLog.print(Errors.MSG_NO_REQUEST);
			
			return;
		}
		
		//execute
		if(parallelMode==false){
			request.execute();
		}else{
			request.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
		}
		
	}
	
	/* Debug mode enabled getter */
	@Override
	public boolean isDebugModeEnabled(){ 
		return SynergykitLog.isEnabled();
	}
	
	/* Debug mode enabled setter */ 
	@Override
	public void setDebugModeEnabled(boolean debugModeEnabled){
		SynergykitLog.setEnabled(debugModeEnabled);
	}
	
	//---------------------------------------------------------------------------------------
	/* Get record */
    @Override
    public void invokeCloudCode(SynergykitCloudCode cloudCodeObject, Type type, ResponseListener listener, boolean parallelMode) {
        cloudCode.invokeCloudCode(cloudCodeObject, type, listener,parallelMode);
    }

	/* Get record */
	@Override
	public void getRecord(SynergykitConfig config, ResponseListener listener) {
		records.getRecord(config, listener);		
	}

	/* Get record */
	@Override
	public void getRecord(String collectionUrl, String recordId, Type type,	ResponseListener listener, boolean parallelMode) {
		records.getRecord(collectionUrl, recordId, type, listener, parallelMode);		
	}
	
	/* Get records */
	@Override
	public void getRecords(SynergykitConfig config,	RecordsResponseListener listener) {
		records.getRecords(config, listener);		
	}

	/* Get records */
	@Override
	public void getRecords(String collectionUrl, Type type,	RecordsResponseListener listener, boolean parallelMode) {
		records.getRecords(collectionUrl, type, listener, parallelMode);
	}

	/* Create record */
	@Override
	public void createRecord(String collectionUrl, SynergykitObject object,	ResponseListener listener, boolean parallelMode) {
		records.createRecord(collectionUrl, object, listener, parallelMode);	
	}

	/* Update record */
	@Override
	public void updateRecord(String collectionUrl,SynergykitObject object, ResponseListener listener,	boolean parallelMode) {
		records.updateRecord(collectionUrl,  object, listener, parallelMode);
	}

    @Override
    public void patchRecord(String collectionUrl, SynergykitObject object, ResponseListener listener, boolean parallelMode) {
        records.patchRecord(collectionUrl,  object, listener, parallelMode);
    }

    /* Delete record */
	@Override
	public void deleteRecord(String collectionUrl, String recordId,	DeleteResponseListener listener, boolean parallelMode) {
		records.deleteRecord(collectionUrl, recordId, listener, parallelMode);
	}

    @Override
    public void addAccess(String collectionUrl, String recordId, String userId, ResponseListener listener, boolean parallelMode) {

    }

    @Override
    public void removeAccess(String collectionUrl, String recordId, String userId, ResponseListener listener, boolean parallelMode) {

    }

    //-------------------------------------------------------------------------------------------------------------------
	/* Get user */
	@Override
	public void getUser(SynergykitConfig config, UserResponseListener listener) {
		users.getUser(config, listener);		
	}
	
	/* Get user */
	@Override
	public void getUser(String userId, Type type, UserResponseListener listener, boolean parallelMode) {
		users.getUser(userId, type, listener, parallelMode);		
	}

	/* Get users */
	@Override
	public void getUsers(SynergykitConfig config, UsersResponseListener listener) {
		users.getUsers(config, listener);		
	}

	/* Get users */
	@Override
	public void getUsers(Type type, UsersResponseListener listener,	boolean parallelMode) {
		users.getUsers(type, listener, parallelMode);		
	}

	/* Create user */
	@Override
	public void createUser(SynergykitUser user, UserResponseListener listener, boolean parallelMode) {
		users.createUser(user, listener, parallelMode);		
	}
	
	/* Update user */
	@Override
	public void updateUser(SynergykitUser user, UserResponseListener listener, boolean parallelMode) {
		users.updateUser(user, listener, parallelMode);
	}

    @Override
    public void patchUser(SynergykitUser user, UserResponseListener listener, boolean parallelMode) {
        users.patchUser(user, listener, parallelMode);
    }

    /* Delete user */
	@Override
	public void deleteUser(SynergykitUser user, DeleteResponseListener listener,	boolean parallelMode) {
		users.deleteUser(user, listener, parallelMode);
	}

    /* Add role */
    @Override
    public void addRole(SynergykitUser user, String role, UserResponseListener listener, boolean parallelMode) {
        users.addRole(user,role,listener,parallelMode);
    }

    /* Remove role */
    @Override
    public void removeRole(SynergykitUser user, String role, UserResponseListener listener, boolean parallelMode) {
        users.removeRole(user,role,listener,parallelMode);
    }

    //-------------------------------------------------------------------------------------------------------------------

    /* Add platform */
    @Override
    public void addPlatform(SynergykitPlatform platform, PlatformResponseListener listener, boolean parallelMode) {
        platforms.addPlatform(platform,listener,parallelMode);
    }

    /* Update platform */
    @Override
    public void updatePlatform(SynergykitPlatform platform, PlatformResponseListener listener, boolean parallelMode) {
        platforms.updatePlatform(platform,listener,parallelMode);
    }

    /* Delete platform */
    @Override
    public void deletePlatform(String platformId, DeleteResponseListener listener, boolean parallelMode) {
        platforms.deletePlatform(platformId,listener,parallelMode);
    }

    /* Get platform */
    @Override
    public void getPlatform(String platformId, PlatformResponseListener listener, boolean parallelMode) {
        platforms.getPlatform(platformId,listener,parallelMode);
    }


    //-------------------------------------------------------------------------------------------------------------------
	/* Send email */
	@Override
	public void sendEmail(String mailId,SynergykitEmail email, EmailResponseListener listener, boolean parallelMode) {
		notifications.sendEmail(mailId, email, listener, parallelMode);
	}
	
	/* Send notification */
	@Override
	public void sendNotification(SynergykitNotification notification, NotificationResponseListener listener, boolean parralelMode) {
		notifications.sendNotification(notification, listener, parralelMode);		
	}
	
	//-------------------------------------------------------------------------------------------------------------------
	/* Install cache */
	@Override
	public void installCache(Context context) {
		cache.installCache(context);		
	}
	
	//-------------------------------------------------------------------------------------------------------------------
	/* Register user */
	@Override
	public void registerUser(SynergykitUser user, UserResponseListener listener) {
		authorization.registerUser(user, listener);		
	}

	/* Login user */
	@Override
	public void loginUser(SynergykitUser user, UserResponseListener listener) {
		authorization.loginUser(user, listener);		
	}

    /* Logout user */
    @Override
    public void logoutUser() {
        authorization.logoutUser();
    }

    /* Link Facebook*/
    @Override
    public void linkFacebook(SynergykitUser user, SynergykitFacebookAuthData facebookAuthData, UserResponseListener listener) {
        authorization.linkFacebook(user,facebookAuthData,listener);
    }

    /* Link Twitter*/
    @Override
    public void linkTwitter(SynergykitUser user, SynergykitTwitterAuthData twitterAuthData, UserResponseListener listener) {
        authorization.linkTwitter(user, twitterAuthData, listener);
    }

    /* Link Google*/
    @Override
    public void linkGoogle(SynergykitUser user, SynergykitGoogleAuthData googleAuthData, UserResponseListener listener) {
        authorization.linkGoogle(user,googleAuthData,listener);
    }

    /* Login via Facebook */
    @Override
    public void loginViaFacebook(SynergykitFacebookAuthData facebookAuthData, UserResponseListener listener) {
        authorization.loginViaFacebook(facebookAuthData,listener);
    }

    /* Login Twitter*/
    @Override
    public void loginViaTwitter(SynergykitTwitterAuthData twitterAuthData, UserResponseListener listener) {
        authorization.loginViaTwitter(twitterAuthData,listener);
    }

    /* Login Google */
    @Override
    public void loginViaGoogle(SynergykitGoogleAuthData googleAuthData, UserResponseListener listener) {
        authorization.loginViaGoogle(googleAuthData,listener);
    }

    /* Login anonymous */
    @Override
    public void loginAnonymous(SynergykitAnonymousAuthData anonymousAuthData, UserResponseListener listener) {
        authorization.loginAnonymous(anonymousAuthData,listener);
    }

    //-------------------------------------------------------------------------------------------------------------------
	/* Create file */
	@Override
	public void createFile(byte[] data, FileResponseListener listener) {
		files.createFile(data, listener);
		
	}
	
	/* Create bitmap */
	@Override
	public void createFile(Bitmap bitmap, FileResponseListener listener) {
		files.createFile(bitmap, listener);
		
	}


    /* Get file */
    @Override
    public void getFile(SynergykitConfig config, FileResponseListener listener) {
        files.getFile(config,listener);
    }

    /* Get file */
    @Override
    public void getFile(String fileId, FileResponseListener listener, boolean parallelMode) {
        files.getFile(fileId,listener,parallelMode);
    }

    /* Get files */
    @Override
    public void getFiles(SynergykitConfig config, FilesResponseListener listener) {
        files.getFiles(config,listener);
    }

    /* Get files */
    @Override
    public void getFiles(FilesResponseListener listener, boolean parallelMode) {
        files.getFiles(listener,parallelMode);
    }

    /* Update file */
    @Override
    public void updateFile(SynergykitFile file, FileResponseListener listener, boolean parallelMode) {
        files.updateFile(file,listener,parallelMode);
    }

    /* Patch file */
    @Override
    public void patchFile(SynergykitFile file, FileResponseListener listener, boolean parallelMode) {
        files.patchFile(file, listener, parallelMode);
    }

    /* Delete file */
    @Override
    public void deleteFile(String fileId, DeleteResponseListener listener, boolean parallelMode) {
        files.deleteFile(fileId,listener,parallelMode);
    }

	/* Download bitmap */
	@Override
	public void downloadBitmap(String path, BitmapResponseListener listener) {
		files.downloadBitmap(path, listener);
		
	}

	/* Download file */
	@Override
	public void downloadFile(String path, BytesResponseListener listener) {
		files.downloadFile(path, listener);
		
	}

    //-------------------------------------------------------------------------------------------------------------------
    /* Init batch */
    @Override
    public void initBatch(String batchId) {
        batches.initBatch(batchId);
    }

    /* Remove batch */
    @Override
    public void removeBatch(String batchId) {
        batches.removeBatch(batchId);
    }

    /* Remove all batches */
    @Override
    public void removeAllBatches() {
       batches.removeAllBatches();
    }

    /* Send batch */
    @Override
    public void sendBatch(String batchId, BatchResponseListener listener, boolean parallelMode) {
        batches.sendBatch(batchId, listener, parallelMode);
    }

    /*Batch getter*/
    @Override
    public LinkedList<SynergykitBatchItem> getBatch(String batchId) {
        return batches.getBatch(batchId);
    }

    //-------------------------------------------------------------------------------------------------------------------
    @Override
    public boolean initSocket() {
        return socket.initSocket();
    }

    @Override
    public boolean isSocketInitialized() {
        return socket.isSocketInitialized();
    }

    @Override
    public boolean isSocketConnected() {
        return socket.isSocketConnected();
    }

    @Override
    public void connectSocket() {
        socket.connectSocket();
    }

    @Override
    public void connectSocket(@Nullable SocketStateListener listener) {
        socket.connectSocket(listener);
    }

    @Override
    public void disconnectSocket() {
        socket.disconnectSocket();
    }

    @Override
    public void onSocket(String eventName, SocketEventListener listener) {
        socket.onSocket(eventName,listener);
    }

    @Override
    public void onSocket(String eventName, @Nullable String collectionName, SocketEventListener listener) {
        socket.onSocket(eventName,collectionName,listener);
    }

    @Override
    public void onSocket(String eventName, @Nullable String collectionName, @Nullable SynergykitSocketFilter filter, SocketEventListener listener) {
        socket.onSocket(eventName,collectionName,filter,listener);
    }

    @Override
    public void offSocket(String eventName, SocketEventListener listener) {
        socket.offSocket(eventName,listener);
    }

    @Override
    public void offSocket(String eventName, @Nullable String collectionName, SocketEventListener listener) {
        socket.offSocket(eventName,collectionName,listener);
    }

    @Override
    public void offSocket(String eventName, @Nullable String collectionName, @Nullable SynergykitSocketFilter filter, SocketEventListener listener) {
        socket.offSocket(eventName,collectionName,filter,listener);
    }

    @Override
    public void emitViaSocket(String eventName, Object object) {
        socket.emitViaSocket(eventName,object);
    }



}
