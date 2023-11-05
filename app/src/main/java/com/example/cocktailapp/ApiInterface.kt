package com.example.cocktailapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("api/json/v1/1/search.php?s=margarita")
    fun getData(): Call<DrinkResponse>
}