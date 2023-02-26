package com.bootcamp.ilhamsetyabudi_mealdb.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.bootcamp.ilhamsetyabudi_mealdb.data.LocalDataResource
import com.bootcamp.ilhamsetyabudi_mealdb.data.Repository
import com.bootcamp.ilhamsetyabudi_mealdb.data.database.MealDatabase
import com.bootcamp.ilhamsetyabudi_mealdb.data.database.MealEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailFavoriteViewModel(application: Application) : AndroidViewModel(application) {

    // DAO
    private val mealDao = MealDatabase.getDatabase(application).mealDao()
    private val local = LocalDataResource(mealDao)

    // Repository
    private val repository = Repository(local = local)

    fun deleteFavoriteMeal(mealEntity: MealEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.local!!.deleteMeal(mealEntity)
        }
    }
}