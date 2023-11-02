package com.example.dodotesttask.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dodotesttask.R
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dodotesttask.model.Banner
import com.example.dodotesttask.viewModel.BannerAdapter

class BannerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_banners, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewBanners)

        // Создание списка баннеров (просто для примера)
        val bannerList = listOf(
            Banner(1, "Banner 1", "https://example.com/banner1.jpg"),
            Banner(2, "Banner 2", "https://example.com/banner2.jpg"),
            Banner(3, "Banner 3", "https://example.com/banner3.jpg")
            // Добавьте столько элементов, сколько вам нужно
        )

        // Создание RecyclerViewAdapter для списка баннеров
        val adapter = BannerAdapter(bannerList) // Передача списка баннеров в адаптер
        recyclerView.adapter = adapter

        // Установка горизонтального LinearLayoutManager для RecyclerView
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = layoutManager

        return view
    }
}
