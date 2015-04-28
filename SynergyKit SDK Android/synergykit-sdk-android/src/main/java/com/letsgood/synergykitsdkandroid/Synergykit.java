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
import android.support.annotation.Nullable;


import com.google.gson.Gson;
import com.letsgood.synergykitsdkandroid.addons.GsonWrapper;
import com.letsgood.synergykitsdkandroid.config.SynergykitConfig;
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
import com.letsgood.synergykitsdkandroid.request.SynergykitRequest;
import com.letsgood.synergykitsdkandroid.resources.SynergykitAnonymousAuthData;
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

public class Synergykit {

    /* Constants */
    public static final String GET = "GET";
    public static final String POST = "POST";
    public static final String PUT = "PUT";
    public static final String DELETE = "DELETE";
    public static final String PATCH = "PATCH";

	/* Synergylize */
	public static void synergylize(SynergykitRequest request, boolean parallelMode){
		SynergykitSdk.getInstance().synergylize(request, parallelMode);
	}
	
	//------------------------------------------------------------------------------
	/* Init */
	public static void init(String tenant, String applicationKey) {
		SynergykitSdk.getInstance().init(tenant, applicationKey);
	}

	/* Reset */
	public static void reset() {
		SynergykitSdk.getInstance().reset();
	}

	
	/* Tenant setter */
	public static void setTenant(String tenant) {
		SynergykitSdk.getInstance().setTenant(tenant);
	}

	/* Tenant getter */
	public static String getTenant() {
		return SynergykitSdk.getInstance().getTenant();
	}

	/* Application key setter */
	public static void setApplicationKey(String applicationKey) {
		SynergykitSdk.getInstance().setApplicationKey(applicationKey);
	}

    /* Token getter */
    public static String getSessionToken() {
        return SynergykitSdk.getInstance().getToken();
    }

    /* Token setter */
    public static void setSessionToken(String token) {
         SynergykitSdk.getInstance().setToken(token);
    }

	/* Application key getter */
	public static String getApplicationKey() {
		return SynergykitSdk.getInstance().getApplicationKey();
	}

    /* Logged user setter */
    public static SynergykitUser getLoggedUser() {
        return SynergykitSdk.getInstance().getLoggedUser();
    }

    /* Logged user getter */
    public static void setLoggedUser(SynergykitUser user) {
        SynergykitSdk.getInstance().setLoggedUser(user);
    }

	/* Is init */
	public static boolean isInit() {
		return SynergykitSdk.getInstance().isInit();
	}
	
	/* Config setter */
	public static void setConfig(SynergykitConfig config) {
		SynergykitSdk.getInstance().setConfig(config);
	}

	/* Config getter */
	public static SynergykitConfig getConfig() {
		return SynergykitSdk.getInstance().getConfig();
	}
	
	/* Debug mode enabled getter */
	public static boolean isDebugModeEnabled(){
		return SynergykitSdk.getInstance().isDebugModeEnabled();
	}
	
	/* Debug mode enabled setter */ 
	public static void setDebugModeEnabled(boolean debugModeEnabled){
		SynergykitSdk.getInstance().setDebugModeEnabled(debugModeEnabled);
	}
	
	//-------------------------------------------------------------------------------------------------------------------	
	/* Install cache */
	public static void installCache(Context context) {
		SynergykitSdk.getInstance().installCache(context);
	}

    //-------------------------------------------------------------------------------------------------------------------

    /* Invoke cloud code*/
    public static void invokeCloudCode(SynergykitCloudCode cloudCodeObject, Type type, ResponseListener listener, boolean parallelMode) {
        SynergykitSdk.getInstance().invokeCloudCode(cloudCodeObject,type, listener,parallelMode);
    }

	//------------------------------------------------------------------------------
	/* Get record */
	public static void getRecord(SynergykitConfig config, ResponseListener listener) {
		SynergykitSdk.getInstance().getRecord(config, listener);
	}

	/* Get record */
	public static void getRecord(String collection, String recordId, Type type,	ResponseListener listener, boolean parallelMode) {
		SynergykitSdk.getInstance().getRecord(collection, recordId, type, listener, parallelMode);
		
	}
	
