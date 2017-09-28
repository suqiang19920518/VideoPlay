package com.thinkive.bank.videoplay.bean;

/**
 * @author: sq
 * @date: 2017/9/27
 * @corporation: 深圳市思迪信息技术股份有限公司
 * @description: 网络视频实体类
 */
public class VIdeoItem {

    private String content;
    private int videoWidth;
    private int videoHeight;
    private String coverUrl;
    private String videoUrl;

    public VIdeoItem(String content, String cover, int video_height, String p360, int video_width) {
        this.content = content;
        this.coverUrl = cover;
        this.videoHeight = video_height;
        this.videoUrl = p360;
        this.videoWidth = video_width;
    }

    public VIdeoItem(){

    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getVideoWidth() {
        return videoWidth;
    }

    public void setVideoWidth(int videoWidth) {
        this.videoWidth = videoWidth;
    }

    public int getVideoHeight() {
        return videoHeight;
    }

    public void setVideoHeight(int videoHeight) {
        this.videoHeight = videoHeight;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
