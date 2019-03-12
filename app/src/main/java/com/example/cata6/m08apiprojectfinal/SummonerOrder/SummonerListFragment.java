package com.example.cata6.m08apiprojectfinal.SummonerOrder;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cata6.m08apiprojectfinal.MainViewModel;
import com.example.cata6.m08apiprojectfinal.Model.Summoner;
import com.example.cata6.m08apiprojectfinal.R;
import com.example.cata6.m08apiprojectfinal.RecyclerAdapters.SummonerRecyclerAdapter;

import java.util.List;


public abstract class SummonerListFragment extends Fragment implements SummonerRecyclerAdapter.ClickListener{
    MainViewModel mainViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_summoner_list, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.summoner_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        final SummonerRecyclerAdapter summonerRecyclerAdapter = new SummonerRecyclerAdapter(this);
        recyclerView.setAdapter(summonerRecyclerAdapter);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        getSummoners().observe(this, new Observer<List<Summoner>>() {
            @Override
            public void onChanged(@Nullable List<Summoner> summoners) {
                summonerRecyclerAdapter.setListaSummoners(summoners);
                summonerRecyclerAdapter.notifyDataSetChanged();
            }
        });

        return view;
    }

    @Override
    public void onDeleteSummoner(Summoner summoner){
        mainViewModel.deleteSummoner(summoner);
    }

    abstract LiveData<List<Summoner>> getSummoners();
}