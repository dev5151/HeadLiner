package com.dev5151.headliner.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dev5151.headliner.Activities.DetailNewsActivity;
import com.dev5151.headliner.Interfaces.FetchNewsInterface;
import com.dev5151.headliner.Models.FabModel;
import com.dev5151.headliner.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class FabAdapter extends RecyclerView.Adapter<FabAdapter.FavViewHolder> {

    List<FabModel> fabList;
    Context context;
    FetchNewsInterface fetchNewsInterface;
    com.google.android.material.floatingactionbutton.FloatingActionButton floatingActionButton;

    public FabAdapter(List<FabModel> fabList, Context context) {
        this.fabList = fabList;
        this.context = context;
    }

    @NonNull
    @Override
    public FavViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fab_item, parent, false);
        return new FabAdapter.FavViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavViewHolder holder, int position) {
        FabModel fabModel = fabList.get(position);
        holder.title.setText(fabModel.getTitle());
        holder.fab.setImageResource(fabModel.getImg());
        holder.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DetailNewsActivity.fetchNewsInterface.fetchNewsFromQuery(holder.title.getText().toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return fabList.size();
    }

    public class FavViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        ImageButton fab;

        public FavViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            fab = itemView.findViewById(R.id.fab);
        }


    }
}
