package com.bangkit.capstonenom.utils

import androidx.lifecycle.LiveData
import com.bangkit.capstonenom.local.room.HistoryFoodDao
import com.bangkit.capstonenom.model.Food
import com.bangkit.capstonenom.model.FoodInformation
import com.bangkit.capstonenom.response.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class Repository(private val dataSource: DataSource,
                 private val mHistoryFoodDao: HistoryFoodDao
) {
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    companion object {
        @Volatile
        private var instance: Repository? = null
        fun getInstance(dataSource: DataSource, foodDao: HistoryFoodDao): Repository =
            instance ?: synchronized(this) {
                instance ?: Repository(dataSource, foodDao).apply { instance = this }
            }
    }


    suspend fun getFoodList(name: String): ArrayList<Food> {
        val foodList: ArrayList<Food> = arrayListOf()
        try {
            val ingredientSearchResponse: FoodResponse = dataSource.getSearchFood(name)
            val ingredientListResponse: List<FoodInformationResponse> =
                ingredientSearchResponse.caloriesList
            ingredientListResponse.forEach { ingredientResponse ->
                foodList.add(
                    Food(
                        ingredientResponse.id,
                        ingredientResponse.name,
                        "https://spoonacular.com/cdn/ingredients_500x500/" + ingredientResponse.image

                    )
                )
            }
        } catch (e: Exception) {
            if (e.message.toString().replace("\\s".toRegex(), "") == "HTTP402") {
                foodList.add(
                    Food(
                        -1,
                        "API limit exceeded, please try again in 24 hours.",
                        "https://spoonacular.com/cdn/ingredients_500x500/" + "error.jpg"
                    )
                )
            } else {
                foodList.add(
                    Food(
                        -1,
                        "Please try again in a few moments.",
                        "https://spoonacular.com/cdn/ingredients_500x500/" + "error.jpg"
                    )
                )
            }
        }
        return foodList
    }

    fun getAllFoodHistory(): LiveData<List<FoodInformation>> = mHistoryFoodDao.getAllFood()

    fun getFoodHistoryById(id: Int): LiveData<FoodInformation> = mHistoryFoodDao.getFoodById(id)

    fun insert(food: FoodInformation) {
        executorService.execute { mHistoryFoodDao.insert(food) }
    }

    fun delete(food: FoodInformation) {
        executorService.execute { mHistoryFoodDao.delete(food) }
    }

    suspend fun getDetailFood(id: Int): FoodInformation {
        try {
            val foodInformationResponse = dataSource.getDetailFood(id)
            return FoodInformation(
                foodInformationResponse.id,
                foodInformationResponse.name,
                "https://spoonacular.com/cdn/ingredients_500x500/" + foodInformationResponse.image,
                foodInformationResponse.nutrition.weightPerServing,
                foodInformationResponse.nutrition.caloricBreakdown,
                foodInformationResponse.nutrition.nutrients
            )
        } catch (e: Exception) {
            if (e.message.toString().replace("\\s".toRegex(), "") == "HTTP402") {
                return FoodInformation(
                    -1,
                    "API Limit Exceeded",
                    "https://spoonacular.com/cdn/ingredients_500x500/" + "error.jpg",
                    WeightPerServing(0, ""),
                    CaloricBreakdown(0.0, 0.0, 0.0),
                    emptyList()
                )
            } else {
                return FoodInformation(
                    -1,
                    "Something went wrong",
                    "https://spoonacular.com/cdn/ingredients_500x500/" + "error.jpg",
                    WeightPerServing(0, ""),
                    CaloricBreakdown(0.0, 0.0, 0.0),
                    emptyList()
                )
            }
        }
    }


}