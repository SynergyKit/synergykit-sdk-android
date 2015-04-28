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

package com.letsgood.sampleapp.addons;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.FileNotFoundException;
import java.io.IOException;

public class PicturePicker {

    /* Attributes */
    private static PicturePicker mInstance;
    private static final int PHOTO_SIZE_SET_WITDH = 800;
    private static final int PHOTO_SIZE_SET_HEIGHT = 800;
    private static final int PHOTO_SIZE_STANDARD_WIDTH = 1080;

    /* Constructor */
    protected PicturePicker() {
    }

    /* Returns instance */
    public static PicturePicker getInstance() {
        if (mInstance == null) {
            mInstance = new PicturePicker();
        }
        return mInstance;
    }

    /* Retruns processed bitmap */
    public Bitmap getBitmap(Uri pictureUri, Context context) {

        try {

            Bitmap originalBitmap = getBitmapFromUri(pictureUri, context);

            if (originalBitmap != null) {
                Bitmap resizedBitmap = resizeBitmap(originalBitmap);
                return resizedBitmap;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    /* Gets bitmap using Uri */
    private Bitmap getBitmapFromUri(Uri pictureUri, Context context) {

        Bitmap bitmap;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(
                    context.getContentResolver(), pictureUri);
            return bitmap;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* Resize of bitmap */
    private Bitmap resizeBitmap(Bitmap bitmap) throws FileNotFoundException, OutOfMemoryError {

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.outWidth = bitmap.getWidth();
        options.outHeight = bitmap.getHeight();

        int width = options.outWidth;
        int height = options.outHeight;
        if (width >= PHOTO_SIZE_STANDARD_WIDTH) {

            double imageRatio = (double) width / (double) height;
            width = PHOTO_SIZE_STANDARD_WIDTH;
            height = (int) (PHOTO_SIZE_STANDARD_WIDTH / imageRatio);
            int heightRatio;
            int widthRatio;

            if (width >= 1080) {
                heightRatio = (int) Math.ceil(options.outHeight
                        / (float) PHOTO_SIZE_SET_WITDH);
                widthRatio = (int) Math.ceil(options.outWidth
                        / (float) PHOTO_SIZE_SET_WITDH);
            } else {
                heightRatio = (int) Math.ceil(options.outHeight
                        / (float) PHOTO_SIZE_SET_HEIGHT);
                widthRatio = (int) Math.ceil(options.outWidth
                        / (float) PHOTO_SIZE_SET_HEIGHT);
            }

            if (heightRatio > 1 || widthRatio > 1) {
                if (heightRatio > widthRatio) {
                    options.inSampleSize = heightRatio;
                } else {
                    options.inSampleSize = widthRatio;
                }
            }

            options.inJustDecodeBounds = false;
            options.outWidth = width;
            options.outHeight = height;
            bitmap = getResizedBitmap(bitmap, height, width);

        }

        return bitmap;
    }

    /* Returns resized bitmap */
    public Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth)
            throws OutOfMemoryError {

        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);

        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height,
                matrix, false);
        return resizedBitmap;
    }

}