package com.example.newsapp;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {


    @GET("top-headlines")
    Call<Headlines> getHeadlines(
            @Query("country") String country,
            @Query("apiKey") String apiKey
    );

    @GET("everything")//everything
    Call<Headlines> getSpecificData(
            @Query("q") String query,
            @Query("apiKey") String apiKey,
            @Query("pageSize") int num,
            @Query("sortBy") String sortBy,
            @Query("page") int page,
            @Query("from") String fromDate,
            @Query("domains") String domains,
            @Query("language") String language
    );

    @GET("everything")//everything
    Call<Headlines> bitcoinData(
            @Query("q") String query,
            @Query("apiKey") String apiKey,
            @Query("from") String fromDate,
            @Query("sortBy") String sortBy,
            @Query("language") String language
    );



}
