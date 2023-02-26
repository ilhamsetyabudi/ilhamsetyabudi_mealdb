package com.bootcamp.ilhamsetyabudi_mealdb.data.network.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class MealResult(

    @field:SerializedName("meals")
    val meals: List<MealsItems?>? = null
) : Parcelable

@Parcelize
data class MealsItems(

    @field:SerializedName("strMealThumb")
    val strMealThumb: String? = null,

    @field:SerializedName("idMeal")
    val idMeal: String? = null,

    @field:SerializedName("strMeal")
    val strMeal: String? = null
) : Parcelable

