package com.example.appleworksltd.twickr.UI;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.appleworksltd.twickr.DataObject.DataObject;
import com.example.appleworksltd.twickr.MainFiles.CircleTransform;
import com.example.appleworksltd.twickr.MainFiles.ReviewDetails;
import com.example.appleworksltd.twickr.R;

import java.util.ArrayList;

/**
 * Created by appleworksltd on 2/24/17.
 */

public class ReviewCustomAdapter extends BaseAdapter {
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Context c;
    ArrayList<DataObject> DataObjects;
    LayoutInflater inflater;
    private RelativeLayout mRelativeLayout;
    Bitmap bmp;


    private Resources mResources;
    private ImageView mImageView;


    public ReviewCustomAdapter(Context c, ArrayList<DataObject> DataObjects) {
        this.c = c;
        this.DataObjects = DataObjects;

        inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return DataObjects.size();
    }

    @Override
    public Object getItem(int position) {
        return DataObjects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = inflater.inflate(R.layout.review_model,parent,false);
        }

        ImageView  img = (ImageView) convertView.findViewById(R.id.image);
        final DataObject e = (DataObject) this.getItem(position);

       Glide.with(c)   // pass Context
                .load(e.getImageUrl1())    // pass the image url
                .centerCrop() // optional scaletype
                //.placeholder(R.drawable.concert) // optional placeholder
                .crossFade() //optional - to enable image crossfading
                .transform(new CircleTransform(c))
                .into(img);

        /*Glide.with(c)
                .load(e.getImageUrl1())
                .transform(new BorderTransformation(c))
                .into(img);*/



       /* Glide.with(c)
                .load(e.getImageUrl1()) // add your image url
                .transform(new CircleTransform(c)) // applying the image transformer
                .into(img);
*/
       // PicassoClient.downloadImage(c,e.getImageUrl1(),img);
        //Picasso.with(c).load(e.getImageUrl1()).transform(new CircleTransformation()).into(img1);

       /* Picasso.with(c).load(e.getImageUrl1())
                .error(R.drawable.concert)
                .placeholder(R.drawable.food)
                .resize(200, 200)
                .transform(new CircleTransformation())
                .into(img1);*/


        // Get the Resources
        /*mResources = c.getResources();

        // Get the bitmap from drawable resources
        final Bitmap srcBitmap = BitmapFactory.decodeResource(mResources, R.drawable.food);

        // Display the bitmap in ImageView
        img1.setImageBitmap(srcBitmap);

        // Set a click listener for circular Button widget

        // Initialize a new Paint instance
        Paint paint = new Paint();

        // Get source bitmap width and height
        int srcBitmapWidth = srcBitmap.getWidth();
        int srcBitmapHeight = srcBitmap.getHeight();

                *//*
                    IMPORTANT NOTE : You should experiment with border and shadow width
                    to get better circular ImageView as you expected.
                    I am confused about those size.
                *//*
        // Define border and shadow width
        int borderWidth = 25;
        int shadowWidth = 10;

        // destination bitmap width
        int dstBitmapWidth = Math.min(srcBitmapWidth,srcBitmapHeight)+borderWidth*2;
        //float radius = Math.min(srcBitmapWidth,srcBitmapHeight)/2;

        // Initializing a new bitmap to draw source bitmap, border and shadow
        Bitmap dstBitmap = Bitmap.createBitmap(dstBitmapWidth,dstBitmapWidth, Bitmap.Config.ARGB_8888);

        // Initialize a new canvas
        Canvas canvas = new Canvas(dstBitmap);

        // Draw a solid color to canvas
        canvas.drawColor(Color.WHITE);

        // Draw the source bitmap to destination bitmap by keeping border and shadow spaces
        canvas.drawBitmap(srcBitmap, (dstBitmapWidth - srcBitmapWidth) / 2, (dstBitmapWidth - srcBitmapHeight) / 2, null);

        // Use Paint to draw border
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(borderWidth * 2);
        paint.setColor(Color.WHITE);

        // Draw the border in destination bitmap
        canvas.drawCircle(canvas.getWidth() / 2, canvas.getHeight() / 2, canvas.getWidth() / 2, paint);

        // Use Paint to draw shadow
        paint.setColor(Color.LTGRAY);
        paint.setStrokeWidth(shadowWidth);

        // Draw the shadow on circular bitmap
        canvas.drawCircle(canvas.getWidth()/2,canvas.getHeight()/2,canvas.getWidth()/2,paint);

                *//*
                    RoundedBitmapDrawable
                        A Drawable that wraps a bitmap and can be drawn with rounded corners. You
                        can create a RoundedBitmapDrawable from a file path, an input stream, or
                        from a Bitmap object.
                *//*
        // Initialize a new RoundedBitmapDrawable object to make ImageView circular
        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(mResources, dstBitmap);

                *//*
                    setCircular(boolean circular)
                        Sets the image shape to circular.
                *//*
        // Make the ImageView image to a circular image
        roundedBitmapDrawable.setCircular(true);

                *//*
                    setAntiAlias(boolean aa)
                        Enables or disables anti-aliasing for this drawable.
                *//*
        roundedBitmapDrawable.setAntiAlias(true);

        // Set the ImageView image as drawable object
        img1.setImageDrawable(roundedBitmapDrawable);*/


        TextView name1Txt = (TextView) convertView.findViewById(R.id.namee);


        TextView venue1Txt = (TextView) convertView.findViewById(R.id.venue);
        TextView date1Txt = (TextView) convertView.findViewById(R.id.date);

        TextView locationTxt = (TextView) convertView.findViewById(R.id.location);
        TextView attendenceTxt = (TextView) convertView.findViewById(R.id.attendance);



        name1Txt.setText(e.getRName());
        venue1Txt.setText(e.getRVenue()+" —");
        date1Txt.setText(e.getRDate());
        locationTxt.setText( e.getRLocation());
        attendenceTxt.setText(e.getAttendence());



        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openDetailActivity(e.getRName(), e.getRVenue(),e.getLocation(),e.getRDate(),e.getImageUrl1(),e.getDesc1(),e.getImageUrl2(),e.getDesc2(),e.getImageUrl3(),e.getDesc3(),e.getImageUrl4(),e.getDesc4(),e.getImageUrl5(),e.getDesc5(),e.getRating());

            }
        });

        return convertView;
    }

    private  void openDetailActivity(String name,String venue,String location, String date,String imageUrl1,String desc1, String imageUrl2,String desc2, String imageUrl3,String desc3, String imageUrl4,String desc4, String imageUrl5,String desc5,int rating){
        Intent i = new Intent(c,ReviewDetails.class);

        i.putExtra("NAME_KEY", name);
        i.putExtra("VENUE_KEY", venue);
        i.putExtra("LOCATION_KEY", location);
        i.putExtra("DATE_KEY", date);
        i.putExtra("IMAGEURL1", imageUrl1);

        i.putExtra("DESC1", desc1);
        i.putExtra("IMAGEURL2", imageUrl2);

        i.putExtra("DESC2", desc2);
        i.putExtra("IMAGEURL3", imageUrl3);

        i.putExtra("DESC3", desc3);
        i.putExtra("IMAGEURL4", imageUrl4);

        i.putExtra("DESC4", desc4);
        i.putExtra("IMAGEURL5", imageUrl5);
        i.putExtra("DESC5", desc5);
        i.putExtra("RATING", rating);



        c.startActivity(i);

    }

}
