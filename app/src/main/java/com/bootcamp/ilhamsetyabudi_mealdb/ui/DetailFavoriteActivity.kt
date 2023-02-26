package com.bootcamp.ilhamsetyabudi_mealdb.ui

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.bootcamp.ilhamsetyabudi_mealdb.R
import com.bootcamp.ilhamsetyabudi_mealdb.data.database.MealEntity
import com.bootcamp.ilhamsetyabudi_mealdb.databinding.ActivityDetailFavoriteBinding
import com.bootcamp.ilhamsetyabudi_mealdb.viewmodels.DetailFavoriteViewModel

class DetailFavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailFavoriteBinding
    private val favoriteDetailViewModel by viewModels<DetailFavoriteViewModel>()

    companion object {
        const val EXTRA_FAVORITE_MEAL = "favorite_meal"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.apply {
            title = "Meal Detail"
            setBackgroundDrawable(ColorDrawable(Color.parseColor("#1a1c1e")))
            setDisplayHomeAsUpEnabled(true)
        }

        val favoriteMeal = intent.getParcelableExtra<MealEntity>(EXTRA_FAVORITE_MEAL)
        binding.mealDetail = favoriteMeal!!.meal

        binding.apply {
            favoriteBtn.apply {
                setImageResource(R.drawable.favorite_added)
                setOnClickListener {
                    deleteFavoriteMeal(favoriteMeal)
                    val intent = Intent(this@DetailFavoriteActivity, FavoriteActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }
    private fun deleteFavoriteMeal(mealEntity: MealEntity) {
        favoriteDetailViewModel.deleteFavoriteMeal(mealEntity)
        Toast.makeText(this, "Successfully remove from favorite", Toast.LENGTH_SHORT).show()
    }
}