package com.bangkit.capstonenom.utils

import com.bangkit.capstonenom.model.Food
import com.bangkit.capstonenom.model.FoodInformation
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

    suspend fun getFoodById(id: Int): FoodInformation {
        try {
            val foodInformationResponse = dataSource.getFoodById(id)
            return FoodInformation(
                foodInformationResponse.id,
                foodInformationResponse.name,
                "https://spoonacular.com/cdn/ingredients_500x500/" + foodInformationResponse.image,
                foodInformationResponse.nutrition
            )
        } catch (e: Exception) {
            if (e.message.toString().replace("\\s".toRegex(), "") == "HTTP402") {
                return FoodInformation(
                    -1,
                    "API Limit Exceeded",
                    "https://spoonacular.com/cdn/ingredients_500x500/" + "error.jpg",
                    emptyList()
                )
            } else {
                return FoodInformation(
                    -1,
                    "Something went wrong",
                    "https://spoonacular.com/cdn/ingredients_500x500/" + "error.jpg",
                    emptyList()
                )
            }
        }
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