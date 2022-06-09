package com.bangkit.capstonenom.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bangkit.capstonenom.response.CaloricBreakdown
import com.bangkit.capstonenom.response.NutritionItem
import com.bangkit.capstonenom.response.WeightPerServing

@Entity(tableName = "foodentity")

data class FoodInformation (
    @PrimaryKey
    val id: Int,
    val name: String,
    val imageUrl: String,
    val weightPerServing: WeightPerServing,
    val caloricBreakdown: CaloricBreakdown,
    val nutrients: List<NutritionItem>
)