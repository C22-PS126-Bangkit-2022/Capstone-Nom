package com.bangkit.capstonenom.ui.fragment.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.capstonenom.api.ApiConfig
import com.bangkit.capstonenom.model.Food
import com.bangkit.capstonenom.response.FoodResponse
import com.bangkit.capstonenom.utils.Repository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(private val mRepository: Repository) : ViewModel() {
    val foodList = MutableLiveData<List<Food>>()

    fun getFoodName(name: String) {
        viewModelScope.launch {
            val foodListRes = mRepository.getFoodList(name)
            foodList.value = foodListRes
        }
    }
}