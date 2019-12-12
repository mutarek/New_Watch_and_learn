package com.bdsoftwaresolution.watchtolearn.Activities.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bdsoftwaresolution.watchtolearn.Activities.VideoListActivity;
import com.bdsoftwaresolution.watchtolearn.R;


public class HomeFragment extends Fragment {

    private LinearLayout taskLinerar;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        casting(root);
        onclickMethod();
        return root;
    }

    private void onclickMethod() {
        taskLinerar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), VideoListActivity.class));
            }
        });
    }

    private void casting(View root) {
        taskLinerar = root.findViewById(R.id.taskLinearID);
    }
}