package com.bangkit.capstonenom.ui.fragment.home

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.capstonenom.adapter.FoodAdapter
import com.bangkit.capstonenom.databinding.FragmentFoodBinding
import com.bangkit.capstonenom.databinding.FragmentHomeBinding
import com.bangkit.capstonenom.ui.activity.add.AddFoodActivity
import com.bangkit.capstonenom.ui.fragment.food.FoodViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var adapter: FoodAdapter
    private lateinit var viewModel: HomeViewModel



    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.tvWelcome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentHomeBinding.bind(view)

        adapter = FoodAdapter()
        adapter.notifyDataSetChanged()

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(HomeViewModel::class.java)
        binding.apply {
            rvFood.layoutManager = LinearLayoutManager(activity)
            rvFood.setHasFixedSize(true)
            rvFood.adapter = adapter

            btnSearch.setOnClickListener {
                searchFood()
            }

            edtQuery.setOnKeyListener { v, keyCode, event ->
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false
            }
        }


        binding.btnTakeFood.setOnClickListener {
            GoTo()
        }
    }

    private fun searchFood() {
        binding.apply {
            val query = edtQuery.text.toString()
            if (query.isEmpty()) return
            showLoading(true)
            viewModel.setSearchFood(query)
        }
    }



    private fun GoTo() {
        val intent = Intent(context, AddFoodActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showLoading(state: Boolean) {
        binding.progressBar.visibility = if (state) View.VISIBLE else View.GONE
    }
}