	/* Get records */
	public static void getRecords(SynergykitConfig config, RecordsResponseListener listener) {
		SynergykitSdk.getInstance().getRecords(config, listener);
	}

	/* Get records */
	public static void getRecords(String collection, Type type, RecordsResponseListener listener, boolean parallelMode) {
		SynergykitSdk.getInstance().getRecords(collection, type, listener, parallelMode);
	}

    /* Create record */
	public static void createRecord(String collection, SynergykitObject object, ResponseListener listener, boolean parallelMode) {
		SynergykitSdk.getInstance().createRecord(collection, object, listener, parallelMode);
		
	}

	/* Update record */
	public static void updateRecord(String collection,SynergykitObject object, ResponseListener listener,	boolean parallelMode) {
		SynergykitSdk.getInstance().updateRecord(collection, object, listener, parallelMode);
		
	}

    /* Patch record */
    public static void patchRecord(String collection,SynergykitObject object, ResponseListener listener,	boolean parallelMode) {
        SynergykitSdk.getInstance().patchRecord(collection, object, listener, parallelMode);

    }

	/* Delete record */
	public static void deleteRecord(String collection, String recordId,	DeleteResponseListener listener, boolean parallelMode) {
		SynergykitSdk.getInstance().deleteRecord(collection, recordId, listener, parallelMode);
	}

	//-------------------------------------------------------------------------------------------------------------------
	/* Get user */
	public static void getUser(SynergykitConfig config, UserResponseListener listener) {
		SynergykitSdk.getInstance().getUser(config, listener);
	}
	
	/* Get user */
	public static void getUser(String userId, Type type, UserResponseListener listener, boolean parallelMode) {
		SynergykitSdk.getInstance().getUser(userId, type, listener, parallelMode);
	}

	/* Get users */
	public static void getUsers(SynergykitConfig config, UsersResponseListener listener) {
		SynergykitSdk.getInstance().getUsers(config, listener);
	}

	/* Get users */
	public static void getUsers(Type type, UsersResponseListener listener,	boolean parallelMode) {
		SynergykitSdk.getInstance().getUsers(type, listener, parallelMode);
	}

	/* Create user */
	public static void createUser(SynergykitUser user, UserResponseListener listener, boolean parallelMode) {
		SynergykitSdk.getInstance().createUser(user, listener, parallelMode);
	}
	
	/* Update user */
	public static void updateUser(SynergykitUser user, UserResponseListener listener, boolean parallelMode) {
		SynergykitSdk.getInstance().updateUser(user, listener, parallelMode);
	}

    /* Update user */
    public static void patchUser(SynergykitUser user, UserResponseListener listener, boolean parallelMode) {
        SynergykitSdk.getInstance().patchUser(user, listener, parallelMode);
    }

	/* Delete user */
	public static void deleteUser(SynergykitUser user, DeleteResponseListener listener,	boolean parallelMode) {
		SynergykitSdk.getInstance().deleteUser(user, listener, parallelMode);
	}

    /* Add role */
    public static void addRole(SynergykitUser user, String role, UserResponseListener listener, boolean parallelMode) {
        SynergykitSdk.getInstance().addRole(user,role,listener,parallelMode);
    }

    /* Remove role */
    public static void removeRole(SynergykitUser user, String role, UserResponseListener listener, boolean parallelMode) {
        SynergykitSdk.getInstance().removeRole(user,role,listener,parallelMode);
    }

    //-------------------------------------------------------------------------------------------------------------------

    /* Add platform */
    public static void addPlatform(SynergykitPlatform platform, PlatformResponseListener listener, boolean parallelMode) {
        SynergykitSdk.getInstance().addPlatform(platform,listener,parallelMode);
    }

    /* Update platform */
    public static void updatePlatform(SynergykitPlatform platform, PlatformResponseListener listener, boolean parallelMode) {
        SynergykitSdk.getInstance().updatePlatform(platform,listener,parallelMode);
    }

