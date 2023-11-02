package com.example.dodotesttask.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dodotesttask.model.MenuCategory

class MenuViewModel : ViewModel() {
    private val categoriesLiveData = MutableLiveData<List<MenuCategory>>()

    init {
        // Здесь вы можете инициализировать список категорий меню
        val categoriesList = listOf(
            MenuCategory("Пицца"),
            MenuCategory("Комбо"),
            MenuCategory("Десерты"),
            MenuCategory("Напитки")
        )
        categoriesLiveData.value = categoriesList
    }

    fun getMenuCategories(): LiveData<List<MenuCategory>> {
        return categoriesLiveData
    }
}
