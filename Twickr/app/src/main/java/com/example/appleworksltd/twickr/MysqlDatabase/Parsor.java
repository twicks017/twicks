package com.example.appleworksltd.twickr.MysqlDatabase;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.appleworksltd.twickr.DataObject.DataObject;
import com.example.appleworksltd.twickr.UI.CustomAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by appleworksltd on 01/01/2001.
 */

public class Parsor extends AsyncTask<Void,Void,Integer> {

    Context c;
    String data;
    ListView lv;
    RecyclerView mRecyclerView;
    SwipeRefreshLayout swipeRefreshLayout;

   // ArrayList<String> names = new ArrayList<>();
    ArrayList<DataObject>  DataObjects = new ArrayList<>();



    public Parsor(Context c, String data, ListView lv,RecyclerView mRecyclerView) {
        this.c = c;
        this.data = data;
        this.mRecyclerView = mRecyclerView;
        this.lv = lv;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected Integer doInBackground(Void... params) {
        return this.Parsor();
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);

        if (integer==1){

            CustomAdapter adapter = new CustomAdapter(c,DataObjects);
            lv.setAdapter(adapter);

           // CustomAdaptee adapt = new CustomAdaptee(c,DataObjects);
           // mRecyclerView.setAdapter(adapt);

        }else{

            Toast.makeText(c, "Unable to parse Data" , Toast.LENGTH_SHORT).show();
        }
    }

    private int Parsor(){

        try {
            JSONArray ja = new JSONArray(data);
            JSONObject jo = null;

            //names.clear();
            DataObjects.clear();
            DataObject eventsDataObject;

            for (int i=0;i<ja.length();i++){
                jo = ja.getJSONObject(i);
                int id = jo.getInt("Id");
                String promoter = jo.getString("Promoter");
                String aproved = jo.getString("Aproved");
                String name = jo.getString("Name");
                String cat = jo.getString("Category");
                String venue = jo.getString("Venue");
                String location = jo.getString("Location");
                String date = jo.getString("Date");
                String description = jo.getString("Description");
                String imageUrl = jo.getString("ImageUrl");
                int ticket_ordinary = jo.getInt("Ordinary");
                int ticket_vip = jo.getInt("VIP");
                int ticket_vvip = jo.getInt("VVIP");
                int ticket_special = jo.getInt("Special");

                eventsDataObject = new DataObject();

                eventsDataObject.setId(id);
                eventsDataObject.setPromoter(promoter);
                eventsDataObject.setAproved(aproved);
                eventsDataObject.setName(name);
                eventsDataObject.setCategory(cat);
                eventsDataObject.setVenue(venue);
                eventsDataObject.setLocation(location);
                eventsDataObject.setDate(date);
                eventsDataObject.setDescription(description);
                eventsDataObject.setImageUrl(imageUrl);
                eventsDataObject.setTicket_Ordinary(ticket_ordinary);
                eventsDataObject.setTicket_Vip(ticket_vip);
                eventsDataObject.setTicket_Vvip(ticket_vvip);
                eventsDataObject.setTicket_Special(ticket_special);
                DataObjects.add(eventsDataObject);
            }

            return 1;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
