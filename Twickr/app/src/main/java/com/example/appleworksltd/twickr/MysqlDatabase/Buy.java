package com.example.appleworksltd.twickr.MysqlDatabase;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

/**
 * Created by appleworksltd on 20/03/2017.
 */

public class Buy extends AsyncTask<Void, Void,String> {

    Context c;
    String urlAddress1;
    int s;
    String name;String venue;String date;String classe;String promoter;String username;String location;String code;String image;


    ProgressDialog pd;

    public Buy(Context c, String urlAddress1, int s, String name, String venue,String location, String date, String classe,String promoter, String username,String code,String image) {
        this.c = c;
        this.urlAddress1 = urlAddress1;
        this.s = s;
        this.name = name;
        this.venue = venue;
        this.location = location;
        this.date = date;
        this.classe = classe;
        this.promoter = promoter;
        this.username = username;
        this.code = code;
        this.image = image;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pd = new ProgressDialog(c);
        pd.setTitle("Buying Ticket");
        pd.setCancelable(false);
        pd.setMessage("Purchasing Ticket...please wait");
        pd.show();
    }

    @Override
    protected String doInBackground(Void... params) {
        return this.send();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        pd.dismiss();

        if(s != null){
            Toast.makeText(c,s,Toast.LENGTH_LONG).show();

        }else{
            Toast.makeText(c,"Unsuccessful",Toast.LENGTH_LONG);
        }
    }

    private String send(){
        HttpURLConnection con = Connector1.connect(urlAddress1);

        if (con == null){
            return null;
        }

        try {
            OutputStream os = con.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
            bw.write(new TicketPackager(s,name,venue,location,date,classe,promoter,username,code,image).packData());

            bw.flush();
            bw.close();
            os.close();

            int reponseCode = con.getResponseCode();

            if (reponseCode == con.HTTP_OK) {

                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuffer response = new StringBuffer();

                String line = null;

                while ((line = br.readLine()) != null) {
                    response.append(line);
                }
                br.close();
                return response.toString();

            }
            else {

            }
        }

        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
