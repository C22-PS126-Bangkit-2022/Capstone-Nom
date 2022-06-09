package com.bangkit.capstonenom.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bangkit.capstonenom.local.entity.*

import com.bangkit.capstonenom.model.FoodInformation

@Database(entities = [FoodInformation::class], version = 1, exportSchema = false)
@TypeConverters(
    NutritionsEntity::class,
    WeightPerServingEntity::class,
    CaloriesEntity::class
)
abstract class HistoryFoodDatabase: RoomDatabase() {
    abstract fun historyFoodDao(): HistoryFoodDao

    companion object {
        @Volatile
        private var INSTANCE: HistoryFoodDatabase? = null
        @JvmStatic
        fun getDatabase(context: Context): HistoryFoodDatabase {
            if (INSTANCE == null) {
                synchronized(HistoryFoodDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        HistoryFoodDatabase::class.java, "food_database")
                        .build()
                }
            }
            return INSTANCE as HistoryFoodDatabase
        }
    }

}