package com.example.cata6.m08apiprojectfinal.api.ddragon;

import com.example.cata6.m08apiprojectfinal.Model.Games.APIDdragonResponse.ChampionsResponse;
import com.example.cata6.m08apiprojectfinal.Model.Games.APIDdragonResponse.SummonerSpellsResponse;
import com.example.cata6.m08apiprojectfinal.StatVars.StatVars;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DdragonAPI {
    @GET("/cdn/" + StatVars.VERSION + "/data/es_ES/champion.json")
    Call<ChampionsResponse> getChampionsData();

    @GET("/cdn/" + StatVars.VERSION + "/data/en_US/summoner.json")
    Call<SummonerSpellsResponse> getSummonerSpellsData();


}
