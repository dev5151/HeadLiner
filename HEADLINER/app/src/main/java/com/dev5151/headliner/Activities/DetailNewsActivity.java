package com.dev5151.headliner.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.dev5151.headliner.Adapters.FabAdapter;
import com.dev5151.headliner.Adapters.HeadLineAdapter;
import com.dev5151.headliner.Interfaces.ClickListener;
import com.dev5151.headliner.Interfaces.FetchNewsInterface;
import com.dev5151.headliner.Interfaces.NewsApi;
import com.dev5151.headliner.Models.FabModel;
import com.dev5151.headliner.Models.News;
import com.dev5151.headliner.Models.NewsResponse;
import com.dev5151.headliner.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailNewsActivity extends AppCompatActivity {

    androidx.appcompat.widget.SearchView searchView;
    String apiKey = "6efd3ee1e13b448a8e1c8b4c45ec0bc7";
    String sortBy = "popularity";
    Retrofit retrofit;
    NewsResponse newsResponse;
    List<News> list;
    RecyclerView recyclerView;
    RecyclerView recyclerViewBtn;
    ProgressBar progressBar;
    HeadLineAdapter headLineAdapter;
    public static FetchNewsInterface fetchNewsInterface;
    List<String> titleList;
    List<Integer> drawableList;
    List<FabModel> fabModelList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_news);

        searchView = findViewById(R.id.searchView);
        searchView.setSubmitButtonEnabled(true);
        list = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
        titleList = new ArrayList<>();
        drawableList = new ArrayList<>();
        fabModelList = new ArrayList<>();
        recyclerViewBtn = findViewById(R.id.recyclerView_btn);

        searchView.setIconifiedByDefault(false);

        addData();

        fetchNewsInterface = new FetchNewsInterface() {
            @Override
            public void fetchNewsFromQuery(String key) {
                fetchArticles(key);
            }
        };

        fetchArticles("india");

        search();


    }

    private void search() {


        searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                fetchArticles(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    public final void fetchArticles(final String keyword) {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://newsapi.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NewsApi newsApi = retrofit.create(NewsApi.class);

        final Call<NewsResponse> newsList = newsApi.getNewsSearch(keyword, sortBy, apiKey);

        newsList.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Code: " + response.code(), Toast.LENGTH_LONG).show();
                    Log.e("Retrofit", "Code: " + response.code());
                } else {
                    list.clear();
                    newsResponse = response.body();
                    //progressBar.setVisibility(View.INVISIBLE);
                    list.addAll(newsResponse.getArticleList());
                }
                headLineAdapter = new HeadLineAdapter(list, getApplicationContext());
                recyclerView.setAdapter(headLineAdapter);
                final LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(layoutManager);
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Code: " + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("Retrofit", "Code: " + t.getMessage());
            }
        });
    }

    private void addData() {
        fabModelList.add(new FabModel("cricket", R.drawable.ic_cricket));
        fabModelList.add(new FabModel("bitcoin", R.drawable.ic_bitcoin));
        fabModelList.add(new FabModel("business", R.drawable.ic_business));
        fabModelList.add(new FabModel("android", R.drawable.ic_android));
        fabModelList.add(new FabModel("technology", R.drawable.ic_tech));


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        FabAdapter fabAdapter = new FabAdapter(fabModelList, getApplicationContext());
        recyclerViewBtn.setAdapter(fabAdapter);
        recyclerViewBtn.setLayoutManager(layoutManager);
    }

    public static FetchNewsInterface getFetchNewsInterface() {
        return fetchNewsInterface;
    }
}
