package com.example.appleworksltd.twickr.MysqlDatabase;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appleworksltd.twickr.MainFiles.Main2Activity;

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

public class Sender extends AsyncTask<Void, Void,String> {

    Context c;
    String urlAddress;
    EditText fnameTxt,lnameTxt,usernameTxt,passwordTxt,confPassTxt,phoneTxt,locationTxt;
    String fname,lname,username,password,confPass,location,phone;

    CoordinatorLayout coordinator_layout;

    ProgressDialog pd;

    public Sender(Context c, String urlAddress, CoordinatorLayout coordinator_layout, EditText...editTexts) {
        this.c = c;
        this.urlAddress = urlAddress;

        this.fnameTxt = editTexts[0];
        this.lnameTxt = editTexts[1];
        this.usernameTxt = editTexts[2];
        this.passwordTxt = editTexts[3];
        this.confPassTxt = editTexts[4];
        this.phoneTxt =editTexts[5];
        this.locationTxt = editTexts[6];
        this.coordinator_layout=coordinator_layout;

        fname = fnameTxt.getText().toString();
        lname = lnameTxt.getText().toString();
        username = usernameTxt.getText().toString();
        password = passwordTxt.getText().toString();
        confPass = confPassTxt.getText().toString();
        phone = phoneTxt.getText().toString();
        location = locationTxt.getText().toString();

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pd = new ProgressDialog(c);
        pd.setCancelable(false);
        pd.setTitle("Account Initiation");
        pd.setMessage("Creating User Account");
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

            if(s.equals("You have successfully registered")) {

                Toast.makeText(c, "Account created successfully", Toast.LENGTH_LONG).show();

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(c);
                SharedPreferences.Editor editor = preferences.edit();
                String x = passwordTxt.getText().toString();
                String name = usernameTxt.getText().toString();
                editor.putString("Password", x);
                editor.putString("Username", name);
                editor.putString("Phone", phone);
                editor.putString("Location", location);
                editor.apply();

                //Toast.makeText(c,x,Toast.LENGTH_LONG).show();


                Snackbar snackbar = Snackbar
                        .make(coordinator_layout, s, Snackbar.LENGTH_LONG)
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

                Intent i = new Intent(c, Main2Activity.class);
                c.startActivity(i);

                fnameTxt.setText("");
                lnameTxt.setText("");
                usernameTxt.setText("");
                passwordTxt.setText("");
                confPassTxt.setText("");
                phoneTxt.setText("");
                locationTxt.setText("");
            }else{
                Toast.makeText(c,s,Toast.LENGTH_LONG).show();
            }

        }else{
            Snackbar snackbar = Snackbar
                    .make(coordinator_layout,"Network issues", Snackbar.LENGTH_LONG)
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
        }
    }

    private String send(){
        HttpURLConnection con = Connector1.connect(urlAddress);

        if (con == null){
            return null;
        }

        try {
            OutputStream os = con.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));

                bw.write(new UserPackager(fname, lname, username, password, phone, location).packData());



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
