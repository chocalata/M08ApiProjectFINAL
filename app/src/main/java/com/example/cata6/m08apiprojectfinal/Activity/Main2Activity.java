package com.example.cata6.m08apiprojectfinal.Activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.cata6.m08apiprojectfinal.MainViewModel;
import com.example.cata6.m08apiprojectfinal.R;
import com.example.cata6.m08apiprojectfinal.SummonerOrder.AZNameListFragment;
import com.example.cata6.m08apiprojectfinal.SummonerOrder.LevelListFragment;
import com.example.cata6.m08apiprojectfinal.SummonerOrder.RankListFragment;


public class Main2Activity extends AppCompatActivity {

    public final static String soloq = "RANKED_SOLO_5x5";
    public final static String teamq = "RANKED_SOLO_5x5";
    public final static String threeq = "RANKED_SOLO_5x5";



    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        mainViewModel = ViewModelProviders.of(Main2Activity.this).get(MainViewModel.class);

        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main2Activity.this, NewSummonerActivity.class));
            }
        });

    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0: return new AZNameListFragment();
                case 1: return new LevelListFragment();
                case 2: return new RankListFragment();
                default: return new AZNameListFragment();
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}