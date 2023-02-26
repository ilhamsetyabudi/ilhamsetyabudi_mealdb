package com.bootcamp.ilhamsetyabudi_mealdb.data.network.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class MealDetail(

    @field:SerializedName("meals")
    val meals: List<MealsItem?>? = null
) : Parcelable

@Parcelize
data class MealsItem(

    @field:SerializedName("strCategory")
    val strCategory: String? = null,

    @field:SerializedName("strArea")
    val strArea: String? = null,

    @field:SerializedName("idMeal")
    val idMeal: String? = null,

    @field:SerializedName("strInstructions")
    val strInstructions: String? = null,

    @field:SerializedName("strMealThumb")
    val strMealThumb: String? = null,

    @field:SerializedName("strYoutube")
    val strYoutube: String? = null,

    @field:SerializedName("strMeal")
    val strMeal: String? = null,
) : Parcelable