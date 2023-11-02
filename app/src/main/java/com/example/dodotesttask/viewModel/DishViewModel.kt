package com.example.dodotesttask.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.dodotesttask.model.Dish

class DishViewModel(private val repository: DishRepository) : ViewModel() {
    fun getDishes(): LiveData<List<Dish>> {
        return repository.getDishes()
    }
}
