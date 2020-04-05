package com.dev5151.headliner.Adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.dev5151.headliner.Models.News;
import com.dev5151.headliner.R;

import java.util.List;

public class HeadLineAdapter extends RecyclerView.Adapter<HeadLineAdapter.HeadLineViewHolder> {

    private List<News> newsList;
    private Context context;

    public HeadLineAdapter(List<News> newsList, Context context) {
        this.newsList = newsList;
        this.context = context;
    }

    @NonNull
    @Override
    public HeadLineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.news_card_item, parent, false);
        return new HeadLineAdapter.HeadLineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HeadLineViewHolder holder, int position) {
        News news = newsList.get(position);

        Glide.with(context).load(news.getImgUrl()).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                holder.progressBar.setVisibility(View.GONE);
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                holder.progressBar.setVisibility(View.GONE);
                return false;
            }
        }).into(holder.newsImg);

        holder.title.setText(news.getTitle());
        holder.content.setText(news.getContent());

    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class HeadLineViewHolder extends RecyclerView.ViewHolder {

        ImageView newsImg;
        TextView title, content;
        ProgressBar progressBar;

        public HeadLineViewHolder(@NonNull View itemView) {
            super(itemView);

            newsImg = itemView.findViewById(R.id.img_news);
            title = itemView.findViewById(R.id.title);
            content = itemView.findViewById(R.id.content);
            progressBar = itemView.findViewById(R.id.progressBar);

        }
    }
}
