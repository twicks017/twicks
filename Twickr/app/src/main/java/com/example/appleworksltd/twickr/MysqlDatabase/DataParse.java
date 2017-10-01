package com.example.appleworksltd.twickr.MysqlDatabase;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.appleworksltd.twickr.DataObject.DataObjectt;
import com.example.appleworksltd.twickr.MainFiles.CustomAdaptee;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by appleworksltd on 2/24/17.
 */

public class DataParse extends AsyncTask<Void,Void,Boolean>{

    Context c;
    String jsonData;
    RecyclerView mRecyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    ArrayList<DataObjectt> dataObjectts = new ArrayList<>();

    public DataParse(Context c, String jsonData, RecyclerView mRecyclerView ) {
        this.c = c;
        this.jsonData = jsonData;
        this.mRecyclerView = mRecyclerView;

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
            CustomAdaptee adapter = new CustomAdaptee(c, dataObjectts);
            mRecyclerView.setAdapter(adapter);



        }
        else{
            Toast.makeText(c, "Unable to parse data", Toast.LENGTH_SHORT).show();

        }
    }

    private Boolean ParseData(){
        try {
            JSONArray ja = new JSONArray(jsonData);
            JSONObject jo;

            dataObjectts.clear();
            DataObjectt eventsDataObjectt;

            for(int i=0;i<ja.length();i++){
                jo = ja.getJSONObject(i);

                int id = jo.getInt("Id");
                String name = jo.getString("Name");
                String venue = jo.getString("Venue");
                String location = jo.getString("Location");
                String date = jo.getString("Date");
                String description = jo.getString("Description");
                String imageUrl = jo.getString("ImageUrl");
                int ticket_ordinary = jo.getInt("Ordinary");
                int ticket_vip = jo.getInt("VIP");
                int ticket_vvip = jo.getInt("VVIP");
                int ticket_special = jo.getInt("Special");


                eventsDataObjectt = new DataObjectt();

                eventsDataObjectt.setId(id);
                eventsDataObjectt.setName(name);
                eventsDataObjectt.setVenue(venue);
                eventsDataObjectt.setLocation(location);
                eventsDataObjectt.setDate(date);
                eventsDataObjectt.setDescription(description);
                eventsDataObjectt.setImageUrl(imageUrl);

                eventsDataObjectt.setTicket_Ordinary(ticket_ordinary);
                eventsDataObjectt.setTicket_Vip(ticket_vip);
                eventsDataObjectt.setTicket_Vvip(ticket_vvip);
                eventsDataObjectt.setTicket_Special(ticket_special);


                dataObjectts.add(eventsDataObjectt);
            }
            return true;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return false;
    }
}
