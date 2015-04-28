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

package com.letsgood.synergykitsdkandroid.resources;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class SynergykitFile extends SynergykitObject implements Serializable {
	
	/* Attributes */
    @Expose
	protected String path;
    @Expose
	protected String extension;
    @Expose
	protected long size;
    @Expose
	protected String fileName;
    @Expose
	protected String applicationUrl;

	/* Path getter */
	public String getPath() {
		return path;
	}

	/* Path getter */
	public void setPath(String path) {
		this.path = path;
	}

	/* Extension getter */
	public String getExtension() {
		return extension;
	}

	/* Extension setter */
	public void setExtension(String extension) {
		this.extension = extension;
	}

	/* Size getter */
	public long getSize() {
		return size;
	}

	/* Size setter */
	public void setSize(long size) {
		this.size = size;
	}

	/* File name getter */
	public String getFileName() {
		return fileName;
	}

	/* File name setter */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/* Application URL getter */
	public String getApplicationUrl() {
		return applicationUrl;
	}

	/* Application URL setter */
	public void setApplicationUrl(String applicationUrl) {
		this.applicationUrl = applicationUrl;
	}
}
