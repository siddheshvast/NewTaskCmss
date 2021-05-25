package com.example.newtaskcmss;

import com.example.newtaskcmss.models.Nearbyplaces;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET(value = "2/categorySearch/{query}.json?")
    Call<Nearbyplaces> getNearByLocations(@Query("query") String search,@Query("lon") double longitude, @Query("lat") double latitude, @Query("key") String key);

}
