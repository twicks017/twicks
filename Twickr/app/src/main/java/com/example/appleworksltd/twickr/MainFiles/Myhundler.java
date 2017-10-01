package com.example.appleworksltd.twickr.MainFiles;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appleworksltd.twickr.R;

/**
 * Created by appleworksltd on 01/01/2001.
 */

public class Myhundler extends RecyclerView.ViewHolder implements View.OnClickListener{
    TextView textView;
    ImageView img;
    ItemClickListener itemClickListener;

    public Myhundler(View itemView) {
        super(itemView);

        textView = (TextView)itemView.findViewById(R.id.name);
        img = (ImageView)itemView.findViewById(R.id.image);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        this.itemClickListener.onItemClick();
    }

    public void setItemClickListener(ItemClickListener itemClickListener){

        this.itemClickListener = itemClickListener;
    }
}
