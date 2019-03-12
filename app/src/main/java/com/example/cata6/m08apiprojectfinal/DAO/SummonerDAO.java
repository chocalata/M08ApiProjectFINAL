package com.example.cata6.m08apiprojectfinal.DAO;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.cata6.m08apiprojectfinal.Model.Summoner;

import java.util.List;

@Dao
public interface SummonerDAO {
    @Insert
    void insertSummoner(Summoner summoner);

    @Query("SELECT * FROM summoner")
    LiveData<List<Summoner>> getAllSummoners();

    @Query("SELECT * FROM summoner ORDER BY name")
    LiveData<List<Summoner>> getAllSummonersAZ();

    @Query("SELECT * FROM summoner ORDER BY summonerLevel")
    LiveData<List<Summoner>> getAllSummonersLevel();

    @Query("SELECT * FROM summoner ORDER BY division DESC")
    LiveData<List<Summoner>> getAllSummonersRank();

    @Delete
    void deleteSummoner(Summoner summoner);


}
