package com.dev5151.headliner.Models;

import com.google.gson.annotations.SerializedName;

public class News {

    @SerializedName("content")
    private String content;

    @SerializedName("title")
    private String title;

    @SerializedName("urlToImage")
    private String imgUrl;

    public News(String content, String title, String imgUrl) {
        this.content = content;
        this.title = title;
        this.imgUrl = imgUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
