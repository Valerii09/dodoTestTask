package com.example.dodotesttask.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dodotesttask.R
import com.example.dodotesttask.databinding.FragmentHomeBinding
import com.example.dodotesttask.viewModel.DishAdapter
import com.example.dodotesttask.viewModel.DishRepository
import com.example.dodotesttask.viewModel.DishViewModel
import com.example.dodotesttask.viewModel.DishViewModelFactory
import com.example.dodotesttask.viewModel.SharedViewModel


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var dishViewModel: DishViewModel
    private lateinit var adapter: DishAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = DishAdapter()

        val repository = DishRepository()
        val viewModelFactory = DishViewModelFactory(repository)

        dishViewModel = ViewModelProvider(this, viewModelFactory).get(DishViewModel::class.java)
        dishViewModel.getDishes().observe(viewLifecycleOwner, { dishes ->
            val allDishes = dishes.values.flatten()
            adapter.submitList(allDishes)
        })
        recyclerView.adapter = adapter

        val sharedViewModel: SharedViewModel by activityViewModels()
        sharedViewModel.getScrollToCategoryPosition().observe(viewLifecycleOwner, { position ->
            recyclerView.scrollToPosition(position)
        })
        // Add MenuFragment to the container_for_menu_fragment
        val menuFragment = MenuFragment()
        childFragmentManager.beginTransaction()
            .replace(R.id.container_for_menu_fragment, menuFragment)
            .commit()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

