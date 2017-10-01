package com.example.appleworksltd.twickr.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appleworksltd.twickr.DataObject.DataObject;
import com.example.appleworksltd.twickr.MainFiles.EventsDetails;
import com.example.appleworksltd.twickr.MainFiles.PicassoClient;
import com.example.appleworksltd.twickr.R;

import java.util.ArrayList;

/**
 * Created by appleworksltd on 2/24/17.
 */

public class CustomAdapter extends BaseAdapter {
    Context c;
    ArrayList<DataObject> DataObjects;
    LayoutInflater inflater;


    public CustomAdapter(Context c, ArrayList<DataObject> DataObjects ) {
        this.c = c;
        this.DataObjects = DataObjects;

        inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return DataObjects.size();
    }

    @Override
    public Object getItem(int position) {
        return DataObjects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = inflater.inflate(R.layout.ticket_model,parent,false);
        }
        TextView nameTxt = (TextView) convertView.findViewById(R.id.namee);
        ImageView  img = (ImageView) convertView.findViewById(R.id.image);

        TextView venueTxt = (TextView) convertView.findViewById(R.id.venue);
        TextView dateTxt = (TextView) convertView.findViewById(R.id.date);

        final DataObject e = (DataObject) this.getItem(position);

        nameTxt.setText(e.getName());
        venueTxt.setText(e.getVenue());
        dateTxt.setText(e.getDate());
        PicassoClient.downloadImage(c,e.getImageUrl(),img);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openDetailActivity(e.getId(),e.getPromoter(),e.getName(), e.getVenue(),e.getDate(),e.getDescription(),e.getImageUrl(),e.getTicket_Ordinary(),e.getTicket_Vip(),e.getTicket_Vvip(),e.getTicket_Special(),e.getTicket_Id(),e.getLocation());
            }
        });

        return convertView;
    }

    private  void openDetailActivity(int id,String promoter,String name,String venue, String date, String description, String imageUrl, int ordinary, int vip, int vvip, int special, int ticket_id,String location){
        Intent i = new Intent(c,EventsDetails.class);

        i.putExtra("Id_KEY",id);
        i.putExtra("PROMOTER",promoter);
        i.putExtra("NAME_KEY", name);
        i.putExtra("VENUE_KEY", venue);
        i.putExtra("DATE_KEY", date);
        i.putExtra("DESCRIPTION_KEY", description);
        i.putExtra("IMAGEURL_KEY", imageUrl);
        i.putExtra("ORDINARY_KEY", ordinary);
        i.putExtra("VIP_KEY", vip);
        i.putExtra("VVIP", vvip);
        i.putExtra("SPECIAL_KEY", special);
        i.putExtra("TICKET_ID_KEY", ticket_id);
        i.putExtra("LOCATION",location);


        c.startActivity(i);

    }

}
