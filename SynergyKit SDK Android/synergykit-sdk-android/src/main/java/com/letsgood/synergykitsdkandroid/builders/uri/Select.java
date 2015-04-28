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

public class Select {

	/* Attributes */
	private List<String> selectList = new LinkedList<String>();

    public static Select newInstance(){
        return new Select();
    }

	/* Select setter */
	public Select addSelect(String attribute) {
		if (attribute == null || attribute.length() == 0) {
			SynergykitLog.print(Errors.MSG_NULL_ARGUMENTS_OR_EMPTY);
		}

		selectList.add(attribute);
        return this;
	}

	/* Select getter */
	public String getSelect() {
		String fullSelect = null;

		if (selectList.isEmpty())
			return fullSelect;

		// set order by
		for (int i = 0; i < selectList.size(); i++) {

			if (i == 0)
				fullSelect = new String("$select=" + selectList.get(i));
			else
				fullSelect += "," + selectList.get(i);
		}

		return fullSelect;
	}
}
