package com.dev5151.headliner.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.dev5151.headliner.Interfaces.HeadLineInterface;
import com.dev5151.headliner.R;

public class HeadLineActivity extends AppCompatActivity {

    HeadLineInterface headLineInterface;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head_line);

        initViews();

        headLineInterface=new HeadLineInterface() {
            @Override
            public void switchToPositiveNews() {

            }

            @Override
            public void switchToNegativeNews() {

            }

            @Override
            public void switchToNeutralNews() {

            }
        };
    }

    private void initViews(){
        fragmentManager=getSupportFragmentManager();

    }

}
