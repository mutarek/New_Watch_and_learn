package com.bdsoftwaresolution.watchtolearn.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bdsoftwaresolution.watchtolearn.Adapters.VideoAdapters;
import com.bdsoftwaresolution.watchtolearn.Interfaces.EachVideoClick;
import com.bdsoftwaresolution.watchtolearn.Models.VideoModel;
import com.bdsoftwaresolution.watchtolearn.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class VideoListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<VideoModel> modelList;
    private VideoAdapters adapters;
    private Context context;
    private EachVideoClick eachVideoClick;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list);
        casting();
        initRecyller();

    }

    private void initRecyller() {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        VideoModel videoModel = ds.getValue(VideoModel.class);
                        modelList.add(videoModel);
                    }
                    adapters = new VideoAdapters(modelList, context, eachVideoClick);


                    recyclerView.setAdapter(adapters);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void casting() {
        recyclerView = findViewById(R.id.video_list_recyller);
        modelList = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Tasks");
        recyclerView.hasFixedSize();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }


}
