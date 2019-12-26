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

import es.dmoral.toasty.Toasty;

public class VideoListActivity extends AppCompatActivity {

    private List<VideoModel> modelList;
    private VideoAdapters videoAdapters;
    private RecyclerView recyclerView;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list);
        recyclerView = findViewById(R.id.videoRecyller);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        recyclerView.setHasFixedSize(true);
        modelList = new ArrayList<>();
        intirecyller();

    }

    private void intirecyller() {
        databaseReference.child("Tasks").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        VideoModel videoModel = dataSnapshot1.getValue(VideoModel.class);
                        modelList.add(videoModel);
                    }

                    videoAdapters = new VideoAdapters(new EachVideoClick() {
                        @Override
                        public void OnVideoClick(View videoView, int videoPosition) {
                            Intent intent = new Intent(getApplicationContext(),PlayerActivity.class);
                            intent.putExtra("videoUrl",modelList.get(videoPosition).getVideoUrl());
                            startActivity(intent);

                        }
                    }, modelList, getApplicationContext());

                    recyclerView.setAdapter(videoAdapters);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(VideoListActivity.this);
                    recyclerView.setLayoutManager(linearLayoutManager);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(VideoListActivity.this, "" + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
