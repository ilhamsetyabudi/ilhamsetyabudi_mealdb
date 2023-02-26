package com.bootcamp.ilhamsetyabudi_mealdb.ui

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.bootcamp.ilhamsetyabudi_mealdb.R
import com.bootcamp.ilhamsetyabudi_mealdb.adapter.FavoriteAdapter
import com.bootcamp.ilhamsetyabudi_mealdb.data.database.MealEntity
import com.bootcamp.ilhamsetyabudi_mealdb.databinding.ActivityFavoriteBinding
import com.bootcamp.ilhamsetyabudi_mealdb.viewmodels.FavoriteViewModel

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    private val favoriteViewModel by viewModels<FavoriteViewModel>()
    private val favoriteAdapter by lazy { FavoriteAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.apply {
            title = "List Favorite"
            setBackgroundDrawable(ColorDrawable(Color.parseColor("#1a1c1e")))
            setDisplayHomeAsUpEnabled(true)
        }

        favoriteViewModel.favoriteMealList.observe(this) { result ->
            if (result.isEmpty()) {
                binding.apply {
                    rvFavoriteMeal.isVisible = false
                    emptyIcon.isVisible = true
                    emptyTv.isVisible = true
                }
            } else {
                binding.apply {
                    rvFavoriteMeal.apply {
                        adapter = favoriteAdapter
                        layoutManager = LinearLayoutManager(this@FavoriteActivity)
                    }
                }
                favoriteAdapter.apply {
                    setData(result)
                    setOnItemClickCallback(object : FavoriteAdapter.IOnFavoriteItemCallBack {
                        override fun onFavoriteItemClickCallback(data: MealEntity) {
                            val intent = Intent(this@FavoriteActivity, DetailFavoriteActivity::class.java)
                            intent.putExtra(DetailFavoriteActivity.EXTRA_FAVORITE_MEAL, data)
                            startActivity(intent)
                        }
                    })
                }
            }
        }
    }
}