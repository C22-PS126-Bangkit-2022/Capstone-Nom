package com.bangkit.capstonenom.utils

import com.bangkit.capstonenom.model.Food
import com.bangkit.capstonenom.response.FoodInformationResponse
import com.bangkit.capstonenom.response.FoodResponse

class Repository(private val dataSource: DataSource) {

    suspend fun getFoodList(name: String): ArrayList<Food> {
        val foodList: ArrayList<Food> = arrayListOf()
        try {
            val ingredientSearchResponse: FoodResponse = dataSource.getFoodResponse(name)
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

    companion object {
        @Volatile
        private var instance: Repository? = null
        fun getInstance(dataSource: DataSource): Repository =
            instance ?: synchronized(this) {
                instance ?: Repository(dataSource).apply { instance = this }
            }
    }
}