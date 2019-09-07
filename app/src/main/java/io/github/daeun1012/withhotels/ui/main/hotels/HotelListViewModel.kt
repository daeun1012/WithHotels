package io.github.daeun1012.withhotels.ui.main.hotels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import io.github.daeun1012.withhotels.data.local.Hotel
import io.github.daeun1012.withhotels.data.repository.HotelRepository
import io.reactivex.disposables.CompositeDisposable

class HotelListViewModel(private val repository: HotelRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    var pagedListHotel = MutableLiveData<PagedList<Hotel>>()

    fun getHotels() {
        compositeDisposable.add(
            repository.fetchOrGetHotels()
            .subscribe({pagedListHotel.value = it}, {it.printStackTrace()}))
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}