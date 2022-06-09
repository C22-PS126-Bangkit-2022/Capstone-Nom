package com.bangkit.capstonenom.ui.fragment.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkit.capstonenom.model.FoodInformation
import com.bangkit.capstonenom.utils.Repository

class HistoryViewModel(
    private val mRepository: Repository
) : ViewModel() {


    fun getAllFoodHistory() = mRepository.getAllFoodHistory()

//    fun getFoodHistoryById(id: Int) = mRepository.getFoodHistoryById(id)

    fun deleteFoodHistory(food: FoodInformation) = mRepository.delete(food)
}