package com.dev5151.headliner.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.transition.Fade;

import com.dev5151.headliner.Adapters.ViewPagerAdapter;
import com.dev5151.headliner.Fragments.NegativeNewsFragment;
import com.dev5151.headliner.Fragments.NeutralNewsFragment;
import com.dev5151.headliner.Fragments.PositiveNewsFragment;
import com.dev5151.headliner.Interfaces.HeadLineInterface;
import com.dev5151.headliner.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class HeadLineActivity extends AppCompatActivity {

    FragmentManager fragmentManager;
    private List<Fragment> fragmentList;
    private List<String> titleList;
    private TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head_line);
        initViews();

        fragmentList.add(new PositiveNewsFragment());
        fragmentList.add(new NegativeNewsFragment());
        fragmentList.add(new NeutralNewsFragment());

        titleList.add("Positive News");
        titleList.add("Negative News");
        titleList.add("Neutral News");

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), fragmentList, titleList);

        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);

    }

    private void initViews() {
        fragmentManager = getSupportFragmentManager();
        tabLayout = findViewById(R.id.tab_layout);
        fragmentList = new ArrayList<>();
        titleList = new ArrayList<>();
        viewPager = findViewById(R.id.view_pager);

    }


}
