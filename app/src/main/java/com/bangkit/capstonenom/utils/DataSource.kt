package com.bangkit.capstonenom.utils

import com.bangkit.capstonenom.api.ApiService
import com.bangkit.capstonenom.response.FoodInformationResponse
import com.bangkit.capstonenom.response.FoodResponse

class DataSource(private val apiService: ApiService) {
    suspend fun getSearchFood(name: String): FoodResponse {
        return apiService.getSearchFood(name)
    }

    suspend fun getDetailFood(id: Int): FoodInformationResponse {
        return apiService.getDetailFood(id)
    }

    companion object {
        @Volatile
        private var instance: DataSource? = null

        fun getInstance(apiService: ApiService): DataSource =
            instance ?: synchronized(this) {
                instance ?: DataSource(apiService).apply { instance = this }
            }
    }
}