package com.bootcamp.ilhamsetyabudi_mealdb.ui

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.bootcamp.ilhamsetyabudi_mealdb.R
import com.bootcamp.ilhamsetyabudi_mealdb.data.database.MealEntity
import com.bootcamp.ilhamsetyabudi_mealdb.data.network.handler.NetworkResult
import com.bootcamp.ilhamsetyabudi_mealdb.data.network.model.MealsItem
import com.bootcamp.ilhamsetyabudi_mealdb.data.network.model.MealsItems
import com.bootcamp.ilhamsetyabudi_mealdb.databinding.ActivityDetailBinding
import com.bootcamp.ilhamsetyabudi_mealdb.viewmodels.DetailViewModel

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var mealDetail: MealsItem
    private val detailViewModel by viewModels<DetailViewModel>()

    companion object {
        const val EXTRA_MEAL = "meal"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.apply {
            title = "Meal Detail"
            setBackgroundDrawable(ColorDrawable(Color.parseColor("#1a1c1e")))
            setDisplayHomeAsUpEnabled(true)
        }

        val meal = intent.getParcelableExtra<MealsItems>(EXTRA_MEAL)

        detailViewModel.fetchMealDetail(meal?.idMeal as String)

        detailViewModel.mealDetail.observe(this) { res ->
            when (res) {
                is NetworkResult.Loading -> {
                    handleUi(wrapper = false, progress = true, errorTv = false)
                }
                is NetworkResult.Error -> {
                    handleUi(wrapper = false, progress = false, errorTv = true)
                }
                is NetworkResult.Success -> {
                    val data = res.data?.meals?.get(0)
                    mealDetail = res.data?.meals?.get(0)!!
                    binding.apply {
                        mealDetail = data
                    }
                    handleUi(wrapper = true, progress = false, errorTv = false)
                }
            }
        }
        isFavoriteMeal(meal)
    }

    private fun handleUi(
        wrapper: Boolean,
        progress: Boolean,
        errorTv: Boolean
    ) {
        binding.apply {
            mealDetailWrapper.isVisible = wrapper
            progressBar.isVisible = progress
            errorText.isVisible = errorTv
        }
    }

    private fun isFavoriteMeal(mealSelected: MealsItems) {
        detailViewModel.favoriteMealList.observe(this) { result ->
            val meal = result.find { favorite ->
                favorite.meal.idMeal == mealSelected.idMeal
            }
            if (meal != null) {
                binding.favoriteBtn.apply {
                    setImageResource(R.drawable.favorite_added)
                    setOnClickListener {
                        deleteFavoriteMeal(meal.id)
                    }
                }
            } else {
                binding.favoriteBtn.apply {
                    setImageResource(R.drawable.favorite_add)
                    setOnClickListener {
                        insertFavoriteMeal()
                    }
                }
            }
        }
    }

    private fun insertFavoriteMeal() {
        val mealEntity = MealEntity(meal = mealDetail)
        detailViewModel.insertFavoriteMeal(mealEntity)
        Toast.makeText(this, "Successfully add to favorite", Toast.LENGTH_SHORT).show()
    }

    private fun deleteFavoriteMeal(mealEntityId: Int) {
        val mealEntity = MealEntity(mealEntityId, mealDetail)
        detailViewModel.deleteFavoriteMeal(mealEntity)
        Toast.makeText(this, "Successfully remove from favorite", Toast.LENGTH_SHORT).show()
    }
}
