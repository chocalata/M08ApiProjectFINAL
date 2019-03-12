package com.example.cata6.m08apiprojectfinal.Converter;


import com.example.cata6.m08apiprojectfinal.Model.Games.APIDdragonResponse.ChampionsResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class ConverterChampionToJSon {
    static Gson gson = new Gson();

    public static ChampionsResponse stringToSomeObjectList(String data) {
        if (data == null) {
            return new ChampionsResponse();
        }

        Type listType = new TypeToken<ChampionsResponse>() {}.getType();

        return gson.fromJson(data, listType);
    }

    public static String someObjectListToString(ChampionsResponse someObjects) {
        return gson.toJson(someObjects);
    }
}
