package com.szm.administrator.retrofittry.services;

import com.szm.administrator.retrofittry.apis.UserApi;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/4/8.
 */
public class NormalClient {

    private static final String BASE_URL = "http://api.openweathermap.org/";
    private UserApi userApi;

    public NormalClient(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient())
                .build();
         userApi=retrofit.create(UserApi.class);
    }

    public UserApi getUserApi(){
        return userApi;
    }

}
