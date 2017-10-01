package com.example.appleworksltd.twickr.UI;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader.TileMode;

import com.squareup.picasso.Transformation;

/**
 * Created by appleworksltd on 23/05/2017.
 */


public class CircleTransformation implements Transformation {
    int borderwidth = 10;
    @Override
    public Bitmap transform(Bitmap source) {
        if (source == null || source.isRecycled()) {
            return null;
        }

        final int width = source.getWidth() + borderwidth;
        final int height = source.getHeight() + borderwidth;

        Bitmap canvasBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        BitmapShader shader = new BitmapShader(source, TileMode.CLAMP, TileMode.CLAMP);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(shader);

        Canvas canvas = new Canvas(canvasBitmap);
        float radius = width > height ? ((float) height) / 2f : ((float) width) / 2f;
        canvas.drawCircle(width / 2, height / 2, radius, paint);

        //border code
        paint.setShader(null);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(borderwidth);
        canvas.drawCircle(width / 2, height / 2, radius - borderwidth / 2, paint);
        //--------------------------------------

        if (canvasBitmap != source) {
            source.recycle();
        }

        return canvasBitmap;
    }

    @Override
    public String key() {
        return "circle";
    }
}
