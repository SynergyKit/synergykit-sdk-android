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



import android.os.AsyncTask;

import com.letsgood.synergykitsdkandroid.builders.ResultObjectBuilder;
import com.letsgood.synergykitsdkandroid.errors.Errors;
import com.letsgood.synergykitsdkandroid.requestmethods.Delete;
import com.letsgood.synergykitsdkandroid.requestmethods.Get;
import com.letsgood.synergykitsdkandroid.requestmethods.Patch;
import com.letsgood.synergykitsdkandroid.requestmethods.Post;
import com.letsgood.synergykitsdkandroid.requestmethods.PostFile;
import com.letsgood.synergykitsdkandroid.requestmethods.Put;
import com.letsgood.synergykitsdkandroid.resources.SynergykitError;
import com.letsgood.synergykitsdkandroid.resources.SynergykitObject;
import com.letsgood.synergykitsdkandroid.resources.SynergykitResponse;
import com.letsgood.synergykitsdkandroid.resources.SynergykitUri;

import org.apache.http.HttpStatus;

import java.lang.reflect.Type;

public abstract class SynergykitRequest extends AsyncTask<Void, Void, Object> {

	/* Do in background */
	@Override
	protected abstract Object doInBackground(Void... params);

	/* On post execute */
	@Override
	protected abstract void onPostExecute(Object object);

	/* Request method GET */
	protected static SynergykitResponse get(final SynergykitUri uri,final String sessionToken, final boolean sessionTokenRequired) {
		SynergykitResponse response = new SynergykitResponse();
		Get get = new Get(uri,sessionToken,sessionTokenRequired); // request method get

		response.setBufferedReader(get.execute());
		response.setStatusCode(get.getStatusCode());

		return response;
	}

	/* Request method GET */
	protected static SynergykitResponse getFile(final SynergykitUri uri,final String sessionToken, final boolean sessionTokenRequired) {
		SynergykitResponse response = new SynergykitResponse();
		Get get = new Get(uri,sessionToken,sessionTokenRequired); // request method get

        get.setAuthorizationEnabled(false);
		response.setInputStream(get.halfExecute());
		response.setStatusCode(get.getStatusCode());

		return response;
	}
	
	/* Request method POST */
	protected static SynergykitResponse post(final SynergykitUri uri,final  Object object,final String sessionToken, final boolean sessionTokenRequired) {
		SynergykitResponse response = new SynergykitResponse();
		Post post = new Post(uri, object,sessionToken,sessionTokenRequired);

		response.setBufferedReader(post.execute());
		response.setStatusCode(post.getStatusCode());

		return response;
	}


	
	/* File method POST */
	protected static SynergykitResponse postFile(SynergykitUri uri, byte[] data,final String sessionToken, final boolean sessionTokenRequired) {
		SynergykitResponse response = new SynergykitResponse();
		PostFile postFile = new PostFile(uri, data,sessionToken,sessionTokenRequired);

		response.setBufferedReader(postFile.execute());
		response.setStatusCode(postFile.getStatusCode());

		return response;
	}

	/* Request method PUT */
	protected static SynergykitResponse put(SynergykitUri uri, Object object,final String sessionToken, final boolean sessionTokenRequired) {
		SynergykitResponse response = new SynergykitResponse();
		Put put = new Put(uri, object,sessionToken,sessionTokenRequired);

		response.setBufferedReader(put.execute());
		response.setStatusCode(put.getStatusCode());

		return response;
	}

	/* Request method PUT */
	protected static SynergykitResponse delete(SynergykitUri uri,final String sessionToken, final boolean sessionTokenRequired) {
		SynergykitResponse response = new SynergykitResponse();
		Delete delete = new Delete(uri,sessionToken,sessionTokenRequired);

		response.setBufferedReader(delete.execute());
		response.setStatusCode(delete.getStatusCode());

		return response;
	}


    /* Request method PATCH */
    protected static SynergykitResponse patch(SynergykitUri uri, Object object,final String sessionToken, final boolean sessionTokenRequired) {
        SynergykitResponse response = new SynergykitResponse();
        Patch patch = new Patch(uri, object,sessionToken,sessionTokenRequired);

        response.setBufferedReader(patch.execute());
        response.setStatusCode(patch.getStatusCode());

        return response;
    }


	/* Manage response */
	protected ResponseDataHolder manageResponseToObject(
			SynergykitResponse response, Type type) {
		ResponseDataHolder dataHolder = new ResponseDataHolder();

		if (response == null
				|| response.getStatusCode() >= HttpStatus.SC_INTERNAL_SERVER_ERROR
				|| response.getStatusCode() <= Errors.SC_UNSPECIFIED_ERROR) {

			dataHolder.errorObject = ResultObjectBuilder.buildError(response.getStatusCode());
		} else if (response.getStatusCode() >= HttpStatus.SC_INTERNAL_SERVER_ERROR
				|| response.getStatusCode() <= Errors.SC_UNSPECIFIED_ERROR) {

			dataHolder.errorObject = ResultObjectBuilder.buildError(response
					.getStatusCode());

		} else if (response.getStatusCode() >= HttpStatus.SC_OK
				&& response.getStatusCode() < HttpStatus.SC_MULTIPLE_CHOICES) {

			dataHolder.statusCode = response.getStatusCode();

			if (response.getBufferedReader() != null)
				dataHolder.object = ResultObjectBuilder.buildObject(
						dataHolder.statusCode, response.getBufferedReader(),
						type);

		} else {

			dataHolder.statusCode = response.getStatusCode();
			dataHolder.errorObject = ResultObjectBuilder.buildError(
					dataHolder.statusCode, response.getBufferedReader());

		}

		return dataHolder;
	}

	/* Manage response */
	protected ResponseDataHolder manageResponseToObjects(SynergykitResponse response, Type type) {
		ResponseDataHolder dataHolder = new ResponseDataHolder();
		

		if (response == null
				|| response.getStatusCode() >= HttpStatus.SC_INTERNAL_SERVER_ERROR
				|| response.getStatusCode() <= Errors.SC_UNSPECIFIED_ERROR) {

			dataHolder.errorObject = ResultObjectBuilder.buildError(response.getStatusCode());
		} else if (response.getStatusCode() >= HttpStatus.SC_INTERNAL_SERVER_ERROR
				|| response.getStatusCode() <= Errors.SC_UNSPECIFIED_ERROR) {

			dataHolder.errorObject = ResultObjectBuilder.buildError(response
					.getStatusCode());

		} else if (response.getStatusCode() >= HttpStatus.SC_OK
				&& response.getStatusCode() < HttpStatus.SC_MULTIPLE_CHOICES) {

			dataHolder.statusCode = response.getStatusCode();
			dataHolder.objects = ResultObjectBuilder.buildObjects(
					dataHolder.statusCode, response.getBufferedReader(), type);

		} else {

			dataHolder.statusCode = response.getStatusCode();
			dataHolder.errorObject = ResultObjectBuilder.buildError(
					dataHolder.statusCode, response.getBufferedReader());

		}

		return dataHolder;
	}

	// ----------------------------------------------------------------------------------
	protected class ResponseDataHolder {
		/* Attributes */
		public SynergykitError errorObject;
		public SynergykitObject object;
		public SynergykitObject[] objects;
		public byte[] data;
		public int statusCode;

		/* Constructor */
		public ResponseDataHolder() {
			statusCode = Errors.SC_UNSPECIFIED_ERROR;
			errorObject = null;
			object = null;
			objects = null;
			data = null;
		}
	}
}
