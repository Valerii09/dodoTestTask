package com.example.dodotesttask.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dodotesttask.R
import com.example.dodotesttask.model.Dish
import com.example.dodotesttask.viewModel.DishRepository
import com.example.dodotesttask.viewModel.MenuCategoryAdapter
import com.example.dodotesttask.viewModel.MenuViewModel
import com.example.dodotesttask.viewModel.MenuViewModelFactory
import com.example.dodotesttask.viewModel.SharedViewModel

class MenuFragment : Fragment() {
    private lateinit var menuViewModel: MenuViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_menu, container, false)

        // Получение SharedViewModel из родительской активности
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        val dishRepository = DishRepository()
        menuViewModel = ViewModelProvider(this, MenuViewModelFactory(sharedViewModel, dishRepository)).get(MenuViewModel::class.java)

        recyclerView = view.findViewById(R.id.recyclerView)
        setupRecyclerView()
        observeScrollToCategoryPosition()

        return view
    }
    private fun observeScrollToCategoryPosition() {
        sharedViewModel.getScrollToCategoryPosition().observe(viewLifecycleOwner, { position ->
            recyclerView.scrollToPosition(position)
        })
    }
    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = layoutManager

        val adapter = MenuCategoryAdapter { category ->
            menuViewModel.setSelectedCategory(category)
            Log.d("MenuFragment", "Category Clicked: ${category.name}")

            // Получаем позицию для прокрутки из MenuViewModel
            menuViewModel.getScrollToCategoryPosition().observe(viewLifecycleOwner, { position ->
                // Прокручиваем RecyclerView к выбранной позиции
                recyclerView.scrollToPosition(position)
            })
        }

        recyclerView.adapter = adapter

        menuViewModel.getMenuCategories().observe(viewLifecycleOwner, { categories ->
            adapter.submitList(categories)
        })
    }
}
