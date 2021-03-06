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

package com.letsgood.sampleapp.model;

import com.google.gson.annotations.Expose;
import com.letsgood.synergykitsdkandroid.resources.SynergykitUser;

/**
 * Created by Letsgood.com - Pavel Stambrecht on 2. 4. 2015.
 */
public class DemoUser extends SynergykitUser {

    /* Attributes */
    @Expose
    private String name;
    private int age;

    /* Name getter */
    public String getName() {
        return name;
    }

    /* Name setter */
    public void setName(String name) {
        this.name = name;
    }

    /* Age getter */
    public int getAge() {
        return age;
    }

    /* Age setter */
    public void setAge(int age) {
        this.age = age;
    }
}
