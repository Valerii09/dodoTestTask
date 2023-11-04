package com.example.dodotesttask.ui.home

import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dodotesttask.R
import com.example.dodotesttask.databinding.FragmentHomeBinding
import com.example.dodotesttask.viewModel.DishAdapter
import com.example.dodotesttask.viewModel.DishRepository
import com.example.dodotesttask.viewModel.DishViewModel
import com.example.dodotesttask.viewModel.DishViewModelFactory
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var dishViewModel: DishViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentManager = childFragmentManager

        // Добавление фрагмента CityInfoFragment
        val cityInfoFragment = CityInfoFragment()
        fragmentManager.beginTransaction().replace(R.id.cityInfoContainer, cityInfoFragment).commit()

        // Добавление фрагмента CategoriesFragment
        val categoriesFragment = CategoriesFragment()
        fragmentManager.beginTransaction().replace(R.id.category_container, categoriesFragment).commit()

        val recyclerView: RecyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(activity)
        val adapter = DishAdapter()

        val repository = DishRepository()
        val viewModelFactory = DishViewModelFactory(repository)
        dishViewModel = ViewModelProvider(this, viewModelFactory).get(DishViewModel::class.java)
        dishViewModel.getDishes().observe(viewLifecycleOwner, { dishes ->
            adapter.submitList(dishes)
        })

        recyclerView.adapter = adapter


        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val bannerFragmentView = binding.bannerFragmentContainer
                val cityInfoFragmentView = binding.cityInfoContainer

                // Проверяем, виден ли BannerFragment
                val bannerVisible = isViewVisible(bannerFragmentView)

                // Проверяем, виден ли CityInfoFragment
                val cityInfoFragmentVisible = isViewVisible(cityInfoFragmentView)

                if (bannerVisible) {
                    // Блокируем прокрутку RecyclerView, если виден BannerFragment
                    recyclerView.setOnTouchListener { _, _ -> true }
                } else if (cityInfoFragmentVisible) {
                    // Разрешаем прокрутку для CityInfoFragment
                    recyclerView.setOnTouchListener { _, _ -> false }
                } else {
                    // Блокируем все, когда BannerFragment не виден
                    recyclerView.setOnTouchListener { _, _ -> true }
                }
            }
        })
    }


    // Функция для проверки видимости view
    private fun isViewVisible(view: View): Boolean {
        val scrollBounds = Rect()
        view.getDrawingRect(scrollBounds)
        val container = view.parent as View
        return view.getLocalVisibleRect(scrollBounds) && container.bottom >= view.bottom
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

