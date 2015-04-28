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


import com.letsgood.synergykitsdkandroid.builders.uri.Filter;
import com.letsgood.synergykitsdkandroid.builders.uri.InLineCount;
import com.letsgood.synergykitsdkandroid.builders.uri.OrderBy;
import com.letsgood.synergykitsdkandroid.builders.uri.Select;
import com.letsgood.synergykitsdkandroid.builders.uri.Skip;
import com.letsgood.synergykitsdkandroid.builders.uri.Top;

/**
 * Created by Letsgood.com - Pavel Stambrecht on 18. 3. 2015.
 */
public class ODataBuilder {

    /* Attributes */
    private Filter filter = new Filter();
    private OrderBy orderBy = new OrderBy();
    private Top top = new Top();
    private Skip skip = new Skip();
    private Select select = new Select();
    private InLineCount inLineCount = new InLineCount();

    /* New instance */
    public static ODataBuilder newInstance(){
        return new ODataBuilder();
    }

    /* Filter setter */
    public ODataBuilder setFilter(String attribute, String operator, String parametr){
        this.filter.setFilter(attribute, operator, parametr);;
        return this;
    }

    /* Filter setter */
    public ODataBuilder setFilter(String attribute, String operator, int parametr){
        this.filter.setFilter(attribute, operator, parametr);
        return this;
    }

    /* Filter setter */
    public ODataBuilder setFilter(String filter){
        this.filter.setFilter(filter);
        return this;
    }

    /* Filter setter */
    public ODataBuilder setFilter(Filter filter){
        this.filter = filter;
        return this;
    }

    /* Select setter */
    public ODataBuilder addSelect(String attribute){
        this.select.addSelect(attribute);
        return this;
    }

    /* Order by desc setter */
    public ODataBuilder setOrderByDesc(String parameter){
        this.orderBy.setOrderByDesc(parameter);
        return this;
    }

    /* Order by asc setter */
    public ODataBuilder setOrderByAsc(String parameter){
        this.orderBy.setOrderByAsc(parameter);
        return this;
    }

    /* Top setter */
    public ODataBuilder setTop(int top){
        this.top.setTop(top);
        return this;
    }

    /* Top setter */
    public ODataBuilder setSkip(int skip){
        this.skip.setSkip(skip);
        return this;
    }

    public ODataBuilder setInLineCountEnabled(boolean enabled){
        this.inLineCount.setEnabled(enabled);
        return this;
    }

    /* Build */
    public String build(){
        String oData = new String();
        boolean hasFilters = false;


        if(select.getSelect()!=null){

            if(hasFilters==false){
                oData+="?";
                hasFilters=true;
                oData += select.getSelect();
            }else{
                oData += "&" + select.getSelect();
            }



        }

        //set filter
        if(filter.getFilter()!=null){

            if(hasFilters==false){
                oData+="?";
                hasFilters=true;
                oData+=filter.getFilter();
            }else{
                oData+="&" + filter.getFilter();
            }
        }

        //set order by
        if(orderBy.getOrderBy()!=null){

            if(hasFilters==false){
                oData+="?";
                hasFilters=true;
                oData+=orderBy.getOrderBy();
            }

            oData+="&" + orderBy.getOrderBy();
        }


        //set top
        if(top.getTop() != null){

            if(hasFilters==false){
                oData+="?";
                hasFilters=true;
                oData+=top.getTop();
            }else{
                oData+="&" + top.getTop();
            }

        }

        //set skip
        if(skip.getSkip()!=null){

            if(hasFilters==false){
                oData+="?";
                hasFilters=true;
                oData+=skip.getSkip();
            }else{
                oData+="&" + skip.getSkip();
            }
        }

        //set inline count
        if(inLineCount.isEnabled()){

            if(hasFilters==false){
                oData+="?";
                oData+=inLineCount.getInLineCount();
            }else{
                oData+="&" + inLineCount.getInLineCount();
            }
        }

        return oData;
    }
}
