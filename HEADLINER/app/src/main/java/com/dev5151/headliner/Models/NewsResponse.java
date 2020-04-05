package com.dev5151.headliner.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsResponse {

    @SerializedName("articles")
    private List<News> articleList;

    public List<News> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<News> articleList) {
        this.articleList = articleList;
    }

    public NewsResponse(List<News> articleList) {
        this.articleList = articleList;
    }
}
