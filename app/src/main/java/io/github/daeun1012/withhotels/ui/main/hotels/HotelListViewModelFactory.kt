package io.github.daeun1012.withhotels.ui.main.hotels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.github.daeun1012.withhotels.data.repository.HotelRepository

class HotelListViewModelFactory(private val repository: HotelRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = HotelListViewModel(repository) as T

}