package com.example.cata6.m08apiprojectfinal;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.cata6.m08apiprojectfinal.Model.Division;
import com.example.cata6.m08apiprojectfinal.Model.Games.APIDdragonResponse.ChampionsResponse;
import com.example.cata6.m08apiprojectfinal.Model.Games.APIDdragonResponse.SummonerSpellsResponse;
import com.example.cata6.m08apiprojectfinal.Model.Games.GameResponse;
import com.example.cata6.m08apiprojectfinal.Model.Games.Match;
import com.example.cata6.m08apiprojectfinal.Model.Summoner;
import com.example.cata6.m08apiprojectfinal.Repository.DdragonRepository;
import com.example.cata6.m08apiprojectfinal.Repository.SummonerdbRepository;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private SummonerdbRepository summonerdbRepository;
    private DdragonRepository ddragonRepository;


    public MainViewModel(@NonNull Application application) {
        super(application);
        summonerdbRepository = new SummonerdbRepository(application);
        ddragonRepository = new DdragonRepository();
    }

    public LiveData<ChampionsResponse> getChampionsResponse(){
        return ddragonRepository.getChampionsResponse();
    }

    public LiveData<SummonerSpellsResponse> getSummonerSpellsData(){
        return ddragonRepository.getSummonerSpellsData();
    }

    public LiveData<ArrayList<Division>> getSummonerDiv(String sumId){
        return summonerdbRepository.getSummonerDiv(sumId);
    }

    public LiveData<List<Match>> getSummonerMatches(String sumId){
        return summonerdbRepository.getSummonerMatches(sumId);
    }

    public LiveData<GameResponse> getSummonerGame(Long gameId){
        return summonerdbRepository.getSummonerGame(gameId);
    }

    public LiveData<Summoner> getSummoner(String summoner){
        return summonerdbRepository.getSummoner(summoner);
    }

    public LiveData<List<Summoner>> getSummonersDB(){
        return summonerdbRepository.getSummonersDB();
    }

    public LiveData<List<Summoner>> getSummonersLevel(){
        return summonerdbRepository.getSummonersLevel();
    }

    public LiveData<List<Summoner>> getSummonersAZ(){
        return summonerdbRepository.getSummonersAZ();
    }

    public LiveData<List<Summoner>> getSummonersRank(){
        return summonerdbRepository.getSummonersRank();
    }

    public void insertSummoner(Summoner summoner){
        summonerdbRepository.insertSummoner(summoner);
    }

    public void deleteSummoner(Summoner summoner){
        summonerdbRepository.deleteSummoner(summoner);
    }
}