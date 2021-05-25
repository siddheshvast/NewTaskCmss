package com.example.newtaskcmss;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient
{

    public static final String BASE_URL = "https://api.tomtom.com/search/" ;

    public static Retrofit retrofit = null;


    public static Retrofit getAPI_Client()
    {

        if (retrofit == null)
        {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).
                    addConverterFactory(GsonConverterFactory.create()).build();
        }

        return retrofit;

    }



}