package com.lec.rider.holder;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lec.rider.R;
import com.lec.rider.model.Job;

public class TicketHolder extends RecyclerView.ViewHolder {


    public TextView ticket_user, ticket_title, ticket_body,ticket_type;
    Button accept;
    public Job jobId;

    public String jobDocId;
    public TicketHolder(@NonNull final View itemView) {
        super(itemView);

        ticket_user = itemView.findViewById(R.id.ticket_user);
        ticket_title = itemView.findViewById(R.id.ticket_title);
        ticket_body = itemView.findViewById(R.id.ticket_body);
        ticket_type = itemView.findViewById(R.id.ticket_type);

    }
}
