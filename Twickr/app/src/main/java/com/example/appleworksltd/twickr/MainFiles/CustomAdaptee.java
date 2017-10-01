package com.example.appleworksltd.twickr.MainFiles;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.appleworksltd.twickr.DataObject.DataObjectt;
import com.example.appleworksltd.twickr.R;

import java.util.ArrayList;

/**
 * Created by appleworksltd on 01/01/2001.
 */

public class CustomAdaptee extends RecyclerView.Adapter<Myhundler> {
    Context c;
    ArrayList<DataObjectt> dataObjectts;

    public CustomAdaptee(Context c, ArrayList<DataObjectt> dataObjectts) {
        this.c = c;
        this.dataObjectts = dataObjectts;
    }

    @Override
    public Myhundler onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ticket_model1,parent,false);

        return new Myhundler(v);
    }

    @Override
    public void onBindViewHolder(Myhundler holder, int position) {
        final DataObjectt dataObjectt = dataObjectts.get(position);
        holder.textView.setText(dataObjectt.getName());
        Glide.with(c)   // pass Context
                .load(dataObjectt.getImageUrl())    // pass the image url
                .centerCrop() // optional scaletype
                //.placeholder(R.drawable.concert) // optional placeholder
                .crossFade() //optional - to enable image crossfading
                .transform(new BorderTransformation(c))
                .into(holder.img);

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick() {
                openDetailActivity(dataObjectt.getId(), dataObjectt.getName(), dataObjectt.getVenue(), dataObjectt.getDate(), dataObjectt.getDescription(), dataObjectt.getImageUrl(), dataObjectt.getTicket_Ordinary(), dataObjectt.getTicket_Vip(), dataObjectt.getTicket_Vvip(), dataObjectt.getTicket_Special(), dataObjectt.getTicket_Id(), dataObjectt.getLocation());

            }
        });




    }



    @Override
    public int getItemCount() {
        return dataObjectts.size();
    }

    private  void openDetailActivity(int id,String name,String venue, String date, String description, String imageUrl, int ordinary, int vip, int vvip, int special, int ticket_id,String location){
        Intent i = new Intent(c,EventsDetails.class);

        i.putExtra("Id_KEY",id);
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
