package com.bangkit.capstonenom.ui.fragment.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.capstonenom.adapter.HistoryFoodAdapter
import com.bangkit.capstonenom.databinding.FragmentHistoryBinding
import com.bangkit.capstonenom.model.FoodInformation
import com.bangkit.capstonenom.utils.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import java.util.*

class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var historyViewModel: HistoryViewModel
    private lateinit var historyAdapter: HistoryFoodAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = ViewModelFactory.getInstance(requireContext())
        historyViewModel =
            ViewModelProvider(this, factory)[HistoryViewModel::class.java]

        setAdapter()
        historyViewModel.getAllFoodHistory().observe(viewLifecycleOwner) {
            updateData(it)
        }

        recyclerView()
    }

    private fun recyclerView() {
        with(binding.rvHistoryList) {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = historyAdapter
        }
    }

    private fun updateData(data: List<FoodInformation>?) {
        if (data != null && data.isNotEmpty()) {
            historyAdapter.setData(data)
            binding.tvNoHist.visibility = View.GONE
            binding.rvHistoryList.visibility = View.VISIBLE
        } else {
            binding.tvNoHist.visibility = View.VISIBLE
            binding.rvHistoryList.visibility = View.GONE
        }
    }

    private fun setAdapter() {
        historyAdapter = HistoryFoodAdapter()
        historyAdapter.onItemClick = {
            val action = HistoryFragmentDirections.actionNavigationHistoryToFoodDetailsFragment(
                id = it.id
            )
            findNavController().navigate(action)
        }
        historyAdapter.onDeleteIconClick = {
            historyViewModel.deleteFoodHistory(it)
            Snackbar.make(
                requireView(),
                "${it.name.capitalize(Locale.ROOT)} is removed from search history.",
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}