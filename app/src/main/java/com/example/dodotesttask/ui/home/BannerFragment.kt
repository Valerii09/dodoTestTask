package com.example.dodotesttask.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.dodotesttask.R
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dodotesttask.model.Banner
import com.example.dodotesttask.viewModel.BannerAdapter
import com.example.dodotesttask.viewModel.BannerViewModel

class BannerFragment : Fragment() {
    private lateinit var bannerViewModel: BannerViewModel
    private lateinit var bannerAdapter: BannerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_banners, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewBanners)

        // Инициализация ViewModel
        bannerViewModel = ViewModelProvider(this).get(BannerViewModel::class.java)
        bannerViewModel.loadBanners()

        // Инициализация адаптера
        bannerAdapter = BannerAdapter(requireContext())

        // Получение данных из ViewModel
        bannerViewModel.bannerList.observe(viewLifecycleOwner, { banners ->
            // Обновление данных адаптера
            bannerAdapter.updateData(banners)
        })

        recyclerView.adapter = bannerAdapter
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        return view
    }
}




