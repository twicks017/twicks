package com.example.appleworksltd.twickr.MysqlDatabase;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

/**
 * Created by appleworksltd on 2/24/17.
 */

public class Download extends AsyncTask<Void,Void,String> {

    Context c;
    String urlAddress;
    RecyclerView mRecyclerView;
    String query;
    SwipeRefreshLayout swipeRefreshLayout;
    ImageView noNetworkImage,noDataImage;



    public Download(Context c, String urlAddress, RecyclerView mRecyclerView, ImageView...imageViews) {
        this.c = c;
        this.query = query;
        this.urlAddress = urlAddress;
        this.mRecyclerView = mRecyclerView;
        this.swipeRefreshLayout = swipeRefreshLayout;
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

        if (jsonData == null){

            //Toast.makeText(c,"Poor Network......Check your connection and try again",Toast.LENGTH_LONG).show();

            noNetworkImage.setVisibility(View.VISIBLE);

         }else{
            new DataParse(c,jsonData,mRecyclerView).execute();
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
