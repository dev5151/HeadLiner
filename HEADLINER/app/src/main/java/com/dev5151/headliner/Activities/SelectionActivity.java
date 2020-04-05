package com.dev5151.headliner.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;
import com.dev5151.headliner.R;

public class SelectionActivity extends AppCompatActivity {

    LottieAnimationView animationViewHeadLines,animationViewWorldNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        animationViewHeadLines=findViewById(R.id.animation_view_headlines);
        animationViewWorldNews=findViewById(R.id.animation_view_world);

        animationViewWorldNews.playAnimation();
        animationViewHeadLines.playAnimation();

        animationViewHeadLines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SelectionActivity.this,HeadLineActivity.class));
            }
        });

        animationViewWorldNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SelectionActivity.this,DetailNewsActivity.class));
            }
        });




    }
}
