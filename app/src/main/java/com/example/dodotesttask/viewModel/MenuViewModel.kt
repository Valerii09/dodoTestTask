package com.example.dodotesttask.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dodotesttask.model.Dish
import com.example.dodotesttask.model.MenuCategory

class MenuViewModel(
    private val sharedViewModel: SharedViewModel,
    private val dishRepository: DishRepository
) : ViewModel() {
    private val dishesLiveData = MutableLiveData<Map<String, List<Dish>>>()
    val dishes: LiveData<Map<String, List<Dish>>> = dishesLiveData
    private val scrollToCategoryPosition = MutableLiveData<Int>()
    private val categoriesLiveData = MutableLiveData<List<MenuCategory>>()

    // Определяем MutableLiveData для отслеживания выбранной категории
    private val selectedCategory = MutableLiveData<MenuCategory>()

    init {
        dishRepository.getDishes().observeForever { dishes ->
            dishesLiveData.value = dishes ?: emptyMap()
        }


        // Здесь вы можете инициализировать список категорий меню
        val categoriesList = listOf(
            MenuCategory("Пицца", "pizza_tag"),
            MenuCategory("Комбо", "combo_tag"),
            MenuCategory("Десерты", "desserts_tag"),
            MenuCategory("Напитки", "drinks_tag")
        )

        categoriesLiveData.value = categoriesList
    }

    fun getMenuCategories(): LiveData<List<MenuCategory>> {
        return categoriesLiveData
    }

    fun getScrollToCategoryPosition(): LiveData<Int> {
        return scrollToCategoryPosition
    }

    fun setSelectedCategory(category: MenuCategory) {
        selectedCategory.value = category
        val categories = categoriesLiveData.value ?: emptyList()
        val index = categories.indexOf(category)
        sharedViewModel.setScrollToCategoryPosition(index)
    }
}