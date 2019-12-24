package com.bdsoftwaresolution.watchtolearn.Models;

public class VideoModel {
    private String videoTitle,videoUrl,videoDesc,videoCategory,videoTk;

    public VideoModel(String videoTitle, String videoUrl, String videoDesc, String videoCategory, String videoTk) {
        this.videoTitle = videoTitle;
        this.videoUrl = videoUrl;
        this.videoDesc = videoDesc;
        this.videoCategory = videoCategory;
        this.videoTk = videoTk;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVideoDesc() {
        return videoDesc;
    }

    public void setVideoDesc(String videoDesc) {
        this.videoDesc = videoDesc;
    }

    public String getVideoCategory() {
        return videoCategory;
    }

    public void setVideoCategory(String videoCategory) {
        this.videoCategory = videoCategory;
    }

    public String getVideoTk() {
        return videoTk;
    }

    public void setVideoTk(String videoTk) {
        this.videoTk = videoTk;
    }
}
