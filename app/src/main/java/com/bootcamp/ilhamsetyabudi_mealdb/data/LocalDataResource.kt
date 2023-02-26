package com.bootcamp.ilhamsetyabudi_mealdb.data

import com.bootcamp.ilhamsetyabudi_mealdb.data.database.MealDao
import com.bootcamp.ilhamsetyabudi_mealdb.data.database.MealEntity
import kotlinx.coroutines.flow.Flow

class LocalDataResource (private val dao: MealDao) {
    suspend fun insertMeal(mealEntity: MealEntity) = dao.insertMeal(mealEntity)
    fun listMeal(): Flow<List<MealEntity>> = dao.listMeal()
    suspend fun deleteMeal(mealEntity: MealEntity) = dao.deleteMeal(mealEntity)
}