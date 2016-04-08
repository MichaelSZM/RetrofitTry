package com.szm.administrator.retrofittry.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/4/8.
 */
public class User {

    @SerializedName("coord")
    private Coord coord;

    @SerializedName("sys")
    private Sys sys;

    @SerializedName("weather")
    private ArrayList<Weather> weather;

    @SerializedName("main")
    private MainInfos mainInfos;

    @SerializedName("wind")
    private Wind wind;

    @SerializedName("name")
    private String strCityName;

    public Coord getCoord()
    {
        return coord;
    }

    public Sys getSys()
    {
        return sys;
    }

    public ArrayList<Weather> getWeather()
    {
        return weather;
    }

    public MainInfos getMainInfos()
    {
        return mainInfos;
    }

    public Wind getWind()
    {
        return wind;
    }

    public String getStrCityName()
    {
        return strCityName;
    }

    @Override
    public String toString() {
        return "User{" +
                "coord=" + coord +
                ", sys=" + sys +
                ", weather=" + weather +
                ", mainInfos=" + mainInfos +
                ", wind=" + wind +
                ", strCityName='" + strCityName + '\'' +
                '}';
    }
}
