package com.bdsoftwaresolution.watchtolearn.Adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class VideoAdapters extends RecyclerView.Adapter<VideoAdapters.VideoHolder> {
    @NonNull
    @Override
    public VideoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull VideoHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class VideoHolder extends RecyclerView.ViewHolder {

        private TextView category,title,desc,tk;

        public VideoHolder(@NonNull View itemView) {
            super(itemView);

        }
    }
}
