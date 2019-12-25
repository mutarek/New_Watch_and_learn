package com.bdsoftwaresolution.watchtolearn.Models;

public class VideoModel {
    String videoCategory,videoDesc,videoTitle,videoTk,videoUrl;

    public VideoModel(String videoCategory, String videoDesc, String videoTitle, String videoTk, String videoUrl) {
        this.videoCategory = videoCategory;
        this.videoDesc = videoDesc;
        this.videoTitle = videoTitle;
        this.videoTk = videoTk;
        this.videoUrl = videoUrl;
    }

    public String getVideoCategory() {
        return videoCategory;
    }

    public void setVideoCategory(String videoCategory) {
        this.videoCategory = videoCategory;
    }

    public String getVideoDesc() {
        return videoDesc;
    }

    public void setVideoDesc(String videoDesc) {
        this.videoDesc = videoDesc;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getVideoTk() {
        return videoTk;
    }

    public void setVideoTk(String videoTk) {
        this.videoTk = videoTk;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
