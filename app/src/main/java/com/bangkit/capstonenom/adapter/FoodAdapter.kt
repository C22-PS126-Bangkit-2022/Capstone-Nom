package com.bangkit.capstonenom.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.capstonenom.R
import com.bangkit.capstonenom.databinding.ItemRowFoodBinding
import com.bangkit.capstonenom.model.Food
import com.bumptech.glide.Glide
import java.util.ArrayList

class FoodAdapter : RecyclerView.Adapter<FoodAdapter.ListViewHolder>() {

    private var listData = ArrayList<Food>()
    var onItemClick: ((Food) -> Unit)? = null

    fun setListData(newListData: List<Food>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_row_food, parent, false)
        )

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemRowFoodBinding.bind(itemView)

        fun bind(food: Food) {
            binding.apply {
                tvItemName.text = food.name
                Glide.with(itemView)
                    .load(food.imageUrl)
                    .placeholder(R.drawable.anim_progress_icon)
                    .error(R.drawable.ic_place_holder)
                    .into(imgItemFood)
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}