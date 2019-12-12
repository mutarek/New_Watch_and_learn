package com.bdsoftwaresolution.watchtolearn.Fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bdsoftwaresolution.watchtolearn.Activities.CompeleteProfileActivity;
import com.bdsoftwaresolution.watchtolearn.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginkFragment extends Fragment {


    public LoginkFragment() {
        // Required empty public constructor
    }

    private Button login;
    private FirebaseAuth firebaseAuth;
    private EditText email,pass;
    String semail,spass;
    private ProgressDialog progressDialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_logink, container, false);
        casting(view);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkNullValue();
            }
        });



        return view;

    }

    private void casting(View view) {
        email = view.findViewById(R.id.login_emailET);
        pass = view.findViewById(R.id.login_passET);
        login = view.findViewById(R.id.loginBTN);
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Please Wait......");
    }


    private void checkNullValue() {
        semail = email.getText().toString();
        spass = pass.getText().toString();
        if (semail.isEmpty())
        {
            email.setError(getString(R.string.email_error));
            email.setFocusable(true);
        }

        else if (spass.isEmpty())
        {
            pass.setError(getString(R.string.password_error));
            pass.setFocusable(true);
        }
        else
        {
            createuser(semail,spass);
            progressDialog.show();
        }
    }

    private void createuser(String semail, String spass) {
        firebaseAuth.signInWithEmailAndPassword(semail,spass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    Toast.makeText(getActivity(), "Successfully Account Created", Toast.LENGTH_SHORT).show();
                    updateUI();
                }
                else
                {
                    Toast.makeText(getActivity(), ""+task.getException(), Toast.LENGTH_SHORT).show();
                }

            }

        });
    }

    private void updateUI() {
        Intent intent = new Intent(getActivity(), CompeleteProfileActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

}
