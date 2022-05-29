package com.bangkit.capstonenom.ui.activity.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkit.capstonenom.api.ApiConfig
import com.bangkit.capstonenom.response.FoodInformationResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FoodDetailViewModel: ViewModel() {
    val food = MutableLiveData<FoodInformationResponse>()

    fun setFoodDetail(id: Int){
        ApiConfig.apiInstance
            .getDetailFood(id)
            .enqueue(object : Callback<FoodInformationResponse>{
                override fun onResponse(
                    call: Call<FoodInformationResponse>,
                    response: Response<FoodInformationResponse>,
                ) {
                    if (response.isSuccessful){
                        food.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<FoodInformationResponse>, t: Throwable) {
                    Log.d("Error", t.message.toString())
                }
            })
    }

    fun getDetailFood(): LiveData<FoodInformationResponse>{
        return food
    }
}