package com.example.dodotesttask.viewModel.Categories

import androidx.lifecycle.ViewModel


class CategoriesViewModel : ViewModel() {
    private val categories: List<String> = listOf("Пицца", "Комбо", "Десерты", "Напитки")

    fun getCategories(): List<String> {
        return categories
    }
}
