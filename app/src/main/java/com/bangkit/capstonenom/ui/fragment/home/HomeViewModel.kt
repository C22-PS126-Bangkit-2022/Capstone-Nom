package com.bangkit.capstonenom.ui.fragment.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkit.capstonenom.api.ApiConfig
import com.bangkit.capstonenom.model.Food
import com.bangkit.capstonenom.model.FoodResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    val listFood = MutableLiveData<ArrayList<Food>>()

    private val _text = MutableLiveData<String>().apply {
        value = "Welcome"
    }

    fun setSearchFood(query: String){
        ApiConfig.apiInstance
            .getSearchFood(query)
            .enqueue(object : Callback<FoodResponse> {
                override fun onResponse(
                    call: Call<FoodResponse>,
                    response: Response<FoodResponse>
                ){
                    if (response.isSuccessful){
                        listFood.postValue(response.body()?.items)
                    }
                }

                override fun onFailure(call: Call<FoodResponse>, t: Throwable) {
                    Log.d("Error", t.message.toString())
                }
            })
    }
    fun getSearchFood(): LiveData<ArrayList<Food>> {
        return listFood
    }
    val text: LiveData<String> = _text
}