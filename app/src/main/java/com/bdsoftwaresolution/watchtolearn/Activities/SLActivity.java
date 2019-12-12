package com.bdsoftwaresolution.watchtolearn.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.bdsoftwaresolution.watchtolearn.Fragments.LoginkFragment;
import com.bdsoftwaresolution.watchtolearn.Fragments.SignUpFragment;
import com.bdsoftwaresolution.watchtolearn.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SLActivity extends AppCompatActivity {
    private BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sl);
        navigation = findViewById(R.id.nav_view);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        loadSignUpFragment();
    }

    private void loadSignUpFragment() {
        SignUpFragment signUpFragment = new SignUpFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frame_cointainer,signUpFragment);
        fragmentTransaction.commit();
        fragmentTransaction.addToBackStack(null);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.signUpID:
                    loadSignUpFragment();
                    return true;
                case R.id.Login:
                    loadLoginFragment();
                    return true;
            }
            return false;
        }
    };

    private void loadLoginFragment() {
        LoginkFragment signUpFragment = new LoginkFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frame_cointainer,signUpFragment);
        fragmentTransaction.commit();
        fragmentTransaction.addToBackStack(null);
    }
}
