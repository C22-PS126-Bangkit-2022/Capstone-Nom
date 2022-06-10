package com.bangkit.capstonenom.ui.fragment.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.capstonenom.model.FoodInformation
import com.bangkit.capstonenom.utils.Repository
import kotlinx.coroutines.launch

class FoodDetailViewModel(private val mRepository: Repository): ViewModel() {
    private val food = MutableLiveData<FoodInformation>()

    fun getFoodDetail(id: Int): LiveData<FoodInformation> {
        viewModelScope.launch {
            food.value = mRepository.getDetailFood(id)
        }
        return food
    }
    fun addToHistory(foodDetails: FoodInformation) = mRepository.insert(foodDetails)


    fun getFoodHistoryById(id: Int) = mRepository.getFoodHistoryById(id)
}