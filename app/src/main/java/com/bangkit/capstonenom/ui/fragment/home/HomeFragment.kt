package com.bangkit.capstonenom.ui.fragment.home

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.capstonenom.adapter.FoodAdapter
import com.bangkit.capstonenom.databinding.FragmentFoodBinding
import com.bangkit.capstonenom.databinding.FragmentHomeBinding
import com.bangkit.capstonenom.ui.activity.add.AddFoodActivity
import com.bangkit.capstonenom.ui.fragment.food.FoodViewModel
import com.bangkit.capstonenom.utils.ViewModelFactory

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var foodAdapter: FoodAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        foodAdapter = FoodAdapter()

        _binding = FragmentHomeBinding.bind(view)

        binding.btnTakeFood.setOnClickListener {
            GoTo()
        }

        searchFood()
        viewModel()
        recyclerView()
    }

    private fun searchFood() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                showLoading(true)
                if (query.isNullOrBlank()) {
                    viewModel.getFoodName("Banana")
                } else {
                    viewModel.getFoodName(query)
                }
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if (query.isNullOrBlank()) {
                    showLoading(true)
                    viewModel.getFoodName("Banana")
                }
                return false
            }
        })
    }

    private fun viewModel() {
        val factory = ViewModelFactory.getInstance(requireContext())
        viewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]
        viewModel.getFoodName("Banana")
        getFood()
    }

    private fun recyclerView() {
        with(binding.rvFood) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = foodAdapter
        }
    }

    private fun getFood() {
        viewModel.foodList.observe(viewLifecycleOwner, { foodList ->
            showLoading(false)
            foodAdapter.setListData(foodList)
        })
    }

    private fun GoTo() {
        val intent = Intent(context, AddFoodActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }

    private fun showLoading(state: Boolean) {
        binding.progressBar.visibility = if (state) View.VISIBLE else View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}