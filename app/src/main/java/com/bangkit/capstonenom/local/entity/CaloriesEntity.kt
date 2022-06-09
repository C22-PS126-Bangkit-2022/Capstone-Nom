package com.bangkit.capstonenom.local.entity

import androidx.room.TypeConverter
import com.bangkit.capstonenom.response.CaloricBreakdown

class CaloriesEntity {
    @TypeConverter
    fun fromString(stringCalBdown: String): CaloricBreakdown {
        val calBdownData = stringCalBdown.split("-")
        return CaloricBreakdown(
            calBdownData[0].toDouble(),
            calBdownData[1].toDouble(),
            calBdownData[2].toDouble()
        )
    }

    @TypeConverter
    fun toString(calBdown: CaloricBreakdown): String {
        return "${calBdown.percentCarbs}-${calBdown.percentProtein}-${calBdown.percentFat}"
    }
}