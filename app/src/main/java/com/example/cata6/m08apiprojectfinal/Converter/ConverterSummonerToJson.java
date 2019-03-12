package com.example.cata6.m08apiprojectfinal.Converter;

import com.example.cata6.m08apiprojectfinal.Model.Summoner;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class ConverterSummonerToJson {
    static Gson gson = new Gson();

    public static Summoner stringToSomeObjectList(String data) {
        if (data == null) {
            return new Summoner();
        }

        Type listType = new TypeToken<Summoner>() {}.getType();

        return gson.fromJson(data, listType);
    }

    public static String someObjectListToString(Summoner someObjects) {
        return gson.toJson(someObjects);
    }
}
