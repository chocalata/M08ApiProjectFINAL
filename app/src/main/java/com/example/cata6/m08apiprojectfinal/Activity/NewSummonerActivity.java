package com.example.cata6.m08apiprojectfinal.Activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cata6.m08apiprojectfinal.Converter.ConverterListToJSon;
import com.example.cata6.m08apiprojectfinal.MainViewModel;
import com.example.cata6.m08apiprojectfinal.Model.Division;
import com.example.cata6.m08apiprojectfinal.Model.Games.GameResponse;
import com.example.cata6.m08apiprojectfinal.Model.Games.Match;
import com.example.cata6.m08apiprojectfinal.Model.Games.Participant;
import com.example.cata6.m08apiprojectfinal.Model.Games.ParticipantIdentity;
import com.example.cata6.m08apiprojectfinal.Model.Summoner;
import com.example.cata6.m08apiprojectfinal.R;
import com.example.cata6.m08apiprojectfinal.StatVars.StatVars;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.cata6.m08apiprojectfinal.Activity.Main2Activity.soloq;

public class NewSummonerActivity extends AppCompatActivity {

    ArrayList<String> divisiones = new ArrayList<>(Arrays.asList("iron", "bronze", "silver", "gold", "platinum", "diamond", "master", "grandmaster", "challenger"));

    Summoner summRespuesta;
    MainViewModel mainViewModel;

    List<GameResponse> gameResponseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_summoner);

        gameResponseList = new ArrayList<>();

        mainViewModel = ViewModelProviders.of(NewSummonerActivity.this).get(MainViewModel.class);


        findViewById(R.id.button_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardar(v);
            }
        });

    }

    private void guardar(View view){
        final String titulo = ((EditText) findViewById(R.id.summonerNameCall)).getText().toString();

        getSumm(titulo);


    }

    public void getSumm(String titulo){
        mainViewModel.getSummoner(titulo).observe(this, new Observer<Summoner>() {
            @Override
            public void onChanged(@Nullable Summoner summoner) {
                if(summoner != null) {
                    //Add summoner
                    summRespuesta = summoner;
                    getSummDiv(summoner.id);

                } else {
                    Toast.makeText(NewSummonerActivity.this, "NO EXISTE", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void getSummDiv(String id){
        mainViewModel.getSummonerDiv(id).observe(this, new Observer<ArrayList<Division>>() {
            @Override
            public void onChanged(@Nullable ArrayList<Division> division) {
                if(division != null) {
                    for (Division div:division) {
                        if(div.queueType.equals(soloq)) {
                            int numDiv = 0;
                            for (String _div:divisiones) {
                                numDiv++;
                                if(_div.equals(div.tier.toLowerCase())){
                                    break;
                                }
                            }

                            summRespuesta.setDivision(numDiv + div.tier.toLowerCase());;
                        }
                    }
                }

                getSummMatches(summRespuesta.getAccountId());

            }
        });

    }

    public void getSummMatches(String id){
        mainViewModel.getSummonerMatches(id).observe(this, new Observer<List<Match>>() {
            @Override
            public void onChanged(@Nullable List<Match> matches) {
                if(matches != null) {
                    for (Match match: matches) {
                        getSummGame(match.gameId);
                    }
                }
            }
        });
    }

    public void getSummGame(Long gameId){
        mainViewModel.getSummonerGame(gameId).observe(this, new Observer<GameResponse>() {
            @Override
            public void onChanged(@Nullable GameResponse gameResponse) {
                if(gameResponse != null){
                    gameResponseList.add(gameResponse);

                    if(gameResponseList.size() == StatVars.CANTIDAD_PARTIDAS){
                        summRespuesta.lastGames = ConverterListToJSon.someObjectListToString(gameResponseList);
                        mainViewModel.insertSummoner(summRespuesta);

                        finishActivity();
                    }

                }
            }
        });
    }

    private void finishActivity(){
        this.finish();
    }
}
