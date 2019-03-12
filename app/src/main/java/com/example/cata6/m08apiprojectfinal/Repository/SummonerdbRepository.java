package com.example.cata6.m08apiprojectfinal.Repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.cata6.m08apiprojectfinal.DAO.SummonerDAO;
import com.example.cata6.m08apiprojectfinal.DataBase.SummonerRoomDatabase;
import com.example.cata6.m08apiprojectfinal.Model.Division;
import com.example.cata6.m08apiprojectfinal.Model.Games.GameResponse;
import com.example.cata6.m08apiprojectfinal.Model.Games.Match;
import com.example.cata6.m08apiprojectfinal.Model.Games.MatchListResponse;
import com.example.cata6.m08apiprojectfinal.Model.Summoner;
import com.example.cata6.m08apiprojectfinal.api.riotgames.SummonerdbAPI;
import com.example.cata6.m08apiprojectfinal.api.riotgames.SummonerdbModule;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SummonerdbRepository {
    SummonerdbAPI summonerdbAPI;
    SummonerDAO summonerDao;
    private final Executor executor = Executors.newFixedThreadPool(2);


    public SummonerdbRepository(Application application){
        summonerdbAPI = SummonerdbModule.getAPI();
        summonerDao = SummonerRoomDatabase.getDatabase(application).SummonerDAO();
    }

    public LiveData<GameResponse> getSummonerGame(Long gameId){
        final MutableLiveData<GameResponse> game = new MutableLiveData<>();

        summonerdbAPI.getSummonerGame(gameId).enqueue(new Callback<GameResponse>() {
            @Override
            public void onResponse(Call<GameResponse> call, Response<GameResponse> response) {
                game.postValue(response.body());
            }

            @Override
            public void onFailure(Call<GameResponse> call, Throwable t) {
            }
        });
        return game;
    }


    public LiveData<List<Match>> getSummonerMatches(String sumId){
        final MutableLiveData<List<Match>> matches = new MutableLiveData<>();

        summonerdbAPI.getSummonerMatches(sumId).enqueue(new Callback<MatchListResponse>(){

            @Override
            public void onResponse(Call<MatchListResponse> call, Response<MatchListResponse> response) {
                matches.setValue(response.body().matches);
            }

            @Override
            public void onFailure(Call<MatchListResponse> call, Throwable t) {

            }
        });
        return matches;
    }

    public LiveData<ArrayList<Division>> getSummonerDiv(String sumId){
        final MutableLiveData<ArrayList<Division>> division = new MutableLiveData<>();

        summonerdbAPI.getSummonerDiv(sumId).enqueue(new Callback<ArrayList<Division>>(){

            @Override
            public void onResponse(Call<ArrayList<Division>> call, Response<ArrayList<Division>> response) {
                division.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Division>> call, Throwable t) {

            }
        });
        return division;
    }

    public LiveData<Summoner> getSummoner(String name){
        final MutableLiveData<Summoner> summoner = new MutableLiveData<>();


        summonerdbAPI.getSummoner(name).enqueue(new Callback<Summoner>() {
            @Override
            public void onResponse(Call<Summoner> call, Response<Summoner> response) {
                summoner.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Summoner> call, Throwable t) {
            }
        });
        return summoner;
    }

    public LiveData<List<Summoner>> getSummonersDB(){
        return summonerDao.getAllSummoners();
    }

    public LiveData<List<Summoner>> getSummonersAZ(){
        return summonerDao.getAllSummonersAZ();
    }

    public LiveData<List<Summoner>> getSummonersLevel(){
        return summonerDao.getAllSummonersLevel();
    }

    public LiveData<List<Summoner>> getSummonersRank(){
        return summonerDao.getAllSummonersRank();
    }


    public void insertSummoner(final Summoner summoner) {
        if(summoner != null) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    summonerDao.insertSummoner(summoner);
                }
            });
        }
    }

    public void deleteSummoner(final Summoner summoner){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                summonerDao.deleteSummoner(summoner);
            }
        });
    }
}