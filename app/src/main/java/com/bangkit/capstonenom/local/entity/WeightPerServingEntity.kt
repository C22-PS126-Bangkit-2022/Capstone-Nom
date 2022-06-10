package com.bangkit.capstonenom.local.entity

import androidx.room.TypeConverter
import com.bangkit.capstonenom.response.WeightPerServing

class WeightPerServingEntity {
    @TypeConverter
    fun fromString(stringWps: String): WeightPerServing {
        val wpsData = stringWps.split("-")
        return WeightPerServing(wpsData[0].toInt(), wpsData[1])
    }

    @TypeConverter
    fun toString(wps: WeightPerServing): String {
        return "${wps.amount}-${wps.unit}"
    }
}