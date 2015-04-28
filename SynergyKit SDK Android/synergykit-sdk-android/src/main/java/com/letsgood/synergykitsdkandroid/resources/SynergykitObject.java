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
import com.letsgood.synergykitsdkandroid.Synergykit;
import com.letsgood.synergykitsdkandroid.interfaces.ISynergykitObjectProtocol;
import com.letsgood.synergykitsdkandroid.listeners.DeleteResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.ResponseListener;

import java.io.Serializable;

public class SynergykitObject implements Serializable, ISynergykitObjectProtocol {
	
	/* Attributes */
    @Expose
    protected String _id;
    @Expose
    protected long __v;
    @Expose
    protected Long createdAt;
    @Expose
    protected Long updatedAt;

    protected String collection;

    /* Constructor */
    public SynergykitObject(){
        this(null,null);
    }

    /* Constructor */
    public SynergykitObject(String collectionUrl){
        this(collectionUrl,null);
    }

    /* Constructor */
    public SynergykitObject(String collectionUrl, String recordId){
        setCollection(collectionUrl);
        setId(recordId);
    }

    /* Id getter */
    public String getId() {
        return _id;
    }

    /* Id setter */
    public void setId(String id) {
        this._id = id;
    }
    /* Version getter */
    public long getVersion() {
        return __v;
    }

    /* Version setter */
    public void setVersion(long version) {
        this.__v = version;
    }
    
    /* CreatedAt setter */
    public void setCreatedAt(long createdAt){
    	this.createdAt=createdAt;
    }
    
    /* CreatedAt getter */
    public long getCreatedAt(){
    	return this.createdAt;
    }
    
    /* UpdateAt setter */
    public void setUpdatedAt(long updatedAt){
    	this.updatedAt = updatedAt;
    }
    
    /* UpdateAt getter */
    public long getUpdatedAt(){
    	return this.updatedAt;
    }

    /* Collection getter */
    public String getCollection() {
        return collection;
    }

    /* Collection setter */
    public void setCollection(String collection) {
        this.collection = collection;
    }

    /* Save */
    @Override
    public void save() {
        this.save(null);
    }

    /* Fetch */
    @Override
    public void fetch() {
        this.fetch(null);
    }

    /* Delete */
    @Override
    public void delete() {
        this.delete(null);
    }

    /* Save */
    @Override
    public void save(ResponseListener listener) {

        if(getId()==null)
            Synergykit.createRecord(getCollection(), this, listener, false);
        else
            Synergykit.updateRecord(getCollection(), this, listener, false);
    }

    /* Fetch */
    @Override
    public void fetch(ResponseListener listener) {
        Synergykit.getRecord(getCollection(), getId(), this.getClass(), listener, false);
    }

    /* Delete */
    @Override
    public void delete(DeleteResponseListener listener) {
        Synergykit.deleteRecord(getCollection(), getId(), listener, false);
    }
}
