package com.bangkit.capstonenom.ui.activity.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bangkit.capstonenom.R
import com.bangkit.capstonenom.databinding.ActivityFoodDetailBinding
import com.bangkit.capstonenom.model.Food
import com.bangkit.capstonenom.model.FoodInformation
import com.bangkit.capstonenom.utils.ViewModelFactory
import com.bumptech.glide.Glide

class FoodDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFoodDetailBinding
    private lateinit var viewModel: FoodDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFoodDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getIntExtra(EXTRA_ID, 1)
        val bundle = Bundle()
        bundle.putInt(EXTRA_ID, id)

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            FoodDetailViewModel::class.java)

        viewModel.setFoodDetail(id)
        viewModel.getDetailFood().observe(this){
            if (it != null){
                binding.apply {
                    Glide.with(this@FoodDetailActivity)
                        .load(it.image)
                        .placeholder(R.drawable.anim_progress_icon)
                        .error(R.drawable.ic_place_holder)
                        .into(imgItemFood)
                    tvName.text = it.name
                }
            }
        }
    }

    companion object{
        const val EXTRA_ID = "extra_id"
    }
}