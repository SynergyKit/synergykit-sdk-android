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

package com.letsgood.synergykitsdkandroid.builders;


import com.letsgood.synergykitsdkandroid.Synergykit;
import com.letsgood.synergykitsdkandroid.builders.uri.Collection;
import com.letsgood.synergykitsdkandroid.builders.uri.Filter;
import com.letsgood.synergykitsdkandroid.builders.uri.RecordId;
import com.letsgood.synergykitsdkandroid.builders.uri.Resource;
import com.letsgood.synergykitsdkandroid.config.SynergykitConfig;
import com.letsgood.synergykitsdkandroid.resources.SynergykitEndpoint;
import com.letsgood.synergykitsdkandroid.resources.SynergykitUri;

public class UriBuilder {

	/* Attributes */
	private Resource resource = new Resource();
	private Collection collection = new Collection();
	private RecordId recordId = new RecordId();
    private ODataBuilder oDataBuilder = ODataBuilder.newInstance();

    /* New instance */
    public static UriBuilder newInstance(){
        return new UriBuilder();

    }

	/* Resource setter */
	public UriBuilder setResource(String resource){
		this.resource.setResource(resource);
		return this;
	}

    /* Resource collection setter */
	public UriBuilder setCollection(String collection){
		this.collection.setCollection(collection);
		return this;
	}

	/* Resource Id setter */
	public UriBuilder setRecordId(String recordId){
		this.recordId.setRecordId(recordId);
		return this;
	}

    /* Mail Id setter */
    public UriBuilder setMailId(String mailId){
        return this.setRecordId(mailId);
    }

    /* Funcion Id setter */
    public UriBuilder setFunctionId(String functionId){
        return this.setRecordId(functionId);
    }
	
	/* Filter setter */
	public UriBuilder setFilter(String attribute, String operator, String parameter){
		oDataBuilder.setFilter(attribute, operator, parameter);;
		return this;
	}
	
	/* Filter setter */
	public UriBuilder setFilter(String attribute, String operator, int parameter){
        oDataBuilder.setFilter(attribute, operator, parameter);
		return this;
	}


	/* Filter setter */
	public UriBuilder setFilter(String attribute, String operator, String[] parameter){
		oDataBuilder.setFilter(attribute, operator, parameter);
		return this;
	}
	
	/* Filter setter */
	public UriBuilder setFilter(String filter){
        oDataBuilder.setFilter(filter);
		return this;
	}

    /* Filter setter */
    public UriBuilder setFilter(Filter filter){
        oDataBuilder.setFilter(filter);
        return this;
    }

	/* Select setter */
	public UriBuilder addSelect(String attribute){
        oDataBuilder.addSelect(attribute);
		return this;
	}
	
	/* Order by desc setter */
	public UriBuilder setOrderByDesc(String parameter){
        oDataBuilder.setOrderByDesc(parameter);
		return this;
	}

	/* Order by asc setter */
	public UriBuilder setOrderByAsc(String parameter){
        oDataBuilder.setOrderByAsc(parameter);
		return this;
	}
	
	/* Top setter */
	public UriBuilder setTop(int top){
        oDataBuilder.setTop(top);
		return this;
	}
	
	/* Top setter */
	public UriBuilder setSkip(int skip){
        oDataBuilder.setSkip(skip);
		return this;
	}

    /* Inline count setter */
	public UriBuilder setInLineCountEnabled(boolean enabled){
		oDataBuilder.setInLineCountEnabled(enabled);
		return this;
	}

	/* Build */
	public SynergykitUri build(){
        SynergykitUri synergyKitUri = null;
		String uri = new String(SynergykitConfig.API_SYNERGYKIT_URL);

	    //set tenant
		uri = String.format(uri, Synergykit.getTenant());

        synergyKitUri = new SynergykitUri(uri);
        synergyKitUri.setEndpoint(buildEndpoint());
		
		return synergyKitUri;
	}

    /* Socket address getter*/
    public SynergykitUri getSocketUrl(){
        String uri = new String(SynergykitConfig.SOCKET_SYNERGYKIT_URL);

        //set tenant
        uri = String.format(uri, Synergykit.getTenant());

        return new SynergykitUri(uri);
    }


    public SynergykitEndpoint buildEndpoint(){
        String endpoint = new String();
       boolean hasFilters = false;

        endpoint += "/" + resource.getResource();  //set resource

        if(collection.getCollection()!=null)
            endpoint += "/" + collection.getCollection();  //set collection


        if(recordId.getRecordId()!=null)
            endpoint += "/" + recordId.getRecordId();	//set resource id


        if(oDataBuilder.build()!=null)
            endpoint += oDataBuilder.build();            // set OData


        return new SynergykitEndpoint(endpoint);
    }
}
