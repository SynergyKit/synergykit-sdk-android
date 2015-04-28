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

public class Filter {

	/* Constants */
	public static final String OPERATOR_EQUAL  = "+eq+";
	public static final String OPERATOR_NOT_EQUAL  = "+ne+";
	public static final String OPERATOR_GREATER_THAN  = "+gt+";
	public static final String OPERATOR_GREATER_THAN_OR_EQUAL  = "+ge+";
	public static final String OPERATOR_LESS_THAN  = "+lt+";
	public static final String OPERATOR_LESS_THAN_OR_EQUAL  = "+le+";
	public static final String OPERATOR_IN = "+in+";
	public static final String OPERATOR_NOT_IN = "+nin+";
	public static final String OPERATOR_AND  = "+and+";
	public static final String OPERATOR_OR  = "+or+";
	public static final String OPERATOR_NOT  = "+and+not+";
	
	/* Attributes */
	private String filter = null; 

    /* New instance*/
    public static Filter newInstance(){
        return new Filter();
    }

	/* Filter setter */
	public Filter setFilter(String attribute, String operator, String parameter){
		
		if(attribute == null || attribute.length()==0 || operator==null || operator.length()==0|| parameter==null || parameter.length() ==0){
			SynergykitLog.print(Errors.MSG_NULL_ARGUMENTS_OR_EMPTY);
			return this;
		}
	
		
		filter=Filter.buildAttribute(attribute)+operator+Filter.buildParametr(parameter);
        return this;

	}
	
	/* Filter setter */
	public Filter setFilter(String attribute, String operator, int parameter){
		
		if(attribute == null || attribute.length()==0 || operator==null || operator.length()==0){
			SynergykitLog.print(Errors.MSG_NULL_ARGUMENTS_OR_EMPTY);
            return this;
		}



        filter=Filter.buildAttribute(attribute)+operator+Filter.buildParametr(parameter);
        return this;
	}
	
	
	/* Filter setter */
	public Filter setFilter(String filter){
		if(filter == null || filter.length()==0){
			SynergykitLog.print(Errors.MSG_NULL_ARGUMENTS_OR_EMPTY);
            return this;
		}
			
		
		
		this.filter=new String(filter);
        return this;
	}
	

	
	
	/* Filter getter */
	public String getFilter(){
		
		String fullFilter = null;
		
		//not filter
		if(filter==null)
			return fullFilter;
		
		
		fullFilter = new String("$filter=" + filter);
		
		
		
		return fullFilter;
	}

    /* Filter body getter */
    public String getFilterBodyForSocket(){
      return filter.replaceAll("\\+"," ");
    }
	
	/* Attribute  builder*/
	public static String buildAttribute(String attribute){
		if(attribute == null || attribute.length()==0){
			SynergykitLog.print(Errors.MSG_NULL_ARGUMENTS_OR_EMPTY);
		}		
		
		return "'" + attribute + "'"; 
	}
	
	/* Parameter builder*/
	public static String buildParametr(int parameter){
		return "" + parameter + "";
	}

    /* Parameter builder*/
    public static String buildParametr(boolean parameter){
        return (parameter ?  "true" : "false");
    }

    /* Parameter builder*/
    public static String buildParametr(double parameter){
        return "" + parameter + "d";
    }

    /* Parameter builder*/
    public static String buildParametr(float parameter){
        return "" + parameter + "d";
    }

    /* Parameter builder*/
    public static String buildParametr(long parameter){
        return "" + parameter + "";
    }

    /* Parameter builder*/
    public static String buildParametr(short parameter){
        return "" + parameter + "";
    }

    /* Parameter builder*/
    public static String buildParametr(String parameter){
        return "'" + parameter + "'";
    }
	
	/* Array parameter builder */
	public static String buildArrayParameter(String[] arrayParameter){
		String parameter = new String();
		
		if(arrayParameter == null || arrayParameter.length==0){
			SynergykitLog.print(Errors.MSG_NULL_ARGUMENTS_OR_EMPTY);
			return "''";
		}
		
		
		for(int i=0; i<arrayParameter.length; i++){
			
			//null check
			if(arrayParameter[i]==null)
				continue;
			
			//set comma
			if(i!=0)
				parameter+=",";
			
			parameter+=arrayParameter[i].toString();
		}
		
		if(!parameter.isEmpty())
			parameter = "'" + parameter + "'";
			
		return parameter;
	}
	
	
	/* SubString Of Filter */
	public static String buildSubStringOfFilter(String attribute, String parameter){
		String filter = null;
		
		if(attribute == null || attribute.length()==0 || parameter==null || parameter.length()==0){
			SynergykitLog.print(Errors.MSG_NULL_ARGUMENTS_OR_EMPTY);
			return "''";
		}
		
		filter =  new String("substringof("+attribute+ ",'" + parameter +"')");
		
		//return filter.replaceAll(" +", "+"); //replace all spaces by 1 + char
        return filter;
	}

    /* Start with Filter */
    public static String buildStartsWithFilter(String attribute, String parameter){
        String filter = null;

        if(attribute == null || attribute.length()==0 || parameter==null || parameter.length()==0){
            SynergykitLog.print(Errors.MSG_NULL_ARGUMENTS_OR_EMPTY);
            return "''";
        }

        filter =  new String("startswith("+attribute+ ",'" + parameter +"')");

        return filter;
    }

    /* Start with Filter */
    public static String buildEndsWithFilter(String attribute, String parameter){
        String filter = null;

        if(attribute == null || attribute.length()==0 || parameter==null || parameter.length()==0){
            SynergykitLog.print(Errors.MSG_NULL_ARGUMENTS_OR_EMPTY);
            return "''";
        }

        filter =  new String("endswith("+attribute+ ",'" + parameter +"')");

        return filter;
    }


}
