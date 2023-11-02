package com.example.dodotesttask.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dodotesttask.databinding.FragmentHomeBinding
import com.example.dodotesttask.viewModel.DishAdapter
import com.example.dodotesttask.viewModel.DishRepository
import com.example.dodotesttask.viewModel.DishViewModel
import com.example.dodotesttask.viewModel.DishViewModelFactory


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

        val repository = DishRepository() // Создание экземпляра репозитория

        val viewModelFactory = DishViewModelFactory(repository) // Создание экземпляра фабрики

        dishViewModel = ViewModelProvider(this, viewModelFactory).get(DishViewModel::class.java)
        dishViewModel.getDishes().observe(viewLifecycleOwner, { dishes ->
            val allDishes = dishes.values.flatten() // Объединение всех списков блюд из категорий в один список
            adapter.submitList(allDishes) // Передача общего списка блюд в адаптер
        })
        recyclerView.adapter = adapter

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

