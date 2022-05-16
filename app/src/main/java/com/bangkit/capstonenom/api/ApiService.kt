package com.bangkit.capstonenom.api

import com.bangkit.capstonenom.model.FoodResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {
    @GET("http://platform.fatsecret.com/api/1.0/")
    @Headers("Authorization")
    fun getSearchFood(
        @Query("method") method: String
    ): Call<FoodResponse>
}