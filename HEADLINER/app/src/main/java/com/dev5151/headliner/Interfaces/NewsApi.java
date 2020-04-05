package com.dev5151.headliner.Interfaces;

import com.dev5151.headliner.Models.News;
import com.dev5151.headliner.Models.NewsResponse;

import java.util.List;
import java.util.LongSummaryStatistics;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApi {

    @GET("/all")
    Call<List<News>> getNews();

    @GET("/positive")
    Call<List<News>> getPositiveNews();

    @GET("/neutral")
    Call<List<News>> getNeutralNews();

    @GET("/negative")
    Call<List<News>> getNegativeNews();

    @GET("/v2/everything")
    Call<NewsResponse> getNewsSearch(
            @Query("q") String keyword,
            @Query("sortBy") String sortBy,
            @Query("apiKey") String apiKey
    );


}
