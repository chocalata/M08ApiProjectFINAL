package com.example.cata6.m08apiprojectfinal.SummonerOrder;

import android.arch.lifecycle.LiveData;

import com.example.cata6.m08apiprojectfinal.Model.Summoner;

import java.util.List;

public class RankListFragment extends SummonerListFragment{
    @Override
    LiveData<List<Summoner>> getSummoners() {
        return mainViewModel.getSummonersRank();
    }
}
