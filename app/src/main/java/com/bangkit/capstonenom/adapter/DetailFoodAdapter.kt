package com.bangkit.capstonenom.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.capstonenom.R
import com.bangkit.capstonenom.databinding.ItemRowDetailFoodBinding
import com.bangkit.capstonenom.databinding.ItemRowFoodBinding
import com.bangkit.capstonenom.model.Food
import com.bangkit.capstonenom.model.FoodInformation
import com.bangkit.capstonenom.response.FoodInformationResponse
import com.bangkit.capstonenom.response.Nutrition
import com.bumptech.glide.Glide
import java.util.ArrayList
import kotlin.math.roundToInt

class DetailFoodAdapter(private val context: Context) : RecyclerView.Adapter<DetailFoodAdapter.ListViewHolder>() {

    private var listData = ArrayList<Nutrition>()
    private var onItemClickCallback: DetailFoodAdapter.OnItemClickCallback? = null

    interface OnItemClickCallback {
        fun onItemClicked(data: Nutrition)
    }

    fun setListData(newListData: List<Nutrition>) {
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_row_detail_food, parent, false)
        )

    override fun onBindViewHolder(holder: DetailFoodAdapter.ListViewHolder, position: Int) {
        val item = listData[position]
        holder.bind(item)
    }

    override fun getItemCount() = listData.size

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemRowDetailFoodBinding.bind(itemView)
        fun bind(food: Nutrition) {
            binding.root.setOnClickListener {
                onItemClickCallback?.onItemClicked(food)
            }
            binding.apply {
                tvNutritionName.text = food.name
                tvNutritionAmount.text = context.getString(food.amount.roundToInt(), food.unit)
            }
        }
    }
}