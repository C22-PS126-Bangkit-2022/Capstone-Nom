package com.bangkit.capstonenom.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.capstonenom.databinding.ItemRowFoodBinding
import com.bangkit.capstonenom.model.Food
import com.bumptech.glide.Glide

class FoodAdapter: RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    private val list = ArrayList<Food>()
    private var onItemClickCallback: OnItemClickCallback? = null

    interface OnItemClickCallback{
        fun onItemClicked(data: Food)
    }

    fun setOnItemClickCallback (onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodAdapter.FoodViewHolder {
        val view = ItemRowFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodAdapter.FoodViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    inner class FoodViewHolder(val binding: ItemRowFoodBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(food: Food){
            binding.root.setOnClickListener {
                onItemClickCallback?.onItemClicked(food)
            }

            binding.apply {
                tvItemName.text = food.food_name
                Glide.with(itemView)
                    .load(imgItemFood)
                    .into(imgItemFood)
            }
        }
    }

    fun setList(users: ArrayList<Food>){
        list.clear()
        list.addAll(users)
        notifyDataSetChanged()
    }
}