package com.example.appleworksltd.twickr.MysqlDatabase;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.appleworksltd.twickr.DataObject.DataObject;
import com.example.appleworksltd.twickr.UI.ReviewCustomAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by appleworksltd on 2/24/17.
 */

public class ReviewDataParsor extends AsyncTask<Void,Void,Boolean>{

    Context c;
    String jsonData;
    ListView lv;
    SwipeRefreshLayout swipeRefreshLayout;

    ArrayList<DataObject>  DataObjects = new ArrayList<>();

    public ReviewDataParsor(Context c, String jsonData, ListView lv, SwipeRefreshLayout swipeRefreshLayout) {
        this.c = c;
        this.jsonData = jsonData;
        this.lv = lv;
        this.swipeRefreshLayout = swipeRefreshLayout;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (!swipeRefreshLayout.isRefreshing()){
            swipeRefreshLayout.setRefreshing(true);
        }

    }

    @Override
    protected Boolean doInBackground(Void... params) {
        return this.ParseData();
    }

    @Override
    protected void onPostExecute(Boolean parsed) {
        super.onPostExecute(parsed);
        swipeRefreshLayout.setRefreshing(false);

        if (parsed){
           // ReviewCustomAdapter adapter = new ReviewCustomAdapter(c,DataObjects);
            ReviewCustomAdapter adapter = new ReviewCustomAdapter(c,DataObjects);
            lv.setAdapter(adapter);



        }
        else{
            Toast.makeText(c,"Unable to parse Data",Toast.LENGTH_SHORT).show();
        }
    }

    private Boolean ParseData(){
        try {
            JSONArray ja = new JSONArray(jsonData);
            JSONObject jo;

            DataObjects.clear();
            DataObject eventsDataObject;

            for(int i=0;i<ja.length();i++){
                jo = ja.getJSONObject(i);

                
                String rname = jo.getString("Name");
                String attendence = jo.getString("Attedence");
                String rvenue = jo.getString("Venue");
                String rlocation = jo.getString("Location");
                String rdate = jo.getString("Date");
                String rimageUrl1 = jo.getString("ImageUrl1");
                String desc1 = jo.getString("Desc1");
                String rimageUrl2 = jo.getString("ImageUrl2");
                String desc2 = jo.getString("Desc2");
                String rimageUrl3 = jo.getString("ImageUrl3");
                String desc3 = jo.getString("Desc3");
                String rimageUrl4 = jo.getString("ImageUrl4");
                String desc4 = jo.getString("Desc4");
                String rimageUrl5 = jo.getString("ImageUrl5");
                String desc5 = jo.getString("Desc5");
                int rating = jo.getInt("Rating");

                eventsDataObject = new DataObject();


                eventsDataObject.setRName(rname);
                eventsDataObject.setAttendence(attendence);
                eventsDataObject.setRVenue(rvenue);
                eventsDataObject.setRLocation(rlocation);
                eventsDataObject.setRDate(rdate);
                eventsDataObject.setImageUrl1(rimageUrl1);
                eventsDataObject.setDesc1(desc1);
                eventsDataObject.setImageUrl2(rimageUrl2);
                eventsDataObject.setDesc2(desc2);
                eventsDataObject.setImageUrl3(rimageUrl3);
                eventsDataObject.setDesc3(desc3);
                eventsDataObject.setImageUrl4(rimageUrl4);
                eventsDataObject.setDesc4(desc4);
                eventsDataObject.setImageUrl5(rimageUrl5);
                eventsDataObject.setDesc5(desc5);
                eventsDataObject.setRating(rating);


                DataObjects.add(eventsDataObject);
            }
            return true;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return false;
    }
}
