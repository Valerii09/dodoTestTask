package com.example.dodotesttask.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dodotesttask.R
import com.example.dodotesttask.viewModel.MenuCategoryAdapter
import com.example.dodotesttask.viewModel.MenuViewModel

class MenuFragment : Fragment() {
    private lateinit var menuViewModel: MenuViewModel
    private lateinit var recyclerView: RecyclerView

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_menu, container, false)

        menuViewModel = ViewModelProvider(this).get(MenuViewModel::class.java)
        recyclerView = view.findViewById(R.id.recyclerView)

        setupRecyclerView()

        return view
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = layoutManager

        val adapter = MenuCategoryAdapter()
        recyclerView.adapter = adapter

        menuViewModel.getMenuCategories().observe(viewLifecycleOwner, { categories ->
            adapter.submitList(categories)
        })
    }
}
