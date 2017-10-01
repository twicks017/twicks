package com.example.appleworksltd.twickr.MainFiles;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appleworksltd.twickr.MysqlDatabase.Buy;
import com.example.appleworksltd.twickr.R;

import java.util.Random;


public class EventsDetails extends AppCompatActivity {

    TextView nameTxt;
    TextView venueTxt;
    int id;
    TextView dateTxt;
    TextView descriptionTxt;
    static TextView px;
    ImageView img;
    String promoter;
    int prce;

    //final String urlAddress1 = "https://twickr.000webhostapp.com/twickr/Buy.php";
    final String urlAddress1 = "http://192.168.43.168/Tickite/Buy.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Spinner sp = (Spinner) findViewById(R.id.spin);

        nameTxt = (TextView) findViewById(R.id.detName);
        venueTxt = (TextView) findViewById(R.id.detVenue);
        dateTxt = (TextView) findViewById(R.id.detDate);
        descriptionTxt = (TextView) findViewById(R.id.detDesc);
        img = (ImageView) findViewById(R.id.image1);
        px = (TextView) findViewById(R.id.detPrice);

        Intent i = this.getIntent();

        promoter = i.getExtras().getString("PROMOTER");
        String name = i.getExtras().getString("NAME_KEY");
        String venue = i.getExtras().getString("VENUE_KEY");
        String date = i.getExtras().getString("DATE_KEY");
        String desc = i.getExtras().getString("DESCRIPTION_KEY");
         final String image = i.getExtras().getString("IMAGEURL_KEY");

        final int ordinary = i.getExtras().getInt("ORDINARY_KEY");
        final int vip = i.getExtras().getInt("VIP_KEY");
        final int vvip = i.getExtras().getInt("VVIP");
        final int special = i.getExtras().getInt("SPECIAL_KEY");
        final int ticket_id = i.getExtras().getInt("TICKET_ID_KEY");
        final String location = i.getExtras().getString("LOCATION");


        nameTxt.setText(name);
        venueTxt.setText(venue);
        dateTxt.setText(date);
        descriptionTxt.setText(desc);
        PicassoClient.downloadImage(this,image,img);
        setTitle(name);
        toolbar.setTitleTextColor(Color.parseColor("#fafafa"));




        final String price [] = {"ORDINARY", "VIP", "VVIP","SPECIAL"};


        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,price);
        stringArrayAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        sp.setAdapter(stringArrayAdapter);




        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (sp.getSelectedItem().toString().equals("ORDINARY")) {
                    px.setText("" + ordinary);
                    prce = ordinary;

                }
                else if (sp.getSelectedItem().toString().equals("VIP")){
                    px.setText("" +vip);
                    prce = vip;
                }
                else if (sp.getSelectedItem().toString().equals("VVIP")){
                    px.setText(""+vvip);
                    prce = vvip;
                }
                else if (sp.getSelectedItem().toString().equals("SPECIAL")){
                    px.setText(""+special);
                    prce=special;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(EventsDetails.this,"You have selected neither options", Toast.LENGTH_SHORT).show();
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String ALLOWED_CHARACTERS ="A1B2C3D4E5F6G7H8I9JKLMNOPQRSTUVWXYZ";
                int sizeOfRandomString = 7;
                final Random random=new Random();
                final StringBuilder sb=new StringBuilder(sizeOfRandomString);
                for(int j=0;j<sizeOfRandomString;++j)
                    sb.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));
                String code = sb.toString();

               // String x = px.getText().toString();
                String name = nameTxt.getText().toString();
                String venue = venueTxt.getText().toString();
                String date = dateTxt.getText().toString();
                String classe = sp.getSelectedItem().toString();
                int s = 0;



                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplication());
                String username = preferences.getString("Username", "");

               Buy r = new Buy(EventsDetails.this,urlAddress1,prce,name,venue,location,date,classe,promoter,username,code,image);
                r.execute();

                Toast.makeText(getApplicationContext(),promoter,Toast.LENGTH_LONG).show();

/*
                Toast.makeText(getApplicationContext()," "+name+" "+venue+" "+location+" "+date+" "+classe+" "+promoter+" "+username+" "+code+" "+image,Toast.LENGTH_LONG).show();
*/
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
