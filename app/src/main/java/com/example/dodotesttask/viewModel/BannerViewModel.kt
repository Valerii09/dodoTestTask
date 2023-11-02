package com.example.dodotesttask.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dodotesttask.model.Banner

class BannerViewModel : ViewModel() {
    // Модель данных для списка баннеров
    val bannerList: MutableLiveData<List<Banner>> = MutableLiveData()

    // Загрузка данных (в данном случае, список баннеров)
    fun loadBanners() {
        bannerList.value = retrieveBanners()
    }

    // Вспомогательный метод для получения данных (замените его на ваш метод загрузки данных)
    private fun retrieveBanners(): List<Banner> {
        // Здесь загружаются и возвращаются данные баннеров (в вашем случае, список Banner)
        return listOf(
            Banner("banner_image.svg"),
            Banner("banner_image.svg"),
            // Добавьте остальные элементы списка баннеров
        )
    }
}

