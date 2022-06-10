package com.bangkit.capstonenom.response

import com.google.gson.annotations.SerializedName

data class FoodResponse(
    @field:SerializedName("offset")
    val offset: String,

    @field:SerializedName("number")
    val number: String,

    @field:SerializedName("totalResults")
    val totalResults: String,

    @field:SerializedName("results")
    val caloriesList: List<FoodInformationResponse>,
)
