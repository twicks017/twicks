package com.example.appleworksltd.twickr.UI;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.appleworksltd.twickr.DataObject.AccountDataObject;
import com.example.appleworksltd.twickr.MainFiles.AccountDetails;
import com.example.appleworksltd.twickr.MainFiles.CircleTransform;
import com.example.appleworksltd.twickr.R;

import java.util.ArrayList;

/**
 * Created by appleworksltd on 2/24/17.
 */

public class AccountCustomAdapter extends BaseAdapter {
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Context c;
    CoordinatorLayout coordinator_layout;
    ArrayList<AccountDataObject> DataObjects;
    LayoutInflater inflater;

    public AccountCustomAdapter(Context c, ArrayList<AccountDataObject> DataObjects, CoordinatorLayout coordinator_layout) {
        this.c = c;
        this.DataObjects = DataObjects;
        this.coordinator_layout=coordinator_layout;

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
            convertView = inflater.inflate(R.layout.account_model,parent,false);
        }
        TextView nameTxt = (TextView) convertView.findViewById(R.id.namee);
        ImageView  img = (ImageView) convertView.findViewById(R.id.image);
        TextView location = (TextView) convertView.findViewById(R.id.AccLoc);
        TextView venueTxt = (TextView) convertView.findViewById(R.id.venue);
        TextView dateTxt = (TextView) convertView.findViewById(R.id.date);

        final AccountDataObject e = (AccountDataObject) this.getItem(position);

        nameTxt.setText(e.getName());
        venueTxt.setText(e.getVenue());
        dateTxt.setText(e.getDate());
        location.setText(e.getLocation());
       // PicassoClient.downloadImage(c,e.getImageUrl(),img);

        Glide.with(c)   // pass Context
                .load(e.getImageUrl())    // pass the image url
                .centerCrop() // optional scaletype
                //.placeholder(R.drawable.concert) // optional placeholder
                .crossFade()//optional - to enable image crossfading
                .transform(new CircleTransform(c))
                .into(img);

        int price1 = e.getAproved();
        if(price1 == 1){
            nameTxt.setTextColor(Color.RED);
            venueTxt.setTextColor(Color.RED);
            dateTxt.setTextColor(Color.RED);
            location.setTextColor(Color.RED);

        }else{

            nameTxt.setTextColor(Color.parseColor("#1b5e20"));
            venueTxt.setTextColor(Color.parseColor("#1b5e20"));
            dateTxt.setTextColor(Color.parseColor("#1b5e20"));
            location.setTextColor(Color.parseColor("#1b5e20"));

        }


        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater li = LayoutInflater.from(c);
                View promptsView = li.inflate(R.layout.android_user_input_dialog, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(c);

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);

                final EditText userInput = (EditText) promptsView
                        .findViewById(R.id.userInputDialog);

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        // get user input and set it to result
                                        // edit text

                                        preferences = PreferenceManager.getDefaultSharedPreferences(c);
                                        String password = preferences.getString("Password","");

                                        String x = userInput.getText().toString();

                                        if(x.equals(password)) {

                                            openAccountDetailActivity(e.getName(), e.getVenue(), e.getLocation(), e.getDate(), e.getCategory(), e.getAproved(), e.getCode(), e.getImageUrl());

                                        }else{

                                            Snackbar snackbar = Snackbar
                                                    .make(coordinator_layout,"Wrong pin", Snackbar.LENGTH_LONG)
                                                    .setAction("Please try Again", new View.OnClickListener() {
                                                        @Override
                                                        public void onClick(View view) {
                                                        }
                                                    });

// Changing message text color
                                            snackbar.setActionTextColor(Color.RED);

// Changing action button text color
                                            View sbView = snackbar.getView();
                                            TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                                            textView.setTextColor(Color.YELLOW);
                                            snackbar.show();

                                        }

                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();



            }
        });
        return convertView;
    }

    private  void openAccountDetailActivity(String name,String venue, String location, String date, String category, int price, String code, String imageUrl){
        Intent i = new Intent(c,AccountDetails.class);

        i.putExtra("NAME_KEY", name);
        i.putExtra("VENUE_KEY", venue);
        i.putExtra("LOCATION_KEY", location);
        i.putExtra("DATE_KEY", date);
        i.putExtra("CATEGORY", category);
        i.putExtra("PRICE_KEY", price);
        i.putExtra("CODE", code);
        i.putExtra("IMAGEURL", imageUrl);

        c.startActivity(i);

    }

}  /*preferences = PreferenceManager.getDefaultSharedPreferences(c);
        String per = preferences.getString("Name","");

        //Toast.makeText(c,input,Toast.LENGTH_LONG).show();

        if (!per.equalsIgnoreCase(input)){
        openDetailActivity(e.getId(),e.getName(), e.getVenue(),e.getDate(),e.getDescription(),e.getImageUrl(),e.getTicket_Ordinary(),e.getTicket_Vip(),e.getTicket_Vvip(),e.getTicket_Special(),e.getTicket_Id());

        }else {
        Toast.makeText(c,"wrong pin",Toast.LENGTH_SHORT).show();
        }*/
///"https://yourlink.com/database.php?clickeditem=" + variable;

//in the php
//$clickeditem = $_GET['clickeditem'];
//$strSQL = "SELECT * FROM table WHERE clickeditem = '$clickeditem'";