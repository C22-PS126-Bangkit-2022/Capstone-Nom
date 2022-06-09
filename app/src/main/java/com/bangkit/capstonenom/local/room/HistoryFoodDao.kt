package com.bangkit.capstonenom.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.bangkit.capstonenom.model.FoodInformation

@Dao
interface HistoryFoodDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(food: FoodInformation)

    @Delete
    fun delete(food: FoodInformation)

    @Query("SELECT * from foodentity")
    fun getAllFood(): LiveData<List<FoodInformation>>

    @Query("SELECT * FROM foodentity WHERE id = :foodId")
    fun getFoodById(foodId: Int): LiveData<FoodInformation>
}