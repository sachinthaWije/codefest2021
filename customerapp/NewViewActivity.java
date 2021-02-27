package com.lec.customerapp;

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
import com.lec.customerapp.holder.EventHolder;
import com.lec.customerapp.mdel.Event;

public class NewViewActivity extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    RecyclerView eventList;
    private FirestoreRecyclerAdapter<Event, EventHolder> recyclerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_view);

        eventList=findViewById(R.id.list);
        eventList.setLayoutManager(new LinearLayoutManager(this));
        Query loadJobs = db.collection("event");
        FirestoreRecyclerOptions recyclerOptions = new FirestoreRecyclerOptions.Builder<Event>().setQuery(loadJobs, Event.class).build();

        recyclerAdapter=new FirestoreRecyclerAdapter<Event, EventHolder>(recyclerOptions) {
            @Override
            protected void onBindViewHolder(@NonNull EventHolder holder, int position, @NonNull Event model) {

                holder.news_name.setText(model.getName());
                holder.lat.setText(Double.toString(model.getLat()));
                holder.log.setText(Double.toString(model.getLog()));
            }

            @NonNull
            @Override
            public EventHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_item, parent, false);
                return new EventHolder(view);
            }
        };

        eventList.setAdapter(recyclerAdapter);
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