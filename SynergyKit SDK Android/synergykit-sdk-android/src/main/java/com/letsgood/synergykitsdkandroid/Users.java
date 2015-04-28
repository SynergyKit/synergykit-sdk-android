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





import com.google.gson.annotations.Expose;
import com.letsgood.synergykitsdkandroid.builders.UriBuilder;
import com.letsgood.synergykitsdkandroid.errors.Errors;
import com.letsgood.synergykitsdkandroid.builders.uri.Resource;
import com.letsgood.synergykitsdkandroid.config.SynergykitConfig;
import com.letsgood.synergykitsdkandroid.interfaces.IUsers;
import com.letsgood.synergykitsdkandroid.listeners.DeleteResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.UserResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.UsersResponseListener;
import com.letsgood.synergykitsdkandroid.log.SynergykitLog;
import com.letsgood.synergykitsdkandroid.request.RequestDelete;
import com.letsgood.synergykitsdkandroid.request.RoleRequestDelete;
import com.letsgood.synergykitsdkandroid.request.UserRequestGet;
import com.letsgood.synergykitsdkandroid.request.UserRequestPatch;
import com.letsgood.synergykitsdkandroid.request.UserRequestPost;
import com.letsgood.synergykitsdkandroid.request.UserRequestPut;
import com.letsgood.synergykitsdkandroid.request.UsersRequestGet;
import com.letsgood.synergykitsdkandroid.resources.SynergykitError;

import com.letsgood.synergykitsdkandroid.resources.SynergykitUser;

import java.lang.reflect.Type;

public class Users implements IUsers {
	/* Constants */
	private static final int TOP = 100;

    /* Attributes */

	/* Get user */
	@Override
	public void getUser(SynergykitConfig config, UserResponseListener listener) {
		UserRequestGet request = new UserRequestGet();
		request.setConfig(config);
		request.setListener(listener);
		Synergykit.synergylize(request, config.isParallelMode());
	}

	/* Get user */
	@Override
	public void getUser(String userId, Type type, UserResponseListener listener, boolean parallelMode) {
		SynergykitConfig config = Synergykit.getConfig();

		
		//Uri builder
		UriBuilder uriBuilder = new UriBuilder()
								.setResource(Resource.RESOURCE_USERS)
								.setRecordId(userId);
		
		//set config
		config.setUri(uriBuilder.build());
		config.setParallelMode(parallelMode);
		config.setType(type);

		//redirect
		this.getUser(config, listener);		
	}

	/* Get users */
	@Override
	public void getUsers(SynergykitConfig config, UsersResponseListener listener) {
		UsersRequestGet request = new UsersRequestGet();
		request.setConfig(config);
		request.setListener(listener);
		Synergykit.synergylize(request, config.isParallelMode());
		
	}

	/* Get users */
	@Override
	public void getUsers(Type type, UsersResponseListener listener, boolean parallelMode) {
		SynergykitConfig config = Synergykit.getConfig();

		//Uri builder
		UriBuilder uriBuilder = new UriBuilder()
								.setResource(Resource.RESOURCE_USERS)
								.setTop(TOP);
		
		//set config
		config.setUri(uriBuilder.build());
		config.setParallelMode(parallelMode);
		config.setType(type);


		//redirect
		this.getUsers(config, listener);		
		
	}

