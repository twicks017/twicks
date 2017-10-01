package com.example.appleworksltd.twickr.MysqlDatabase;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

/**
 * Created by appleworksltd on 01/01/2001.
 */

public class SenderReciever extends AsyncTask<Void,Void,String> {

    Context c;
    String urlAddress;
    String query;
    String cat;
    ListView lv;
    ImageView noDataImg,noNetworkImage;
    RecyclerView mRecyclerView;

    public SenderReciever(Context c, String urlAddress, String query,String cat, ListView lv, RecyclerView mRecyclerView, ImageView...imageViews) {
        this.c = c;
        this.urlAddress = urlAddress;
        this.query = query;
        this.cat = cat;
        this.lv = lv;
        this.mRecyclerView=mRecyclerView;
        this.noDataImg = imageViews[0];
        this.noNetworkImage = imageViews[1];
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected String doInBackground(Void... params) {
        return this.sendandRecieve();
    }

    @Override
    protected void onPostExecute(String s) {

        super.onPostExecute(s);


        lv.setAdapter(null);

        if (s != null){

            if (!s.contains("null")){
                Parsor p = new Parsor(c,s,lv,mRecyclerView);
                p.execute();
            }else {
                noNetworkImage.setVisibility(View.INVISIBLE);
                noDataImg.setVisibility(View.VISIBLE);
            }
        }else{
            noNetworkImage.setVisibility(View.VISIBLE);
            noDataImg.setVisibility(View.INVISIBLE);
        }
    }

    private String sendandRecieve(){

        HttpURLConnection con = Connector.connect(urlAddress);

        if (con==null){
            return null;
        }


        try {
            OutputStream os = con.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
            bw.write(new SearchDataPackager(query,cat).packager());

            bw.flush();
            bw.close();
            os.close();

            int responseCode = con.getResponseCode();
            if (responseCode==con.HTTP_OK){

                InputStream is = con.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String line;

                StringBuffer response = new StringBuffer();

                if (br != null){
                    while ((line=br.readLine()) != null){
                        response.append(line+ "\n");
                    }
                }else {return null;}

                return response.toString();


            }else {
                return String.valueOf(responseCode);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


      return  null;
    }
}
