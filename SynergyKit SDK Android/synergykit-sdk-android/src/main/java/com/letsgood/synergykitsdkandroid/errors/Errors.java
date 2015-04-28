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

package com.letsgood.synergykitsdkandroid.errors;

public class Errors {
	
	//Unspecified error 
	public static final int SC_UNSPECIFIED_ERROR= -1;
	public static final String MSG_UNSPECIFIED_ERROR = "Unspecified error.";
	
	//Not initialized
	public static final int SC_SK_NOT_INITIALIZED = -2;
	public static final String MSG_SK_NOT_INITIALIZED = "SynergyKIT is NOT initialized.";
	
	//Uri not valid
	public static final int SC_URI_NOT_VALID = -3;
	public static final String MSG_URI_NOT_VALID= "URI in NOT valid.";
	
	//Object null
	public static final int SC_NO_OBJECT = -4;
	public static final String MSG_NO_OBJECT= "Sending object is NULL.";
	
	//Arguments must me set
	public static final int SC_NULL_ARGUMENTS_OR_EMPTY= -5;
	public static final String MSG_NULL_ARGUMENTS_OR_EMPTY= "Some of arguments is NULL or EMPTY.";

    //No batch found me set
    public static final int SC_BATCH_NOT_FOUND= -6;
    public static final String MSG_BATCH_NOT_FOUND= "Batch was not found.";

    //No sesstion token
    public static final int SC_NO_SESSION_TOKEN= -7;
    public static final String MSG_NO_SESSION_TOKEN= "No session token set.";
	
	//Order by overflow
	public static final String MSG_ORDRER_BY_OVERFLOW = "Maximum size of ORDER BY arguments is 12. You are over.";
	
	//Skip negative
	public static final String MSG_SKIP_NEGATIVE  = "Skip value must NOT be negative";
	
	//Top negative
	public static final String MSG_TOP_NEGATIVE  = "Top value must NOT be negative";
	
	//No listener
	public static final String MSG_NO_CALLBACK_LISTENER = "No callback listener was set.";
	
	//No config file
	public static final String MSG_NO_CONFIG = "SynergyKIT config file WAS NOT set";
	
	// No request
	public static final String MSG_NO_REQUEST = "SynergyKITRequest WAS NOT set.";
	
	// Cache init failed
	public static final String MSG_CACHE_INIT_FAILED = "HTTP response cache installation failed.";

    // Socket init failed
    public static final String MSG_SOCKET_INIT_FAILED = "Socket initialization failed.";

    // Socket connect failed
    public static final String MSG_SOCKET_CONNECT_FAILED = "Socket connect failed.";

    // Socket not inited
    public static final String MSG_SOCKET_NOT_INITED = "Socket is not initialized.";

    // No user logged in
    public static final String MSG_NO_USER_LOGGED_IN = "No user is logged in.";

    public static final String MSG_SERVICE_UNAVAILABLE="Connection error.";



}
