package com.bangkit.capstonenom.ui.activity.detail

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.capstonenom.R
import com.bangkit.capstonenom.adapter.DetailFoodAdapter
import com.bangkit.capstonenom.databinding.ActivityFoodDetailBinding
import com.bangkit.capstonenom.model.Food
import com.bangkit.capstonenom.model.FoodInformation
import com.bangkit.capstonenom.utils.ViewModelFactory
import com.bumptech.glide.Glide
import java.util.*

class FoodDetailActivity : AppCompatActivity() {

    private lateinit var detailAdapter: DetailFoodAdapter
    private lateinit var binding: ActivityFoodDetailBinding
    private lateinit var viewModel: FoodDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFoodDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getIntExtra(EXTRA_ID, 0)
        val bundle = Bundle()
        bundle.putInt(EXTRA_ID, id)

        with(binding.rvDetailFood) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[
            FoodDetailViewModel::class.java]

        viewModel.getFoodDetailsById(id).observe(this) {
            if (it != null){
                binding.apply {
                    tvName.text = it.name
                    Glide.with(this@FoodDetailActivity)
                        .load(it.imageUrl)
                        .circleCrop()
                        .into(imgItemFood)
                }
            }
        }
    }

    companion object{
        const val EXTRA_ID = "extra_id"
    }
}