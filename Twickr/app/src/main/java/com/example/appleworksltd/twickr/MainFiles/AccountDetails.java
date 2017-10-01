package com.example.appleworksltd.twickr.MainFiles;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.appleworksltd.twickr.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.Random;


public class AccountDetails extends AppCompatActivity {
    ImageView image1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.parseColor("#fafafa"));

        image1 = (ImageView) findViewById(R.id.image);

        RelativeLayout acc = (RelativeLayout)findViewById(R.id.content_account2);

        TextView nameTxt;
        TextView venueTxt;
        TextView dateTxt;
        TextView locationTxt;
        TextView catTxt;
        TextView codeTxt;
        ImageView img;

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            final String name1 = preferences.getString("Name", "");


            nameTxt = (TextView) findViewById(R.id.AccName);
            venueTxt = (TextView) findViewById(R.id.AccVenue);
            dateTxt = (TextView) findViewById(R.id.AccDate);
            locationTxt = (TextView) findViewById(R.id.AccLoc);
            img = (ImageView) findViewById(R.id.Accimage);
            codeTxt = (TextView) findViewById(R.id.code);
            catTxt = (TextView) findViewById(R.id.AccCat);

            Intent i = this.getIntent();

            String name = i.getExtras().getString("NAME_KEY");
            String venue = i.getExtras().getString("VENUE_KEY");
            String location = i.getExtras().getString("LOCATION_KEY");
            String date = i.getExtras().getString("DATE_KEY");
            String category = i.getExtras().getString("CATEGORY");

            int price = i.getExtras().getInt("PRICE_KEY");
            String code = i.getExtras().getString("CODE");
            String image = i.getExtras().getString("IMAGEURL");

            nameTxt.setText(name);
            venueTxt.setText(venue);
            dateTxt.setText(date);
            locationTxt.setText(location);
            codeTxt.setText(code);
            catTxt.setText(category);
        setTitle(name);

        if (price == 1){
            acc.setBackgroundColor(Color.RED);
        }


        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(code, BarcodeFormat.QR_CODE,200,200);

            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
           image1.setImageBitmap(bitmap);

        } catch (WriterException e) {

        }

            PicassoClient.downloadImage(this, image, img);

        final String ALLOWED_CHARACTERS ="A1B2C3D4E5F6G7H8I9JKLMNPQRSTUVWXYZ";
        int sizeOfRandomString = 7;
        final Random random=new Random();
        final StringBuilder sb=new StringBuilder(sizeOfRandomString);
        for(int j=0;j<sizeOfRandomString;++j)
            sb.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));
        String st = sb.toString();


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


}
