package com.bdsoftwaresolution.watchtolearn.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bdsoftwaresolution.watchtolearn.Interfaces.EachVideoClick;
import com.bdsoftwaresolution.watchtolearn.Models.VideoModel;
import com.bdsoftwaresolution.watchtolearn.R;

import java.util.List;

public class VideoAdapters extends RecyclerView.Adapter<VideoAdapters.VideoHolder> {
    private List<VideoModel> videoModelList;
    private EachVideoClick eachVideoClick;
    private Context context;

    public VideoAdapters(List<VideoModel> videoModelList, EachVideoClick eachVideoClick, Context context) {
        this.videoModelList = videoModelList;
        this.eachVideoClick = eachVideoClick;
        this.context = context;
    }

    @NonNull
    @Override
    public VideoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.sample_video_layout, parent, false);
        VideoHolder videoAdapters = new VideoHolder(view);



        return videoAdapters;
    }

    @Override
    public void onBindViewHolder(@NonNull VideoHolder holder, int position) {
        holder.category.setText(videoModelList.get(position).getVideoCategory());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class VideoHolder extends RecyclerView.ViewHolder {

        private TextView category, desc, reward;

        public VideoHolder(@NonNull View itemView) {
            super(itemView);

            category = itemView.findViewById(R.id.sample_categoty_id);
            desc = itemView.findViewById(R.id.sample_title_id);
            reward = itemView.findViewById(R.id.sample_reward_tk);
        }
    }
}
