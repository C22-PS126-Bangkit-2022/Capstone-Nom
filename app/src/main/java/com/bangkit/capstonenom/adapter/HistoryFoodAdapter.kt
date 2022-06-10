package com.bangkit.capstonenom.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.capstonenom.R
import com.bangkit.capstonenom.databinding.ItemRowHistoryBinding
import com.bangkit.capstonenom.model.FoodInformation
import com.bumptech.glide.Glide
import java.util.*

class HistoryFoodAdapter : RecyclerView.Adapter<HistoryFoodAdapter.ListViewHolder>() {

    private var listData = ArrayList<FoodInformation>()
    var onItemClick: ((FoodInformation) -> Unit)? = null
    var onDeleteIconClick: ((FoodInformation) -> Unit)? = null

    fun setData(data: List<FoodInformation>) {
        listData.clear()
        listData.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HistoryFoodAdapter.ListViewHolder =
        ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_row_history, parent, false)
        )

    override fun onBindViewHolder(holder: HistoryFoodAdapter.ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount() = listData.size

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemRowHistoryBinding.bind(itemView)
        fun bind(data: FoodInformation) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.imageUrl)
                    .into(imgFood)

                tvName.text = data.name.capitalize(Locale.ROOT)

                icDelete.setOnClickListener {
                    onDeleteIconClick?.invoke(data)
                }
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}