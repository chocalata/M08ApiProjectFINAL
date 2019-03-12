package com.example.cata6.m08apiprojectfinal.Activity;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.cata6.m08apiprojectfinal.Converter.ConverterListToJSon;
import com.example.cata6.m08apiprojectfinal.Converter.ConverterSummonerToJson;
import com.example.cata6.m08apiprojectfinal.MainViewModel;
import com.example.cata6.m08apiprojectfinal.Model.Games.APIDdragonResponse.ChampionsResponse;
import com.example.cata6.m08apiprojectfinal.Model.Games.APIDdragonResponse.SummonerSpellsResponse;
import com.example.cata6.m08apiprojectfinal.Model.Games.GameResponse;
import com.example.cata6.m08apiprojectfinal.Model.Games.ParticipantIdentity;
import com.example.cata6.m08apiprojectfinal.Model.Summoner;
import com.example.cata6.m08apiprojectfinal.R;
import com.example.cata6.m08apiprojectfinal.RecyclerAdapters.SummonerMatchRecyclerAdapter;

import java.util.List;

public class SummonerGamesActivity extends AppCompatActivity {
    MainViewModel mainViewModel;
    RecyclerView recyclerView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout./*//////////CUERDATE DE PONER DE NUEVO EL activity_summoner_games*/activity_summoner_games/*/////////////*/);

        recyclerView = findViewById(R.id.summonerGames_result_list);
        mainViewModel = new MainViewModel(this.getApplication());

        //Coger Summoner que le pasamos al llamar a la activity.
        Summoner summoner = ConverterSummonerToJson.stringToSomeObjectList(getIntent().getStringExtra("summoner"));

        final List<GameResponse> gameResponseList = ConverterListToJSon.stringToSomeObjectList(summoner.lastGames);

        setSummonerInfoToRecyclerVeiw(gameResponseList);
    }

    private void setSummonerInfoToRecyclerVeiw(final List<GameResponse> gameResponseList){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getChampRespo(gameResponseList);
    }
    private void getChampRespo(final List<GameResponse> gameResponseList){
        mainViewModel.getChampionsResponse().observe(this, new Observer<ChampionsResponse>() {
            @Override
            public void onChanged(@Nullable ChampionsResponse championsResponse) {
                getSummSpellData(gameResponseList, championsResponse);
            }
        });
    }
    private void getSummSpellData(final List<GameResponse> gameResponseList, final ChampionsResponse championsResponse){
        mainViewModel.getSummonerSpellsData().observe(this, new Observer<SummonerSpellsResponse>() {
            @Override
            public void onChanged(@Nullable SummonerSpellsResponse summonerSpellsResponse) {
                SummonerMatchRecyclerAdapter summonerMatchRecyclerAdapter = new SummonerMatchRecyclerAdapter(gameResponseList.get(0).participants, gameResponseList.get(0).participantIdentities, championsResponse, summonerSpellsResponse);
                recyclerView.setAdapter(summonerMatchRecyclerAdapter);
            }
        });
    }
}
