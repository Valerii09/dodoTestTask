package com.example.dodotesttask.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dodotesttask.R
import com.example.dodotesttask.model.Dish

class DishRepository {
    private val dishesLiveData = MutableLiveData<Map<String, List<Dish>>>()

    fun getDishes(): LiveData<Map<String, List<Dish>>> {
        val categorizedDishes = mapOf(
            "Пицца" to listOf(
                Dish("Пицца 1", "Описание пиццы 1", 9.99, R.drawable.pizza_image),
                Dish("Пицца 2", "Описание пиццы 2", 8.99, R.drawable.pizza_image),
                Dish("Пицца 3", "Описание пиццы 3", 10.99, R.drawable.pizza_image)
                // Добавьте другие блюда категории "Пицца"
            ),
            "Комбо" to listOf(
                Dish("Салат 1", "Описание салата 1", 7.49, R.drawable.pizza_image),
                Dish("Салат 2", "Описание салата 2", 6.99, R.drawable.pizza_image)
                // Добавьте другие блюда категории "Салаты"
            ),
            "Десерты" to listOf(
                Dish("Салат 1", "Описание салата 1", 7.49, R.drawable.pizza_image),
                Dish("Салат 2", "Описание салата 2", 6.99, R.drawable.pizza_image)
                // Добавьте другие блюда категории "Салаты"
            ),
            "Напитки" to listOf(
                Dish("Салат 1", "Описание салата 1", 7.49, R.drawable.pizza_image),
                Dish("Салат 2", "Описание салата 2", 6.99, R.drawable.pizza_image)
                // Добавьте другие блюда категории "Салаты"
            ),
            // Добавьте другие категории блюд по аналогии
        )

        dishesLiveData.value = categorizedDishes
        return dishesLiveData
    }
}

