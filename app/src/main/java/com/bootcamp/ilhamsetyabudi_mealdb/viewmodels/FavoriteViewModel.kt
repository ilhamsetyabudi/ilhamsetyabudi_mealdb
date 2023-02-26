package com.bootcamp.ilhamsetyabudi_mealdb.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.bootcamp.ilhamsetyabudi_mealdb.data.LocalDataResource
import com.bootcamp.ilhamsetyabudi_mealdb.data.Repository
import com.bootcamp.ilhamsetyabudi_mealdb.data.database.MealDatabase
import com.bootcamp.ilhamsetyabudi_mealdb.data.database.MealEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel (application: Application) : AndroidViewModel(application) {
    // LOCAL
    private val mealDao = MealDatabase.getDatabase(application).mealDao()
    private val local = LocalDataResource(mealDao)

    private val repository = Repository(local = local)

    val favoriteMealList: LiveData<List<MealEntity>> = repository.local!!.listMeal().asLiveData()
}