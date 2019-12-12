package com.bdsoftwaresolution.watchtolearn.Fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
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
import com.google.firebase.auth.FirebaseUser;

import static com.bdsoftwaresolution.watchtolearn.Config.staticValue.emailPattern;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment {


    public SignUpFragment() {
        // Required empty public constructor
    }

    private Button signup;
    private FirebaseAuth firebaseAuth;
    private EditText email,pass;
    String semail,spass;
    private ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        casting(view);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkNullValue();
            }
        });
        return view;

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
        firebaseAuth.createUserWithEmailAndPassword(semail,spass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
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
        Intent intent = new Intent(getActivity(),CompeleteProfileActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    private void casting(View view) {
        signup = view.findViewById(R.id.signUpBtn);
        email = view.findViewById(R.id.emailET);
        pass = view.findViewById(R.id.passET);
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle(getString(R.string.progress_dialoge_title));
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            updateUI();
        } else {
            Toast.makeText(getActivity(), getString(R.string.welcome_message), Toast.LENGTH_SHORT).show();
        }

    }
}
