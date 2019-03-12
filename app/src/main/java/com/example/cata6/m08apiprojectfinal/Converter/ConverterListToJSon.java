package com.example.cata6.m08apiprojectfinal.Converter;

import android.arch.persistence.room.TypeConverter;

import com.example.cata6.m08apiprojectfinal.Model.Games.GameResponse;
import com.example.cata6.m08apiprojectfinal.Model.Games.Match;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ConverterListToJSon {
    static Gson gson = new Gson();

    @TypeConverter
    public static List<GameResponse> stringToSomeObjectList(String data) {
        if (data == null) {
            return new ArrayList<>();
        }

        Type listType = new TypeToken<List<GameResponse>>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String someObjectListToString(List<GameResponse> someObjects) {
        return gson.toJson(someObjects);
    }
}
