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

package com.letsgood.synergykitsdkandroid.builders.uri;



import com.letsgood.synergykitsdkandroid.errors.Errors;
import com.letsgood.synergykitsdkandroid.log.SynergykitLog;

import java.util.LinkedList;
import java.util.List;

public class OrderBy{

	/* Constants */
	private static final String DIRECTION_DESCENDING = "desc";
	private static final String DIRECTION_ASCENDING = "asc";
	private static final int MAX_SIZE = 12;

	
	/* Attributes */
	private List<String> orderByList = new LinkedList<String>();

    /* New instance */
    public static OrderBy newInstance(){
        return new OrderBy();
    }
	
	/* Order by desc */
	public OrderBy setOrderByAsc(String parameter){
		this.setOrderBy(parameter, DIRECTION_ASCENDING);
        return this;
	}
	
	
	/* Order by desc */
	public OrderBy setOrderByDesc(String parameter){
		this.setOrderBy(parameter, DIRECTION_DESCENDING);

		return this;
	}
	
	/* Order by */
	private OrderBy setOrderBy(String parameter, String direction){
		//parameter check
		if(parameter==null || parameter.length()==0){
			SynergykitLog.print(Errors.MSG_NULL_ARGUMENTS_OR_EMPTY);
		}
		
		//size check
		if(orderByList.size()>=MAX_SIZE){
			SynergykitLog.print(Errors.MSG_NULL_ARGUMENTS_OR_EMPTY);
		}
			
		
		
		//add order by
		orderByList.add(parameter + "+" + direction);

        return this;
	}
	
	
	/* Order by getter */
	public String getOrderBy(){
		String fullOrderBy = null;
		
		//no filter	
		if(orderByList.isEmpty())
			return fullOrderBy;
		

		//set order by
		for(int i=0; i<orderByList.size(); i++){
			
			if(i==0)
				fullOrderBy = new String( "$orderby=" + orderByList.get(i));
			else
				fullOrderBy += "," + orderByList.get(i);
		}
	
		
		return fullOrderBy;	
	}
}
