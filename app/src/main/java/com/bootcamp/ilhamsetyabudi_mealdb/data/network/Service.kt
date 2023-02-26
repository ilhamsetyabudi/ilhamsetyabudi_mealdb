package com.bootcamp.ilhamsetyabudi_mealdb.data.network

import com.bootcamp.ilhamsetyabudi_mealdb.data.network.api.MealsApi
import com.bootcamp.ilhamsetyabudi_mealdb.utils.Constant.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Service {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val mealService: MealsApi by lazy {
        retrofit.create(MealsApi::class.java)
    }
}