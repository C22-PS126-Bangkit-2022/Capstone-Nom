package com.bangkit.capstonenom.ui.fragment.home

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.bangkit.capstonenom.adapter.FoodAdapter
import com.bangkit.capstonenom.databinding.FragmentHomeBinding
import com.bangkit.capstonenom.ui.activity.add.AddFoodActivity
import com.bangkit.capstonenom.ui.fragment.settings.SettingsViewModel
import com.bangkit.capstonenom.ui.fragment.settings.darkmode.SettingPreferences
import com.bangkit.capstonenom.utils.ViewModelFactory

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

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
        return binding.root
    }

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar!!.hide()
        _binding = FragmentHomeBinding.bind(view)

        binding.btnTakeFood.setOnClickListener {
            goTo()
        }

        searchFood()
        viewModel()
        getFoodDetail()
        recyclerView()

        val pref = SettingPreferences.getInstance(requireContext().dataStore)
        val settingsViewModel = ViewModelProvider(
            this,
            com.bangkit.capstonenom.ui.fragment.settings.darkmode.ViewModelFactory(pref)
        ).get(
            SettingsViewModel::class.java
        )

        settingsViewModel.getThemeSettings().observe(this,
            { isDarkModeActive: Boolean ->
                if (isDarkModeActive) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
            })
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
            layoutManager = GridLayoutManager(context, 2)
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

    private fun getFoodDetail() {
        foodAdapter = FoodAdapter()
        foodAdapter.onItemClick = { selectedFood ->
            intentToDetail(selectedFood.id)
        }
    }

    private fun intentToDetail(id: Int) {
        val action = HomeFragmentDirections.actionNavigationToDetail(id = id)
        findNavController().navigate(action)
    }

    private fun goTo() {
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