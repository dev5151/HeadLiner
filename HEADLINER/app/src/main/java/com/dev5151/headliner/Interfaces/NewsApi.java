package com.dev5151.headliner.Interfaces;

import com.dev5151.headliner.Models.News;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApi {

    @GET("/all")
    Call<List<News>> getNews();

}
