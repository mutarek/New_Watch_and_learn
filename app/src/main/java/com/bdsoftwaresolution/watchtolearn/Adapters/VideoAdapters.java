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
    private List<VideoModel> modelList;
    private Context context;
    private EachVideoClick eachVideoClick;

    public VideoAdapters(List<VideoModel> modelList, Context context, EachVideoClick eachVideoClick) {
        this.modelList = modelList;
        this.context = context;
        this.eachVideoClick = eachVideoClick;
    }

    @NonNull
    @Override
    public VideoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.sample_video_list, parent, false);
        return new VideoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoHolder holder, int position) {
        holder.title.setText(modelList.get(position).getVideoTitle());
        holder.desc.setText(modelList.get(position).getVideoDesc());
        holder.category.setText(modelList.get(position).getVideoCategory());
        holder.tk.setText(modelList.get(position).getVideoTk());
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class VideoHolder extends RecyclerView.ViewHolder {

        private TextView category, title, desc, tk;

        public VideoHolder(@NonNull View itemView) {
            super(itemView);

            category = itemView.findViewById(R.id.sample_categoty_id);
            title = itemView.findViewById(R.id.sample_title_id);
            desc = itemView.findViewById(R.id.sampleDescID);
            tk = itemView.findViewById(R.id.sample_reward_tk);
        }
    }
}
