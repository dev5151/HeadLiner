package com.dev5151.headliner.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.azoft.carousellayoutmanager.CarouselLayoutManager;
import com.azoft.carousellayoutmanager.CarouselZoomPostLayoutListener;
import com.azoft.carousellayoutmanager.CenterScrollListener;
import com.dev5151.headliner.Adapters.NewsAdapter;
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

public class MainActivity extends AppCompatActivity {

    private NewsAdapter newsAdapter;
    private RecyclerView recyclerView;
    private Retrofit retrofit;
    private List<News> news, list;
    private TextView tv;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        fetchNews();

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SelectionActivity.class));
                finish();
            }
        });
    }

    private void initView() {
        list = new ArrayList<>();
        news = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        tv = findViewById(R.id.textView);
        progressBar = findViewById(R.id.progressBar);
    }

    private void fetchNews() {

        retrofit = new Retrofit.Builder()
                .baseUrl("https://e9969264-fc46-47b7-b347-8b701969d717.mock.pstmn.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NewsApi newsApi = retrofit.create(NewsApi.class);

        final Call<List<News>> newsList = newsApi.getNews();

        newsList.enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Code: " + response.code(), Toast.LENGTH_LONG).show();
                    Log.e("Retrofit", "Code: " + response.code());
                } else {
                    news = response.body();
                    progressBar.setVisibility(View.GONE);
                    list.addAll(news);
                }
                newsAdapter = new NewsAdapter(list, getApplicationContext());
                recyclerView.setAdapter(newsAdapter);
                final CarouselLayoutManager layoutManager = new CarouselLayoutManager(CarouselLayoutManager.HORIZONTAL, true);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setHasFixedSize(true);
                recyclerView.addOnScrollListener(new CenterScrollListener());
                layoutManager.setPostLayoutListener(new CarouselZoomPostLayoutListener());
                layoutManager.canScrollHorizontally();
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "" + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("FAIL", "" + t.getMessage());
            }
        });


    }
}
