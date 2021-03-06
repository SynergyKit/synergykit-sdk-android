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

package com.letsgood.synergykitsdkandroid.interfaces;



import com.letsgood.synergykitsdkandroid.listeners.UserResponseListener;
import com.letsgood.synergykitsdkandroid.resources.SynergykitAnonymousAuthData;
import com.letsgood.synergykitsdkandroid.resources.SynergykitFacebookAuthData;
import com.letsgood.synergykitsdkandroid.resources.SynergykitGoogleAuthData;
import com.letsgood.synergykitsdkandroid.resources.SynergykitTwitterAuthData;
import com.letsgood.synergykitsdkandroid.resources.SynergykitUser;

public interface IAuthorization {
	public void registerUser(SynergykitUser user, UserResponseListener listener);
	public void loginUser(SynergykitUser user, UserResponseListener listener);
    public void logoutUser();
    public void linkFacebook(SynergykitUser user, SynergykitFacebookAuthData facebookAuthData, UserResponseListener listener);
    public void linkTwitter(SynergykitUser user,SynergykitTwitterAuthData twitterAuthData, UserResponseListener listener);
    public void linkGoogle(SynergykitUser user, SynergykitGoogleAuthData googleAuthData, UserResponseListener listener);
    public void loginViaFacebook(SynergykitFacebookAuthData facebookAuthData, UserResponseListener listener);
    public void loginViaTwitter(SynergykitTwitterAuthData twitterAuthData, UserResponseListener listener);
    public void loginViaGoogle(SynergykitGoogleAuthData googleAuthData, UserResponseListener listener);
    public void loginAnonymous(SynergykitAnonymousAuthData anonymousAuthData, UserResponseListener listener);
}
