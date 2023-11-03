package com.example.dodotesttask.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private val scrollToCategoryPosition = MutableLiveData<Int>()

    fun setScrollToCategoryPosition(position: Int) {
        scrollToCategoryPosition.value = position
    }

    fun getScrollToCategoryPosition(): LiveData<Int> {
        return scrollToCategoryPosition
    }
}
