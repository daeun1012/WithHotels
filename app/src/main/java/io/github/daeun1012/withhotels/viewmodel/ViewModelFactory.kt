package io.github.daeun1012.withhotels.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.github.daeun1012.withhotels.data.remote.HotelRepository

class ViewModelFactory(private val repository: HotelRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HotelListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HotelListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
