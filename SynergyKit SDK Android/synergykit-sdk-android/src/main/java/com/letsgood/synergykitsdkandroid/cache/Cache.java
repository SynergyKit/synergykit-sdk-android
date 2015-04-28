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

package com.letsgood.synergykitsdkandroid.cache;




import android.content.Context;

import com.letsgood.synergykitsdkandroid.errors.Errors;
import com.letsgood.synergykitsdkandroid.interfaces.ICache;
import com.letsgood.synergykitsdkandroid.log.SynergykitLog;

import java.io.File;

public class Cache implements ICache {
	
	/* Constants */
	private final static String CACHE_DIR_NAME = "http";
	private final static int DEFAULT_CACHE_SIZE = 10 * 1024 * 1024;

	/* Init cache */
	@Override
	public void installCache(Context context) {
		File httpCacheDir = null;

		try {
			httpCacheDir = new File(context.getCacheDir(), CACHE_DIR_NAME); // init cache dir
			//HttpResponseCache.install(httpCacheDir, DEFAULT_CACHE_SIZE); //init cache
			Class.forName("android.net.http.HttpResponseCache")
				 .getMethod("install", File.class, long.class)      
				 .invoke(null, httpCacheDir, DEFAULT_CACHE_SIZE);
			
		} catch (Exception e) {
			SynergykitLog.print(Errors.MSG_CACHE_INIT_FAILED + ": " + e);
		}
		
	}
}
