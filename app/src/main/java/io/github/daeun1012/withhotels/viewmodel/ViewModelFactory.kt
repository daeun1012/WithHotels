package io.github.daeun1012.withhotels.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.github.daeun1012.withhotels.data.repository.HotelRepository
import io.github.daeun1012.withhotels.ui.main.hotels.HotelListViewModel

class ViewModelFactory(private val repository: HotelRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HotelListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HotelListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

//class ViewModelFactory constructor(private val viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>>) :
//    ViewModelProvider.Factory {
//
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        val creator = viewModels[modelClass]
//            ?: viewModels.asIterable().firstOrNull { modelClass.isAssignableFrom(it.key) }?.value
//            ?: throw IllegalArgumentException("unknown model class $modelClass")
//        return try {
//            creator.get() as T
//        } catch (e: Exception) {
//            throw RuntimeException(e)
//        }
//    }
//}