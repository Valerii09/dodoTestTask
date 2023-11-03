package com.example.dodotesttask.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MenuViewModelFactory(private val sharedViewModel: SharedViewModel,
    private val dishRepository: DishRepository

) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MenuViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MenuViewModel(sharedViewModel, dishRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

