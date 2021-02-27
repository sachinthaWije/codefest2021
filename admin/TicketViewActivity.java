package com.lec.rider;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.lec.rider.holder.TicketHolder;
import com.lec.rider.model.Tickect;

public class TicketViewActivity extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    RecyclerView ticketList;
    private FirestoreRecyclerAdapter<Tickect, TicketHolder> recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_view);

        ticketList=findViewById(R.id.list);
        ticketList.setLayoutManager(new LinearLayoutManager(this));
//        Query loadJobs = db.collection("Job").orderBy("date");
        Query loadJobs = db.collection("Ticket");
        FirestoreRecyclerOptions recyclerOptions = new FirestoreRecyclerOptions.Builder<Tickect>().setQuery(loadJobs, Tickect.class).build();

        recyclerAdapter=new FirestoreRecyclerAdapter<Tickect, TicketHolder>(recyclerOptions) {
            @Override
            protected void onBindViewHolder(@NonNull TicketHolder holder, int position, @NonNull Tickect model) {
                holder.ticket_user.setText(model.getUserName());
                holder.ticket_title.setText(model.getTitle());
                holder.ticket_body.setText(model.getBody());
                holder.ticket_type.setText(model.getType());
            }

            @NonNull
            @Override
            public TicketHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tickect_item, parent, false);
                return new TicketHolder(view);
            }
        };

        ticketList.setAdapter(recyclerAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        recyclerAdapter.startListening();

    }

    @Override
    protected void onStop() {
        super.onStop();
        recyclerAdapter.stopListening();
    }
}