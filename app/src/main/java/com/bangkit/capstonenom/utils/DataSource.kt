package com.bangkit.capstonenom.utils

import com.bangkit.capstonenom.api.ApiService
import com.bangkit.capstonenom.response.FoodInformationResponse
import com.bangkit.capstonenom.response.FoodResponse

class DataSource(private val apiService: ApiService) {
    companion object {
        @Volatile
        private var instance: DataSource? = null

        fun getInstance(apiService: ApiService): DataSource =
            instance ?: synchronized(this) {
                instance ?: DataSource(apiService).apply { instance = this }
            }
    }

    suspend fun getFoodResponse(name: String): FoodResponse {
        return apiService.getFoodListByName(name)
    }

    suspend fun getFoodById(id: Int): FoodInformationResponse {
        return apiService.getDetailFood(id)
    }
}