package com.bangkit.capstonenom.ui.fragment.history

import androidx.lifecycle.ViewModel
import com.bangkit.capstonenom.model.FoodInformation
import com.bangkit.capstonenom.utils.Repository

class HistoryViewModel(
    private val mRepository: Repository
) : ViewModel() {


    fun getAllFoodHistory() = mRepository.getAllFoodHistory()

    fun deleteFoodHistory(food: FoodInformation) = mRepository.delete(food)
}