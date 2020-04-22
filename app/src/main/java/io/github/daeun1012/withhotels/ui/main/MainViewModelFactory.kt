package io.github.daeun1012.withhotels.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.github.daeun1012.withhotels.data.repository.HotelRepository
import io.github.daeun1012.withhotels.data.repository.LocalRepository

class MainViewModelFactory(private val repository: HotelRepository, private val localRepository: LocalRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = MainViewModel(
        repository,
        localRepository
    ) as T

}