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

public class VideoAdapters extends RecyclerView.Adapter<VideoAdapters.VideoViewHolder> {
    private EachVideoClick eachVideoClick;
    private List<VideoModel> modelList;
    private Context mContext;

    public VideoAdapters(EachVideoClick eachVideoClick, List<VideoModel> modelList, Context mContext) {
        this.eachVideoClick = eachVideoClick;
        this.modelList = modelList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, final int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        final View view = layoutInflater.inflate(R.layout.sample_video_list,parent,false);
        final VideoViewHolder videoViewHolder = new VideoViewHolder(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eachVideoClick.OnVideoClick(view,videoViewHolder.getAdapterPosition());
            }
        });

        return videoViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        holder.title.setText(modelList.get(position).getVideoTitle());
        holder.desc.setText(modelList.get(position).getVideoDesc());
        holder.category.setText(modelList.get(position).getVideoCategory());
        holder.tk.setText(modelList.get(position).getVideoTk());
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder {

        private TextView category,title,desc,tk;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);

            category = itemView.findViewById(R.id.sampleCategoryID);
            title = itemView.findViewById(R.id.sampleTitleID);
            desc = itemView.findViewById(R.id.sampleDescID);
            tk = itemView.findViewById(R.id.sampleRewardID);
        }
    }
}
