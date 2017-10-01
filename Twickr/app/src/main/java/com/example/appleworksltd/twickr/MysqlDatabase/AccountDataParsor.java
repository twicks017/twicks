package com.example.appleworksltd.twickr.MysqlDatabase;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.appleworksltd.twickr.DataObject.AccountDataObject;
import com.example.appleworksltd.twickr.UI.AccountCustomAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by appleworksltd on 2/24/17.
 */

public class AccountDataParsor extends AsyncTask<Void,Void,Boolean>{

    Context c;
    String jsonData;
    ListView lv;
    CoordinatorLayout coordinator_layout;
    ArrayList<AccountDataObject>  DataObjects = new ArrayList<>();

    public AccountDataParsor(Context c, String jsonData, ListView lv, CoordinatorLayout coordinator_layout) {
        this.c = c;
        this.jsonData = jsonData;
        this.lv = lv;
        this.coordinator_layout=coordinator_layout;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected Boolean doInBackground(Void... params) {
        return this.ParseData();
    }

    @Override
    protected void onPostExecute(Boolean parsed) {
        super.onPostExecute(parsed);

        if (parsed){
            AccountCustomAdapter adapter = new AccountCustomAdapter(c,DataObjects,coordinator_layout);
            lv.setAdapter(adapter);
        }
        else{

            Snackbar snackbar = Snackbar
                    .make(coordinator_layout,"Unable to parse Data", Snackbar.LENGTH_LONG)
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

    private Boolean ParseData(){
        try {
            JSONArray ja = new JSONArray(jsonData);
            JSONObject jo;

            DataObjects.clear();
            AccountDataObject DataObject;

            for(int i=0;i<ja.length();i++){
                jo = ja.getJSONObject(i);

                String name = jo.getString("Name");
                String venue = jo.getString("Venue");
                String location = jo.getString("Location");
                String date = jo.getString("TDate");
                String category = jo.getString("Category");
                int aproved = jo.getInt("Aproved");
                String code = jo.getString("Code");
                String imageUrl = jo.getString("ImageUrl");

                DataObject = new AccountDataObject();

                DataObject.setName(name);
                DataObject.setVenue(venue);
                DataObject.setLocation(location);
                DataObject.setDate(date);
                DataObject.setCategory(category);
                DataObject.setAproved(aproved);
                DataObject.setCode(code);
                DataObject.setImageUrl(imageUrl);


                DataObjects.add(DataObject);
            }
            return true;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return false;
    }
}
