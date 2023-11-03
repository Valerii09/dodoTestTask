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
import com.example.dodotesttask.databinding.FragmentHomeBinding
import com.example.dodotesttask.viewModel.DishAdapter
import com.example.dodotesttask.viewModel.DishRepository
import com.example.dodotesttask.viewModel.DishViewModel
import com.example.dodotesttask.viewModel.DishViewModelFactory


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var dishViewModel: DishViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val recyclerView: RecyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(activity)
        val adapter = DishAdapter()

        val repository = DishRepository() // Создание экземпляра репозитория

        val viewModelFactory = DishViewModelFactory(repository) // Создание экземпляра фабрики

        dishViewModel = ViewModelProvider(this, viewModelFactory).get(DishViewModel::class.java) // Получение DishViewModel через фабрику
        dishViewModel.getDishes().observe(viewLifecycleOwner, { dishes ->
            adapter.submitList(dishes)
        })

        recyclerView.adapter = adapter

        val fragmentTransaction = childFragmentManager.beginTransaction()
        val categoriesFragment = CategoriesFragment()

        fragmentTransaction.replace(R.id.category_container, categoriesFragment)
        fragmentTransaction.commit()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
