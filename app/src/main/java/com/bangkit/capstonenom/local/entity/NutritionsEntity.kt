package com.bangkit.capstonenom.local.entity


import androidx.room.TypeConverter
import com.bangkit.capstonenom.response.NutritionItem

class NutritionsEntity {
    @TypeConverter
    fun fromString(stringListNutrient: String): ArrayList<NutritionItem> {
        stringListNutrient.split(",").map {
            it.split("-")
        }
        return ArrayList()
    }

    @TypeConverter
    fun toString(nutrientList: List<NutritionItem>): String {
        return nutrientList.joinToString(separator = ",") {
            "${it.amount}-${it.unit}-${it.name}-${it.title}"
        }
    }
}