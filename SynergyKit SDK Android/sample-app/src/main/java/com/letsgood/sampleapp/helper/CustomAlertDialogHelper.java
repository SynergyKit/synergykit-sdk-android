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

package com.letsgood.sampleapp.helper;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Static methods working with view and classes extends view. Created by goldj
 * on 14.2.14.
 */
public class CustomAlertDialogHelper {
	/**
	 * Set view visibility visible.
	 * 
	 * @param view
	 */
	public static void setVisibilityVisible(View view) {
		view.setVisibility(View.VISIBLE);
	}

	/**
	 * Set view visibility gone.
	 * 
	 * @param view
	 */
	public static void setVisibilityGone(View view) {
		view.setVisibility(View.GONE);
	}

	/**
	 * Get image view from view for defined resource id.
	 * 
	 * @param resourceId
	 * @return
	 */
	public static ImageView getImageView(View view, int resourceId) {
		return ((ImageView) view.findViewById(resourceId));
	}

	/**
	 * Get layout view from defined resource id.
	 * 
	 * @param resourceId
	 * @return
	 */
	public static RelativeLayout getLayout(View view, int resourceId) {
		return ((RelativeLayout) view.findViewById(resourceId));
	}

	/**
	 * Get text view from view for defined resource id.
	 * 
	 * @param resourceId
	 * @return
	 */
	public static View getView(View view, int resourceId) {
		return ((View) view.findViewById(resourceId));
	}

	/**
	 * Get text view from view for defined resource id.
	 * 
	 * @param resourceId
	 * @return
	 */
	public static TextView getTextView(View view, int resourceId) {
		return ((TextView) view.findViewById(resourceId));
	}

	/**
	 * Get button from view for defined resource id.
	 * 
	 * @param resourceId
	 * @return
	 */
	public static Button getButton(View view, int resourceId) {
		return ((Button) view.findViewById(resourceId));
	}

	/**
	 * Get relative layout from view for defined resource id.
	 * 
	 * @param resourceId
	 * @return
	 */
	public static RelativeLayout getRelativeLayout(View view, int resourceId) {
		return ((RelativeLayout) view.findViewById(resourceId));
	}

	/**
	 * Get progress bar from view for defined resource id.
	 * 
	 * @param resourceId
	 * @return
	 */
	public static ProgressBar getProgressBar(View view, int resourceId) {
		return ((ProgressBar) view.findViewById(resourceId));
	}
}
