package com.bangkit.capstonenom.response

import com.google.gson.annotations.SerializedName

data class FoodInformationResponse (

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("image")
    val image: String,

    @field:SerializedName("amount")
    val amount: Int,

    @field:SerializedName("unit")
    val unit: String,

    @field:SerializedName("nutrition")
    val nutrition: Nutrition,
)

data class NutritionItem(

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("amount")
    val amount: Double,

    @field:SerializedName("unit")
    val unit: String,

    @field:SerializedName("name")
    val name: String,
)

data class Nutrition(

    @field:SerializedName("weightPerServing")
    val weightPerServing: WeightPerServing,

    @field:SerializedName("caloricBreakdown")
    val caloricBreakdown: CaloricBreakdown,

    @field:SerializedName("nutrients")
    val nutrients: List<NutritionItem>
)

data class WeightPerServing(

    @field:SerializedName("amount")
    val amount: Int,

    @field:SerializedName("unit")
    val unit: String
)

data class CaloricBreakdown(

    @field:SerializedName("percentCarbs")
    val percentCarbs: Double,

    @field:SerializedName("percentFat")
    val percentFat: Double,

    @field:SerializedName("percentProtein")
    val percentProtein: Double
)
