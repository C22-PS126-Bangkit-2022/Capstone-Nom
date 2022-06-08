package com.bangkit.capstonenom.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.capstonenom.R
import com.bangkit.capstonenom.databinding.ItemRowDetailFoodBinding
import com.bangkit.capstonenom.response.Nutrition
import com.bangkit.capstonenom.response.NutritionItem
import java.util.ArrayList
import kotlin.math.roundToInt

class FoodDetailAdapter(private val context: Context) :
    RecyclerView.Adapter<FoodDetailAdapter.ListViewHolder>() {

    private var nutrients = ArrayList<NutritionItem>()

    fun setListData(newListData: List<NutritionItem>) {
        nutrients.clear()
        nutrients.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_row_detail_food, parent, false)
        )

    override fun onBindViewHolder(holder: FoodDetailAdapter.ListViewHolder, position: Int) {
        val item = nutrients[position]
        holder.bind(item)
    }

    override fun getItemCount() = nutrients.size

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemRowDetailFoodBinding.bind(itemView)
        fun bind(food: NutritionItem) {
            binding.apply {
                tvNutritionName.text = food.name
                tvNutritionAmount.text = context.getString(R.string.nutrient_amount, food.amount, food.unit)
            }
        }
    }
}