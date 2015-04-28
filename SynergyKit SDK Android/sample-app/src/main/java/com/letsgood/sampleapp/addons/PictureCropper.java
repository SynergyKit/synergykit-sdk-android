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

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;

public class PictureCropper {

    /* Crop center square */
    public static Bitmap cropCenterSquare(Bitmap image) {
        int newSize = 0;
        int x = 0;
        int y = 0;

        //null check
        if (image == null)
            return null;

        //square check
        if (image.getWidth() == image.getHeight())
            return image;

        //new size && center counting
        if (image.getWidth() > image.getHeight()) {
            newSize = image.getHeight();
            x = (image.getWidth() - image.getHeight()) / 2;
        } else {
            newSize = image.getWidth();
            y = (image.getHeight() - image.getWidth()) / 2;
        }


        return Bitmap.createBitmap(image, x, y, newSize, newSize);
    }

    /* Rounded corner  cropper */
    public static Bitmap cropRoundedCorner(Bitmap image, int pixels) {

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect;
        final RectF rectF;
        final float roundPx = pixels;
        Bitmap croppedImage;
        Canvas canvas;

        //null check
        if (image == null && pixels < 0)
            return image;

        rect = new Rect(0, 0, image.getWidth(), image.getHeight()); //create rectangle
        rectF = new RectF(rect); //

        croppedImage = Bitmap.createBitmap(image.getWidth(), image.getHeight(), Config.ARGB_8888); //create bitmap
        canvas = new Canvas(croppedImage); //create canvas


        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(image, rect, rect, paint);

        return croppedImage;
    }

    /* Crop center circle */
    public static Bitmap cropCircleCenter(Bitmap image) {
        Bitmap croppedImage;

        //null check
        if (image == null)
            return null;

        croppedImage = PictureCropper.cropCenterSquare(image); //crop square
        croppedImage = PictureCropper.cropRoundedCorner(croppedImage, image.getWidth());

        return croppedImage;

    }
}
