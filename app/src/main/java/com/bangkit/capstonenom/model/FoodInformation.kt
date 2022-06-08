package com.bangkit.capstonenom.model

import android.os.Parcelable
import androidx.room.PrimaryKey
import com.bangkit.capstonenom.response.*
import kotlinx.parcelize.Parcelize


data class FoodInformation (

    val id: Int,
    val name: String,
    val imageUrl: String,
    val weightPerServing: WeightPerServing,
    val caloricBreakdown: CaloricBreakdown,
    val nutrients: List<NutritionItem>
)