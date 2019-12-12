package com.bdsoftwaresolution.watchtolearn.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bdsoftwaresolution.watchtolearn.R;

public class VideoListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list);
    }

    public void playavideo(View view) {
        startActivity(new Intent(this,PlayerActivity.class));
    }
}
