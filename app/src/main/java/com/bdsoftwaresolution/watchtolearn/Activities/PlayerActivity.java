package com.bdsoftwaresolution.watchtolearn.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.webkit.URLUtil;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bdsoftwaresolution.watchtolearn.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import es.dmoral.toasty.Toasty;

public class PlayerActivity extends AppCompatActivity {

    private VideoView mVideoView;
    private TextView mBufferingTextView;
    private static final String PLAYBACK_TIME = "play_time";
    private ProgressDialog progressDialog;
    String ur, currentUser;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    double oldtk, newtk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        mVideoView = findViewById(R.id.videoview);
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
        currentUser = firebaseAuth.getCurrentUser().getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
        getOldReward();
        Intent intent = getIntent();
        ur = intent.getStringExtra("videoUrl");
        progressDialog.setTitle("Loading.......");
        initializePlayer();
        MediaController mediaController = new MediaController(this);
        mVideoView.setMediaController(mediaController);
    }

    private void getOldReward() {
        databaseReference.child(currentUser).child("User_Balance").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                oldtk = Float.parseFloat(Float.valueOf(String.valueOf(dataSnapshot.getValue())).toString());
                newtk = oldtk + 11.60;

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void initializePlayer() {
        progressDialog.show();
        Uri videoUri = Uri.parse(ur);
        mVideoView.setVideoURI(videoUri);
        mVideoView.start();

        // Listener for onPrepared() event (runs after the media is prepared).
        /*mVideoView.setOnPreparedListener(
                new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {
                        progressDialog.dismiss();
                        mVideoView.start();
                    }
                });*/


        /*mVideoView.setOnCompletionListener(
                new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        //setReward();
                        Toasty.success(PlayerActivity.this,"Done").show();
                        updateUI();

                    }
                });*/
    }

   /* private void updateUI() {
        startActivity(new Intent(PlayerActivity.this,VideoListActivity.class));
    }

    private void setReward() {
        databaseReference.child(currentUser).child("User_Balance").setValue(newtk).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    //Toasty.success(getApplicationContext(), "You Get Reward").show();
                } else {
                    Toasty.error(getApplicationContext(), "" + task.getException()).show();
                }
            }
        });
    }*/


}
