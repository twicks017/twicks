package com.example.appleworksltd.twickr.UI;

import android.content.Context;
import android.widget.ImageView;

import com.example.appleworksltd.twickr.R;
import com.squareup.picasso.Picasso;

/**
 * Created by appleworksltd on 2/24/17.
 */

public class PicassoClient5 {

    public static void downloadImage(Context c, String imageUrl, ImageView image5){

        if (imageUrl != null && imageUrl.length()>0){
            Picasso.with(c).load(imageUrl).placeholder(R.drawable.concert).into(image5);

        }
        else {
            Picasso.with(c).load(R.drawable.concert).into(image5);
        }

    }
}
