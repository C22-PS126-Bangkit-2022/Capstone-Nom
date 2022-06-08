package com.bangkit.capstonenom.ui.fragment.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.capstonenom.api.ApiConfig
import com.bangkit.capstonenom.model.FoodInformation
import com.bangkit.capstonenom.response.FoodInformationResponse
import com.bangkit.capstonenom.utils.Repository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FoodDetailViewModel(private val mRepository: Repository): ViewModel() {
    private val food = MutableLiveData<FoodInformation>()

    fun getFoodDetail(id: Int): LiveData<FoodInformation> {
        viewModelScope.launch {
            food.value = mRepository.getDetailFood(id)
        }
        return food
    }
}