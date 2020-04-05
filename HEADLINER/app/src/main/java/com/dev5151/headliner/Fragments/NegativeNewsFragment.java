package com.dev5151.headliner.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dev5151.headliner.Adapters.HeadLineAdapter;
import com.dev5151.headliner.Interfaces.NewsApi;
import com.dev5151.headliner.Models.News;
import com.dev5151.headliner.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NegativeNewsFragment extends Fragment {

    Retrofit retrofit;
    RecyclerView recyclerView;
    HeadLineAdapter headLineAdapter;
    List<News> list, news;
    ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_negative_news, container, false);
        intiView(view);
        fetchNegativeNews();
        return view;

    }

    private void intiView(View view) {
        list = new ArrayList<>();
        news = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recyclerView);
        progressBar=view.findViewById(R.id.progressBar);
    }

    private void fetchNegativeNews() {

        retrofit = new Retrofit.Builder()
                .baseUrl("https://e9969264-fc46-47b7-b347-8b701969d717.mock.pstmn.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NewsApi newsApi = retrofit.create(NewsApi.class);

        final Call<List<News>> newsList = newsApi.getNegativeNews();

        newsList.enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                list.clear();
                news.clear();
                if (!response.isSuccessful()) {
                    Toast.makeText(getActivity(), "Code: " + response.code(), Toast.LENGTH_LONG).show();
                    Log.e("Retrofit", "Code: " + response.code());
                } else {
                    progressBar.setVisibility(View.INVISIBLE);
                    news = response.body();
                    list.addAll(news);
                }
                headLineAdapter = new HeadLineAdapter(list, getActivity());
                recyclerView.setAdapter(headLineAdapter);
                final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setHasFixedSize(true);
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
                Toast.makeText(getActivity(), "" + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("FAIL", "" + t.getMessage());
            }
        });

    }

}
