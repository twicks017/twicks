package com.example.appleworksltd.twickr.MainFiles;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.appleworksltd.twickr.R;
import com.example.appleworksltd.twickr.UI.PicassoClient1;
import com.example.appleworksltd.twickr.UI.PicassoClient2;
import com.example.appleworksltd.twickr.UI.PicassoClient4;
import com.example.appleworksltd.twickr.UI.PicassoClient5;


public class ReviewDetails extends AppCompatActivity {


    private static final String isPlaying = "Media is ready to Play";
    private static final String notPlaying = "Media has stopped Playing";

    private SeekBar seek;

    MediaPlayer player = new MediaPlayer();
    private ImageButton plus,minus;

    ImageButton im;

    TextView nameTxt;
    TextView venueTxt;
    int id;
    VideoView image3;
    TextView dateTxt;
    TextView Rdesc;
    static TextView px;
    ImageView imge;
    String img3;
    String x;

    ProgressDialog pd;

    ImageButton btnPlayPause;
    MediaController mediaControls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*seek = (SeekBar) findViewById(R.id.seeker1);
        seek.setVisibility(ProgressBar.VISIBLE);
        seek.setProgress(0);
        seek.setMax(player.getDuration());*/
        toolbar.setTitleTextColor(Color.parseColor("#fafafa"));


        Toast.makeText(this, isPlaying, Toast.LENGTH_SHORT).show();


        btnPlayPause = (ImageButton)findViewById(R.id.pause_play);

        Intent i = this.getIntent();



      //  x = "http://192.168.43.111/Tickite/images/Furio.mp4";

        String img1 = i.getExtras().getString("IMAGEURL1");
        String img2 = i.getExtras().getString("IMAGEURL2");
        img3 = i.getExtras().getString("IMAGEURL3");
        String img4 = i.getExtras().getString("IMAGEURL4");
        String img5 = i.getExtras().getString("IMAGEURL5");

        int rating = i.getExtras().getInt("RATING");
        String desc1 = i.getExtras().getString("DESC1");
        String desc2 = i.getExtras().getString("DESC2");

        String desc3 = i.getExtras().getString("DESC3");
        String desc4 = i.getExtras().getString("DESC4");
        String desc5 = i.getExtras().getString("DESC5");
        String name = i.getExtras().getString("NAME_KEY");


        String filename = img1;
        String filenameArray[] = filename.split("\\.");
        String extension = filenameArray[filenameArray.length-1];
        //Toast.makeText(getApplicationContext(),extension,Toast.LENGTH_LONG).show();



        if(extension==".mp4"){

        }

        //Rdesc.setText(desc);

        ImageView image1 = (ImageView)findViewById(R.id.img1);
        ImageView image2 = (ImageView)findViewById(R.id.img2);
        image3 = (VideoView) findViewById(R.id.img3);
        ImageView image4 = (ImageView)findViewById(R.id.img4);
        ImageView image5 = (ImageView)findViewById(R.id.img5);

        setTitle(name);


        PicassoClient1.downloadImage(getApplicationContext(),img1,image1);
        PicassoClient2.downloadImage(getApplicationContext(),img2,image2);
        PicassoClient4.downloadImage(getApplicationContext(),img4,image4);
        PicassoClient5.downloadImage(getApplicationContext(),img5,image5);

        TextView descc1 = (TextView)findViewById(R.id.desc1);
        TextView descc2 = (TextView)findViewById(R.id.desc2);
        TextView descc3 = (TextView)findViewById(R.id.desc3);
        TextView descc4 = (TextView)findViewById(R.id.desc4);
        TextView descc5 = (TextView)findViewById(R.id.desc5);

        descc1.setText(desc1);
        descc2.setText(desc2);
        descc3.setText(desc3);
        descc4.setText(desc4);
        descc5.setText(desc5);

        if (mediaControls == null) {
            // create an object of media controller class
            mediaControls = new MediaController(ReviewDetails.this);
            mediaControls.setAnchorView(image3);
        }

        RatingBar ratingBar = (RatingBar)findViewById(R.id.rating);
        ratingBar.setRating(rating);

        btnPlayPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd = new ProgressDialog(ReviewDetails.this);
                pd.setMessage("Please wait....");
                pd.setCancelable(false);
                pd.show();

                try {
                    if (!image3.isPlaying()) {
                        Uri uri = Uri.parse(img3);
                        image3.setVideoURI(uri);
                        image3.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                btnPlayPause.setImageResource(R.drawable.play_btn);
                            }
                        });
                    }else{
                        image3.pause();
                        btnPlayPause.setImageResource(R.drawable.play_btn);
                    }

                }catch (Exception ex){}



                image3.requestFocus();
                image3.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        pd.dismiss();
                        mp.setLooping(true);
                        image3.start();
                        btnPlayPause.setImageResource(R.drawable.btn_pause);
                        btnPlayPause.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                btnPlayPause.setImageResource(R.drawable.play_btn);
                                image3.pause();

                            }
                        });
                    }
                });


            }

        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

}