    /* Delete platform */
    public static void deletePlatform(String platformId, DeleteResponseListener listener, boolean parallelMode) {
        SynergykitSdk.getInstance().deletePlatform(platformId,listener,parallelMode);
    }

    /* Get platform */
    public static void getPlatform(String platformId, PlatformResponseListener listener, boolean parallelMode) {
        SynergykitSdk.getInstance().getPlatform(platformId,listener,parallelMode);
    }


    //-------------------------------------------------------------------------------------------------------------------
	/* Send email */
	public static void sendEmail(String templateName, SynergykitEmail email, EmailResponseListener listener, boolean parallelMode){
		SynergykitSdk.getInstance().sendEmail(templateName, email, listener, parallelMode);
	}
	
	/* Send notification */
	public static void sendNotification(SynergykitNotification notification, NotificationResponseListener listener, boolean parralelMode){
		SynergykitSdk.getInstance().sendNotification(notification, listener, parralelMode);
	}
	
	//-------------------------------------------------------------------------------------------------------------------	
	/* Register user */
	public static void registerUser(SynergykitUser user, UserResponseListener listener){
		SynergykitSdk.getInstance().registerUser(user, listener);
	}
	
	/* Login user */
	public static void loginUser(SynergykitUser user, UserResponseListener listener){
		SynergykitSdk.getInstance().loginUser(user, listener);
	}

    /* Logout user */
    public static void logoutUser(){
        SynergykitSdk.getInstance().logoutUser();
    }

    /* Link Facebook*/
    public static void linkFacebook(SynergykitUser user, SynergykitFacebookAuthData facebookAuthData, UserResponseListener listener) {
        SynergykitSdk.getInstance().linkFacebook(user, facebookAuthData, listener);
    }

    /* Link Twitter*/
    public static void linkTwitter(SynergykitUser user, SynergykitTwitterAuthData twitterAuthData, UserResponseListener listener) {
        SynergykitSdk.getInstance().linkTwitter(user, twitterAuthData, listener);
    }

    /* Link Google*/
    public static void linkGoogle(SynergykitUser user, SynergykitGoogleAuthData googleAuthData, UserResponseListener listener) {
        SynergykitSdk.getInstance().linkGoogle(user,googleAuthData,listener);
    }

    /* Login via Facebook */
    public static void loginViaFacebook(SynergykitFacebookAuthData facebookAuthData, UserResponseListener listener) {
        SynergykitSdk.getInstance().loginViaFacebook(facebookAuthData,listener);
    }

    /* Login Twitter*/
    public static void loginViaTwitter(SynergykitTwitterAuthData twitterAuthData, UserResponseListener listener) {
        SynergykitSdk.getInstance().loginViaTwitter(twitterAuthData,listener);
    }

    /* Login Google */
    public static void loginViaGoogle(SynergykitGoogleAuthData googleAuthData, UserResponseListener listener) {
        SynergykitSdk.getInstance().loginViaGoogle(googleAuthData,listener);
    }

    /* Login anonymous */
    public static void loginAnonymous(SynergykitAnonymousAuthData anonymousAuthData, UserResponseListener listener) {
        SynergykitSdk.getInstance().loginAnonymous(anonymousAuthData,listener);
    }
	
	//-------------------------------------------------------------------------------------------------------------------
/* Create file */
    public static void createFile(byte[] data, FileResponseListener listener) {
        SynergykitSdk.getInstance().createFile(data, listener);

    }

    /* Create bitmap */
    public static void createFile(Bitmap bitmap, FileResponseListener listener) {
        SynergykitSdk.getInstance().createFile(bitmap, listener);

    }


    /* Get file */
    public static void getFile(SynergykitConfig config, FileResponseListener listener) {
        SynergykitSdk.getInstance().getFile(config,listener);
    }

    /* Get file */
    public static void getFile(String fileId, FileResponseListener listener, boolean parallelMode) {
        SynergykitSdk.getInstance().getFile(fileId,listener,parallelMode);
    }

    /* Get files */
    public static void getFiles(SynergykitConfig config, FilesResponseListener listener) {
        SynergykitSdk.getInstance().getFiles(config,listener);
    }

