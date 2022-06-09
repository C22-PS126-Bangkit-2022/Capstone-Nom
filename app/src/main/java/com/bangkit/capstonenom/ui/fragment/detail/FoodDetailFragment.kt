package com.bangkit.capstonenom.ui.fragment.detail

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.capstonenom.R
import com.bangkit.capstonenom.adapter.FoodDetailAdapter
import com.bangkit.capstonenom.databinding.FragmentFoodDetailBinding
import com.bangkit.capstonenom.model.FoodInformation
import com.bangkit.capstonenom.utils.ViewModelFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class FoodDetailFragment : Fragment() {
    private var id: Int? = null
    private lateinit var adapter: FoodDetailAdapter
    private var isHistoryPage = false
    private lateinit var binding: FragmentFoodDetailBinding
    private lateinit var detailsViewModel: FoodDetailViewModel
    private var foodInformation: FoodInformation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getInt(ID)
            isHistoryPage = it.getBoolean(HIST)
        }
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFoodDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showLoading(true)
        val viewModel = ViewModelFactory.getInstance(requireContext())
        detailsViewModel = ViewModelProvider(this, viewModel)[FoodDetailViewModel::class.java]

        adapter = FoodDetailAdapter(requireContext())
        binding.apply {
            rvNutrients.adapter = this@FoodDetailFragment.adapter
            rvNutrients.layoutManager = LinearLayoutManager(context)
            rvNutrients.setHasFixedSize(true)
        }
        setUpObserver()
        setInformationFood()
    }

    private fun setUpObserver() {
        if (isHistoryPage) {
            detailsViewModel.getFoodHistoryById(id as Int).observe(viewLifecycleOwner) {
                foodInformation = it
                information(it)
            }
        } else {
            detailsViewModel.getFoodDetail(id as Int).observe(viewLifecycleOwner) {
                foodInformation = it
                information(it)
                if (it.id != -1) {
                    detailsViewModel.addToHistory(it)
                }
            }
        }
    }

    private fun information(foodInformation: FoodInformation?) {
        if (foodInformation != null) {
            binding.apply {
                adapter.setListData(foodInformation.nutrients)
                tvName.text = foodInformation.name
                Glide.with(requireContext())
                    .load(foodInformation.imageUrl)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_place_holder)
                            .error(R.drawable.ic_place_holder)
                    )
                    .into(imgItemFood)

                with(foodInformation.weightPerServing) {
                    tvServingSize.text = getString(R.string.serving_size, this.amount, this.unit)
                }
                with(foodInformation.caloricBreakdown) {
                    tvPercCarbs.text = getString(R.string.percentage, this.percentCarbs)
                    tvPercFat.text = getString(R.string.percentage, this.percentFat)
                    tvPercProtein.text = getString(R.string.percentage, this.percentProtein)
                }
            }
            showLoading(false)
        }
    }

    private fun setInformationFood() {
        detailsViewModel.getFoodDetail(id as Int).observe(viewLifecycleOwner) {
            foodInformation = it
            information(it)
        }
    }

    private fun showLoading(state: Boolean) {
        binding.progressBar.visibility = if (state) View.VISIBLE else View.GONE
    }

    companion object {
        private const val ID = "id"
        private const val HIST = "history"
    }
}