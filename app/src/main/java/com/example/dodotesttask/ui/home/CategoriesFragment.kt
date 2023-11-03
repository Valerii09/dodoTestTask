package com.example.dodotesttask.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dodotesttask.R
import com.example.dodotesttask.viewModel.Categories.CategoriesViewModel
import com.example.dodotesttask.viewModel.Categories.CategoryAdapter

class CategoriesFragment : Fragment() {
    private lateinit var viewModel: CategoriesViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_categories, container, false)
        recyclerView = view.findViewById(R.id.recycler_view_categories)

        // Устанавливаем менеджер компоновки с горизонтальной ориентацией для RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)

        viewModel = ViewModelProvider(this).get(CategoriesViewModel::class.java)

        val categories = viewModel.getCategories()

        adapter = CategoryAdapter(categories)
        recyclerView.adapter = adapter

        return view
    }
}
