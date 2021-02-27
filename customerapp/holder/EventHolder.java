package com.lec.customerapp.holder;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lec.customerapp.R;
import com.lec.customerapp.mdel.Event;


public class EventHolder extends RecyclerView.ViewHolder {


    public TextView news_name, lat, log;

    public EventHolder(@NonNull final View itemView) {
        super(itemView);

        news_name = itemView.findViewById(R.id.news_name);
        lat = itemView.findViewById(R.id.lat);
        log = itemView.findViewById(R.id.log);


    }
}
