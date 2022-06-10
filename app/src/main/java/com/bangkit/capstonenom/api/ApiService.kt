package com.bangkit.capstonenom.api

import com.bangkit.capstonenom.response.FoodInformationResponse
import com.bangkit.capstonenom.response.FoodResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("ingredients/search?apiKey=d4e160b3da9a4359b964f89a89db54e6")
    suspend fun getSearchFood(
        @Query("query") query: String
    ): FoodResponse

    @GET("ingredients/{id}/information?amount=1&apiKey=d4e160b3da9a4359b964f89a89db54e6")
    suspend fun getDetailFood(
        @Path("id") id: Int
    ): FoodInformationResponse
}