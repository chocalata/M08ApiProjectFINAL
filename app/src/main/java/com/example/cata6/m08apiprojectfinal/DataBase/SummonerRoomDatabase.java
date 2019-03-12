package com.example.cata6.m08apiprojectfinal.DataBase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.cata6.m08apiprojectfinal.DAO.SummonerDAO;
import com.example.cata6.m08apiprojectfinal.Model.Summoner;


@Database(entities = {Summoner.class}, version = 1)
public abstract class SummonerRoomDatabase extends RoomDatabase {

    public abstract SummonerDAO SummonerDAO();

    private static volatile SummonerRoomDatabase INSTANCE;

    public static SummonerRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (SummonerRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            SummonerRoomDatabase.class, "Summoner_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}