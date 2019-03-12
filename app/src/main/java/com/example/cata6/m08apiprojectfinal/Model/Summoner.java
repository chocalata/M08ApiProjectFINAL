package com.example.cata6.m08apiprojectfinal.Model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.example.cata6.m08apiprojectfinal.Converter.ConverterListToJSon;

@Entity
public class Summoner {
    @PrimaryKey
    @NonNull
    public String accountId;
    public String id;

    public int profileIconId;
    public long summonerLevel;

    public String division;

    @TypeConverters(ConverterListToJSon.class)
    public String lastGames;

    public String name;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getProfileIconId() {
        return profileIconId;
    }

    public void setProfileIconId(int profileIconId) {
        this.profileIconId = profileIconId;
    }

    public long getSummonerLevel() {
        return summonerLevel;
    }

    public void setSummonerLevel(long summonerLevel) {
        this.summonerLevel = summonerLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getDivision() {
        return division;
    }

    @Override
    public String toString() {
        return "Summoner{" +
                "accountId=" + accountId +
                ", id=" + id +
                ", profileIconId=" + profileIconId +
                ", summonerLevel=" + summonerLevel +
                ", name='" + name + '\'' +
                '}';
    }
}
