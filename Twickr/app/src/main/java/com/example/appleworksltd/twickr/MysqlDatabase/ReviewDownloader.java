package com.example.appleworksltd.twickr.MysqlDatabase;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

/**
 * Created by appleworksltd on 2/24/17.
 */

public class ReviewDownloader extends AsyncTask<Void,Void,String> {

    Context c;
    String urlAddress;
    ListView lv;

    SwipeRefreshLayout swipeRefreshLayout;
    ImageView noNetworkImage;
    CoordinatorLayout coordinator_layout;


    public ReviewDownloader(Context c, String urlAddress, ListView lv, SwipeRefreshLayout swipeRefreshLayout, CoordinatorLayout coordinator_layout, ImageView noNetworkImage) {
        this.c = c;
        this.urlAddress = urlAddress;
        this.lv = lv;
        this.coordinator_layout= coordinator_layout;
        this.swipeRefreshLayout = swipeRefreshLayout;
        this.noNetworkImage = noNetworkImage;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

       if (!swipeRefreshLayout.isRefreshing()){
            swipeRefreshLayout.setRefreshing(true);
        }


    }

    @Override
    protected String doInBackground(Void... params) {
        return this.download();
    }

    @Override
    protected void onPostExecute(String jsonData) {
        super.onPostExecute(jsonData);


        if (jsonData == null){
            swipeRefreshLayout.setRefreshing(false);
            Snackbar snackbar = Snackbar
                    .make(coordinator_layout,"Poor or No network", Snackbar.LENGTH_LONG)
                    .setAction("RETRY", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            new ReviewDownloader(c,urlAddress,lv,swipeRefreshLayout,coordinator_layout,noNetworkImage).execute();
                        }
                    });

// Changing message text color
            snackbar.setActionTextColor(Color.RED);

// Changing action button text color
            View sbView = snackbar.getView();
            TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.YELLOW);
            snackbar.show();

           /* CoordinatorLayout coordinatorLayout;
            coordinatorLayout = (CoordinatorLayout) new CoordinatorLayout(c).findViewById(R.id.coordinator_layout);


            Snackbar snackbar = Snackbar
                    .make(coordinatorLayout,"Poor Network......Check your connection and try again", Snackbar.LENGTH_LONG)
                    .setAction("RETRY", new View.OnClickListener() {
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
*/
            noNetworkImage.setVisibility(View.VISIBLE);
         }else{
            new ReviewDataParsor(c,jsonData,lv,swipeRefreshLayout).execute();
            noNetworkImage.setVisibility(View.INVISIBLE);
        }
    }

    private String download(){
        HttpURLConnection con = Connector.connect(urlAddress);
        if (con == null){return null;}

        try {
            InputStream is = new BufferedInputStream(con.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String line;
            StringBuffer jsonData = new StringBuffer();

            while ((line=br.readLine()) != null){

                jsonData.append(line+"\n");
            }
            br.close();
            is.close();
            return jsonData.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
