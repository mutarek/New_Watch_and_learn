package com.bdsoftwaresolution.watchtolearn.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bdsoftwaresolution.watchtolearn.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;

public class CompeleteProfileActivity extends AppCompatActivity {

    private CircleImageView userprofile;
    private EditText uname, unumber, ucode, urefer;
    private Button finishbtn;
    String username, usernumber, usercode, userRefer;
    private Uri filePath;

    private final int PICK_IMAGE_REQUEST = 10;

    //Firebase
    private StorageReference storageReference;
    private FirebaseAuth firebaseAuth;
    String cUser;
    private DatabaseReference databaseReference;
    String downloaduri;
    ProgressDialog progressDialog ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compelete_profile);
        casting();
        userprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
            }
        });

        finishbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotomain(v);
            }
        });

    }

    private void checkprofiledoneornot() {
        databaseReference.child("Users").child(cUser).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("User_Name"))
                {
                    updateUi();
                }
                else
                {
                    Toasty.error(CompeleteProfileActivity.this, "This is an error toast.", Toast.LENGTH_SHORT, true).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void updateUi() {
        startActivity(new Intent(CompeleteProfileActivity.this,LandingActivity.class));
        finish();
    }

    private void casting() {
        userprofile = findViewById(R.id.user_profile_image);
        uname = findViewById(R.id.user_nameET);
        unumber = findViewById(R.id.user_numberET);
        ucode = findViewById(R.id.user_u_codeET);
        urefer = findViewById(R.id.user_refer_code);
        finishbtn = findViewById(R.id.finishBTN);
        firebaseAuth = FirebaseAuth.getInstance();
        cUser = firebaseAuth.getCurrentUser().getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        storageReference = FirebaseStorage.getInstance().getReference().child("Profile_Picture");
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading.....");


    }

    public void gotomain(View view) {
        username = uname.getText().toString();
        usercode = ucode.getText().toString();
        usernumber = unumber.getText().toString();
        userRefer = urefer.getText().toString();
        if (username.isEmpty()) {
            Toast.makeText(this, "User Name is mandatory ", Toast.LENGTH_SHORT).show();
        } else if (usercode.isEmpty()) {
            Toast.makeText(this, "Please Enter your Code", Toast.LENGTH_SHORT).show();
        } else if (username.isEmpty()) {
            Toast.makeText(this, "Number is Mandatory", Toast.LENGTH_SHORT).show();
        } else {
            uploadInfotoDb();
            progressDialog.show();
        }
    }

    private void uploadInfotoDb() {
        uploadProfilePic();
    }

    private void uploadProfilePic() {
        final StorageReference upload = storageReference.child("image"+filePath.getLastPathSegment());
        upload.putFile(filePath).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if (task.isSuccessful())
                {
                    upload.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            downloaduri = String.valueOf(uri);
                            uploadAllInfotoDB();
                        }
                    });
                }
                else
                {
                    Toast.makeText(CompeleteProfileActivity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void uploadAllInfotoDB() {
        HashMap hashMap = new HashMap();
        hashMap.put("User_Name", username);
        hashMap.put("User_Number", usernumber);
        hashMap.put("User_Code", usercode);
        hashMap.put("User_Refer", userRefer);
        hashMap.put("User_Balance", 0);
        hashMap.put("Activity","Pending");
        hashMap.put("User_Profile_Picture", downloaduri);
        databaseReference.child("Users").child(cUser).setValue(hashMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            progressDialog.dismiss();
                            Toast.makeText(CompeleteProfileActivity.this, "Done", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(CompeleteProfileActivity.this, LandingActivity.class));
                        } else {
                            Toast.makeText(CompeleteProfileActivity.this, "" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                userprofile.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        checkprofiledoneornot();
    }
}
