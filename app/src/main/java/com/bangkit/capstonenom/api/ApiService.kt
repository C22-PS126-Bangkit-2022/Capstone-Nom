package com.bangkit.capstonenom.api

import com.bangkit.capstonenom.response.FoodResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("search?apiKey=d4e160b3da9a4359b964f89a89db54e6")
    suspend fun getFoodListByName(
        @Query("query") query: String
    ): FoodResponse
}