	/* Create user */
	@Override
	public void createUser(SynergykitUser user, UserResponseListener listener,	boolean parallelMode) {
		SynergykitConfig config = new SynergykitConfig();
		UserRequestPost request = new UserRequestPost();
		
		//User check
		if(user == null){
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
								.setResource(Resource.RESOURCE_USERS);
		
		//set config
		config.setUri(uriBuilder.build());
		config.setParallelMode(parallelMode);
		config.setType(user.getClass());
		
		
		//set request
		request.setConfig(config);
		request.setListener(listener);
		request.setObject(user);
		
		//execute
		Synergykit.synergylize(request, parallelMode);
		
		
	}

	/* Update user */
	@Override
	public void updateUser(SynergykitUser user, UserResponseListener listener, boolean parallelMode) {
		SynergykitConfig config = new SynergykitConfig();
		UserRequestPut request = new UserRequestPut();
		
		//User check
		if(user == null){
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
								.setResource(Resource.RESOURCE_USERS)
								.setRecordId(user.getId());
		
		//set config
		config.setUri(uriBuilder.build());
		config.setParallelMode(parallelMode);
		config.setType(user.getClass());
		
		
		//set request
		request.setConfig(config);
		request.setListener(listener);
		request.setObject(user);
		
		//execute
		Synergykit.synergylize(request, parallelMode);
	}

    @Override
    public void patchUser(SynergykitUser user, UserResponseListener listener, boolean parallelMode) {
        SynergykitConfig config = new SynergykitConfig();
        UserRequestPatch request = new UserRequestPatch();

        //User check
        if(user == null){
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
                .setResource(Resource.RESOURCE_USERS)
                .setRecordId(user.getId());

        //set config
        config.setUri(uriBuilder.build());
        config.setParallelMode(parallelMode);
        config.setType(user.getClass());


        //set request
        request.setConfig(config);
        request.setListener(listener);
        request.setObject(user);

        //execute
        Synergykit.synergylize(request, parallelMode);
    }

    /* Delete user */
	@Override
	public void deleteUser(SynergykitUser user, DeleteResponseListener listener,	boolean parallelMode) {
		SynergykitConfig config = new SynergykitConfig();
		RequestDelete request = new RequestDelete();
		
		//Object check
		if(user == null){
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
								.setResource(Resource.RESOURCE_USERS)
								.setRecordId(user.getId());
		
		//set config
		config.setUri(uriBuilder.build());
		config.setParallelMode(parallelMode);		
		
		//set request
		request.setConfig(config);
		request.setListener(listener);
		
		//execute
		Synergykit.synergylize(request, parallelMode);
	}

     /* Add role */
    @Override
    public void addRole(SynergykitUser user, String role, UserResponseListener listener, boolean parallelMode) {
        UserRequestPost request = new UserRequestPost();

        //Object check
        if(user == null || role == null || user.getId()==null){
            //Log
            SynergykitLog.print(Errors.MSG_NULL_ARGUMENTS_OR_EMPTY);

            //error callback
            if(listener!=null)
                listener.errorCallback(Errors.SC_NULL_ARGUMENTS_OR_EMPTY, new SynergykitError(Errors.SC_NULL_ARGUMENTS_OR_EMPTY, Errors.MSG_NULL_ARGUMENTS_OR_EMPTY));
            else if(Synergykit.isDebugModeEnabled())
                SynergykitLog.print(Errors.MSG_NO_CALLBACK_LISTENER);

            return;
        }

        // Role
        UserRole userRole = new UserRole();
        userRole.setRole(role);

        //Uri builder
        UriBuilder uriBuilder = new UriBuilder()
                .setResource(String.format(Resource.RESOURCE_USERS_ROLES, user.getId()));

        SynergykitConfig config = new SynergykitConfig();
        config.setType(SynergykitUser.class);
        config.setParallelMode(parallelMode);
        config.setUri(uriBuilder.build());

        request.setConfig(config);
        request.setListener(listener);
        request.setObject(userRole);
        Synergykit.synergylize(request, config.isParallelMode());
    }

    /* Remove role */
    @Override
    public void removeRole(SynergykitUser user, String role, UserResponseListener listener, boolean parallelMode) {
        RoleRequestDelete request = new RoleRequestDelete();

        //Object check
        if(user == null || role == null || user.getId()==null){
            //Log
            SynergykitLog.print(Errors.MSG_NULL_ARGUMENTS_OR_EMPTY);

            //error callback
            if(listener!=null)
                listener.errorCallback(Errors.SC_NULL_ARGUMENTS_OR_EMPTY, new SynergykitError(Errors.SC_NULL_ARGUMENTS_OR_EMPTY, Errors.MSG_NULL_ARGUMENTS_OR_EMPTY));
            else if(Synergykit.isDebugModeEnabled())
                SynergykitLog.print(Errors.MSG_NO_CALLBACK_LISTENER);

            return;
        }

        // Role
        UserRole userRole = new UserRole();
        userRole.setRole(role);

        //Uri builder
        UriBuilder uriBuilder = new UriBuilder()
                .setResource(String.format(Resource.RESOURCE_USERS_ROLES, user.getId()))
                .setRecordId(role);

        SynergykitConfig config = new SynergykitConfig();
        config.setType(SynergykitUser.class);
        config.setParallelMode(parallelMode);
        config.setUri(uriBuilder.build());

        request.setConfig(config);
        request.setListener(listener);
        Synergykit.synergylize(request, config.isParallelMode());
    }

    /* User role */
    private class UserRole{

        /* Attributes */
        @Expose
        private String role;

        /* Roles getter */
        public String getRole() {
            return role;
        }

        /* Role setter */
        public void setRole(String role) {
            this.role = role;
        }
    }
}
