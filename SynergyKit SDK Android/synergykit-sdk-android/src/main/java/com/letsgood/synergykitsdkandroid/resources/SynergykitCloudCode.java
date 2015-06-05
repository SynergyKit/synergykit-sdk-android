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

import java.io.Serializable;

/**
 * Created by Letsgood.com - Pavel Stambrecht on 21. 4. 2015.
 */
public class SynergykitCloudCode extends  SynergykitObject implements Serializable {

    /* Attributes */
    protected String cloudCodeName;

    /* New instance*/
    public static SynergykitCloudCode newInstance(String cloudCodeName){
        return new SynergykitCloudCode(cloudCodeName);
    }

    public SynergykitCloudCode(String cloudCodeName) {
        this.cloudCodeName = cloudCodeName;
    }

        /* Cloud code name getter */
    public String getCloudCodeName() {
        return cloudCodeName;
    }

    /* Cloud code name setter */
    public void setCloudCodeName(String cloudCodeName) {
        this.cloudCodeName = cloudCodeName;
    }
}
