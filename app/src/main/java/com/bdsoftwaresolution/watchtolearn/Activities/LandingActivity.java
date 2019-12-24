package com.bdsoftwaresolution.watchtolearn.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bdsoftwaresolution.watchtolearn.R;
import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class LandingActivity extends AppCompatActivity {

    private LinearLayout tasklinear;
    private CircleImageView landingprofile;
    private TextView name, mobile, balance;
    private ImageView activity;
    private FirebaseAuth firebaseAuth;
    String cUser;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        casting();
        tasklinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LandingActivity.this,VideoListActivity.class));
            }
        });
    }

    private void casting() {
        landingprofile = findViewById(R.id.landing_profile_image);
        name = findViewById(R.id.landing_user_name);
        mobile = findViewById(R.id.lading_user_number);
        balance = findViewById(R.id.landing_user_balance);
        tasklinear = findViewById(R.id.taskLinearID);
        activity = findViewById(R.id.activity);
        firebaseAuth = FirebaseAuth.getInstance();
        cUser = firebaseAuth.getCurrentUser().getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.child(cUser).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String sname = dataSnapshot.child("User_Name").getValue().toString();
                    name.setText("Name: "+sname);
                    String snumber = dataSnapshot.child("User_Number").getValue().toString();
                    String sbalance = dataSnapshot.child("User_Balance").getValue().toString();
                    String sActivty = dataSnapshot.child("Activity").getValue().toString();
                    balance.setText("Balance: "+sbalance +"Tk");
                    mobile.setText("Number: "+snumber);
                    String sprofile = dataSnapshot.child("User_Profile_Picture").getValue().toString();
                    Glide.with(LandingActivity.this).load(sprofile).into(landingprofile);
                    if (sActivty.contains("Pending"))
                    {
                       activity.setImageResource(R.drawable.nonverifieduser);
                    }
                    else
                    {
                        activity.setImageResource(R.drawable.verifiedicon);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
