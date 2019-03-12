package com.example.cata6.m08apiprojectfinal.api.riotgames;

import com.example.cata6.m08apiprojectfinal.Model.Division;
import com.example.cata6.m08apiprojectfinal.Model.Games.GameResponse;
import com.example.cata6.m08apiprojectfinal.Model.Games.Match;
import com.example.cata6.m08apiprojectfinal.Model.Games.MatchListResponse;
import com.example.cata6.m08apiprojectfinal.Model.Summoner;
import com.example.cata6.m08apiprojectfinal.StatVars.StatVars;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SummonerdbAPI {
    @GET("/lol/summoner/v4/summoners/by-name/{name}")
    Call<Summoner> getSummoner(@Path("name") String name);

    @GET("/lol/league/v4/positions/by-summoner/{sumId}")
    Call<ArrayList<Division>> getSummonerDiv(@Path("sumId") String sumId);

    @GET("/lol/match/v4/matchlists/by-account/{sumId}?endIndex="+ StatVars.CANTIDAD_PARTIDAS)
    Call<MatchListResponse> getSummonerMatches(@Path("sumId") String sumId);

    @GET("/lol/match/v4/matches/{gameId}")
    Call<GameResponse> getSummonerGame(@Path("gameId") Long gameId);



}
