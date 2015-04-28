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

package com.letsgood.synergykitsdkandroid.log;

import android.util.Log;

public class SynergykitLog {
	
	/* Constants */
	private static final String TAG = "SynergyKitDebug";

	/* Attributes */
	private static SynergykitLog instance = null;
	private boolean enabled = false;
	
	/* Instance getter */
	public static SynergykitLog getInstance(){
		
		if(instance == null){
			instance = new SynergykitLog();
		}
		
		return instance;
	}
	
	/* Enabled setter */
	public static void setEnabled(boolean enabled){
		SynergykitLog.getInstance().enabled = enabled;
	}
	
	/* Enabled getter */
	public static boolean isEnabled(){
		return SynergykitLog.getInstance().enabled;
	}
	
	/* Log */
	public static void print(String log){
		// null check
		if(log == null)
			return;
		
		//print 
		if(SynergykitLog.getInstance().enabled == true){
			Log.d(TAG, log);
		}
			
	}
	
}
