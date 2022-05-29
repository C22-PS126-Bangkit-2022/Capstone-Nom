package com.bangkit.capstonenom.response

import com.google.gson.annotations.SerializedName

data class FoodInformationResponse (

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("image")
    val image: String,

    @field:SerializedName("amount")
    val amount: Int,

    @field:SerializedName("nutrition")
    val nutrition: List<Nutrition>

)

data class Nutrition(

    @field:SerializedName("amount")
    val amount: Double,

    @field:SerializedName("unit")
    val unit: String,

    @field:SerializedName("name")
    val name: String,
)
