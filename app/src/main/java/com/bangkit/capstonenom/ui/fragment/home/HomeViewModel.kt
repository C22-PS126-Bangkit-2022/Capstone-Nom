package com.bangkit.capstonenom.ui.fragment.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.capstonenom.model.Food
import com.bangkit.capstonenom.utils.Repository
import kotlinx.coroutines.launch

class HomeViewModel(private val mRepository: Repository) : ViewModel() {
    val foodList = MutableLiveData<List<Food>>()

    fun getFoodName(name: String) {
        viewModelScope.launch {
            val foodListRes = mRepository.getFoodList(name)
            foodList.value = foodListRes
        }
    }
}