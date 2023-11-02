package com.example.dodotesttask.model

data class Dish(
    val name: String,
    val description: String,
    val price: Double,
    val imageResourceId: Int // предположим, что картинки хранятся как ресурсы
)
