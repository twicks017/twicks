package com.example.appleworksltd.twickr.MysqlDatabase;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
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

public class AccountDownloader extends AsyncTask<Void,Void,String> {

    Context c;
    String urlAddress;
    ListView lv;
    ProgressDialog pd;
    ImageView noDataImage,noNetworkImage;
    CoordinatorLayout coordinatorLayout;

    public AccountDownloader(Context c, String urlAddress, ListView lv, CoordinatorLayout coordinatorLayout, ImageView...imageViews) {
        this.c = c;
        this.urlAddress = urlAddress;
        this.lv = lv;
        this.coordinatorLayout = coordinatorLayout;
        this.noDataImage = imageViews[0];
        this.noNetworkImage = imageViews[1];
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected String doInBackground(Void... params) {
        return this.download();
    }

    @Override
    protected void onPostExecute(String jsonData) {
        super.onPostExecute(jsonData);
        lv.setAdapter(null);

        if (jsonData == null){

            Snackbar snackbar = Snackbar
                    .make(coordinatorLayout,"Network Failure", Snackbar.LENGTH_LONG)
                    .setAction("RETRY", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            new AccountDownloader(c,urlAddress,lv,coordinatorLayout,noNetworkImage,noDataImage).execute();
                        }
                    });

// Changing message text color
            snackbar.setActionTextColor(Color.RED);

// Changing action button text color
            View sbView = snackbar.getView();
            TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.YELLOW);
            snackbar.show();

            noNetworkImage.setVisibility(View.VISIBLE);
            noDataImage.setVisibility(View.INVISIBLE);
         }else{

            if (!jsonData.contains("null")){
                new AccountDataParsor(c,jsonData,lv,coordinatorLayout).execute();
            }else {
                noNetworkImage.setVisibility(View.INVISIBLE);
                noDataImage.setVisibility(View.VISIBLE);
            }


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
