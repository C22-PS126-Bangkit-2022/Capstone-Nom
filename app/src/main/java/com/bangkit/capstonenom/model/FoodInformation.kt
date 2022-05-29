package com.bangkit.capstonenom.model

import com.bangkit.capstonenom.response.Nutrition


data class FoodInformation (
    val id: Int,
    val name: String,
    val imageUrl: String,
    val nutrients: List<Nutrition>
)