    /* Get files */
    public static void getFiles(FilesResponseListener listener, boolean parallelMode) {
        SynergykitSdk.getInstance().getFiles(listener,parallelMode);
    }

    /* Update file */
    public static void updateFile(SynergykitFile file, FileResponseListener listener, boolean parallelMode) {
        SynergykitSdk.getInstance().updateFile(file,listener,parallelMode);
    }

    /* Patch file */
    public static void patchFile(SynergykitFile file, FileResponseListener listener, boolean parallelMode) {
        SynergykitSdk.getInstance().patchFile(file,listener,parallelMode);
    }

    /* Delete file */
    public static void deleteFile(String fileId, DeleteResponseListener listener, boolean parallelMode) {
        SynergykitSdk.getInstance().deleteFile(fileId, listener, parallelMode);
    }

    /* Download bitmap */
    public static void downloadBitmap(String path, BitmapResponseListener listener) {
        SynergykitSdk.getInstance().downloadBitmap(path, listener);

    }

    /* Download file */
    public static void downloadFile(String path, BytesResponseListener listener) {
        SynergykitSdk.getInstance().downloadFile(path, listener);

    }
    //-------------------------------------------------------------------------------------------------------------------
    /* Init batch */
    public static void initBatch(String batchId) {
        SynergykitSdk.getInstance().initBatch(batchId);
    }

    /* Remove batch */
    public static void removeBatch(String batchId) {
        SynergykitSdk.getInstance().removeBatch(batchId);
    }

    /* Remove all batches */

    public static void removeAllBatches() {
        SynergykitSdk.getInstance().removeAllBatches();
    }

    /* Send batch */
    public static void sendBatch(String batchId, BatchResponseListener listener, boolean parallelMode) {
        SynergykitSdk.getInstance().sendBatch(batchId, listener, parallelMode);
    }

    /*Batch getter*/
    public static LinkedList<SynergykitBatchItem> getBatch(String batchId) {
        return SynergykitSdk.getInstance().getBatch(batchId);
    }

    //-------------------------------------------------------------------------------------------------------------------
    public static boolean isSocketInitialized() {
        return SynergykitSdk.getInstance().isSocketInitialized();
    }

    public static boolean isSocketConnected() {
        return SynergykitSdk.getInstance().isSocketConnected();
    }

    public static void connectSocket() {
        SynergykitSdk.getInstance().connectSocket();
    }

    public static void connectSocket(@Nullable SocketStateListener listener) {
        SynergykitSdk.getInstance().connectSocket(listener);
    }

    public static void disconnectSocket() {
        SynergykitSdk.getInstance().disconnectSocket();
    }

    public static void onSocket(String eventName, SocketEventListener listener) {
        SynergykitSdk.getInstance().onSocket(eventName, listener);
    }

    public static void onSocket(String eventName, @Nullable String collectionName, SocketEventListener listener) {
        SynergykitSdk.getInstance().onSocket(eventName, collectionName, listener);
    }

    public static void onSocket(String eventName, @Nullable String collectionName, @Nullable SynergykitSocketFilter filter, SocketEventListener listener) {
        SynergykitSdk.getInstance().onSocket(eventName, collectionName, filter, listener);
    }

    public static void offSocket(String eventName, SocketEventListener listener) {
        SynergykitSdk.getInstance().offSocket(eventName, listener);
    }

    public static void offSocket(String eventName, @Nullable String collectionName, SocketEventListener listener) {
        SynergykitSdk.getInstance().offSocket(eventName, collectionName, listener);
    }

    public static void offSocket(String eventName, @Nullable String collectionName, @Nullable SynergykitSocketFilter filter, SocketEventListener listener) {
        SynergykitSdk.getInstance().offSocket(eventName, collectionName, filter, listener);
    }

    public static void emitViaSocket(String eventName, Object object) {
        SynergykitSdk.getInstance().emitViaSocket(eventName, object);
    }

    //-------------------------------------------------------------------------------------------------------------------
    public static Gson getGson(){
        return GsonWrapper.getGson();
    }
}
