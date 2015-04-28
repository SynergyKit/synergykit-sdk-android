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


/**
 * Created by Letsgood.com - Pavel Stambrecht on 24. 3. 2015.
 */
public class SynergykitSocketFilter {

    /* Attributes */
    @Expose
    private String name;
    @Expose
    private String query;

    /* Constructor  */
    public SynergykitSocketFilter(String name, String query){

        if(name==null || query == null || name.isEmpty() || query.isEmpty())
            throw new IllegalArgumentException();

        this.name = name;
        this.query = query;
    }

    /* Name getter */
    public String getName() {
        return name;
    }

    /* Name setter */
    public void setName(String name) {

        if(name==null || name.isEmpty())
            throw new IllegalArgumentException();

        this.name = name;
    }

    /* Query getter */
    public String getQuery() {
        return query;
    }

    /* Query setter */
    public void setQuery(String query) {

        if(query == null ||  query.isEmpty())
            throw new IllegalArgumentException();

        this.query = query;
    }


}
