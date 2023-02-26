package com.bootcamp.ilhamsetyabudi_mealdb.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bootcamp.ilhamsetyabudi_mealdb.data.database.MealEntity
import com.bootcamp.ilhamsetyabudi_mealdb.databinding.FavoriteRowLayoutBinding
import com.bootcamp.ilhamsetyabudi_mealdb.databinding.MealRowLayoutBinding

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    private val diffCallBack = object : DiffUtil.ItemCallback<MealEntity>() {
        override fun areItemsTheSame(oldItem: MealEntity, newItem: MealEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MealEntity, newItem: MealEntity): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallBack)
    private lateinit var onFavoriteItemCallBack: IOnFavoriteItemCallBack

    inner class FavoriteViewHolder(private val binding: FavoriteRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MealEntity) {
            binding.apply {
                datas = item.meal
                itemView.setOnClickListener {
                    onFavoriteItemCallBack.onFavoriteItemClickCallback(item)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val rowBinding = FavoriteRowLayoutBinding.inflate(layoutInflater, parent, false)
        return FavoriteViewHolder(rowBinding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val itemData = differ.currentList[position]!!
        holder.bind(itemData)
    }

    fun setData(list: List<MealEntity?>?) {
        differ.submitList(list ?: emptyList())
    }

    fun setOnItemClickCallback(action: IOnFavoriteItemCallBack) {
        this.onFavoriteItemCallBack = action
    }

    interface IOnFavoriteItemCallBack {
        fun onFavoriteItemClickCallback(data: MealEntity)
    }
}
