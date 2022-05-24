package com.bangkit.capstonenom.response

import com.google.gson.annotations.SerializedName

data class FoodInformationResponse (
    @field:SerializedName("shoppingListUnits")
    val shoppingListUnits: List<String>,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("image")
    val image: String
)