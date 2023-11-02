package com.example.dodotesttask.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dodotesttask.R
import com.example.dodotesttask.model.Dish

class DishRepository {
    // Здесь вы можете использовать базу данных, сетевые вызовы или другие источники данных
    private val dishesLiveData = MutableLiveData<List<Dish>>()

    fun getDishes(): LiveData<List<Dish>> {
        // Здесь загрузка блюд из источника данных (например, из базы данных или сети)
        // В данном случае, эмулируем загрузку данных
        val dishes = mutableListOf<Dish>()
        dishes.add(Dish("Пицца", "Аппетитная пицца с разнообразными топпингами", 9.99, R.drawable.pizza_image))
        dishes.add(Dish("Салат Цезарь", "Свежий салат с курицей и соусом Цезарь", 7.49, R.drawable.pizza_image))
        // Добавьте другие блюда по аналогии

        dishesLiveData.value = dishes
        return dishesLiveData
    }
}
