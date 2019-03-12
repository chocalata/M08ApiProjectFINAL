package com.example.cata6.m08apiprojectfinal.Repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.cata6.m08apiprojectfinal.Model.Games.APIDdragonResponse.ChampionsResponse;
import com.example.cata6.m08apiprojectfinal.Model.Games.APIDdragonResponse.SummonerSpellsResponse;
import com.example.cata6.m08apiprojectfinal.api.ddragon.DdragonAPI;
import com.example.cata6.m08apiprojectfinal.api.ddragon.DdragonModule;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DdragonRepository {
    
    DdragonAPI ddragonAPI;

    public DdragonRepository() {
        ddragonAPI = DdragonModule.getAPI();
    }

    public LiveData<SummonerSpellsResponse> getSummonerSpellsData(){
        final MutableLiveData<SummonerSpellsResponse> summonerSpellsResponse = new MutableLiveData<>();

        ddragonAPI.getSummonerSpellsData().enqueue(new Callback<SummonerSpellsResponse>() {
            @Override
            public void onResponse(Call<SummonerSpellsResponse> call, Response<SummonerSpellsResponse> response) {
                summonerSpellsResponse.setValue(response.body());
            }

            @Override
            public void onFailure(Call<SummonerSpellsResponse> call, Throwable t) {

            }
        });

        return summonerSpellsResponse;
    }
    public LiveData<ChampionsResponse> getChampionsResponse(){
        final MutableLiveData<ChampionsResponse> championsResponse = new MutableLiveData<>();


        ddragonAPI.getChampionsData().enqueue(new Callback<ChampionsResponse>() {
            @Override
            public void onResponse(Call<ChampionsResponse> call, Response<ChampionsResponse> response) {
                championsResponse.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ChampionsResponse> call, Throwable t) {
            }
        });
        return championsResponse;
    }
}
