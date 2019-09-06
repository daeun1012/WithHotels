package io.github.daeun1012.withhotels.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import io.github.daeun1012.withhotels.data.HotelResult
import io.github.daeun1012.withhotels.data.local.Hotel
import io.github.daeun1012.withhotels.data.remote.HotelRepository

class HotelListViewModel internal constructor(private val hotelRepository: HotelRepository) : ViewModel() {

    private var hotelResult: MutableLiveData<HotelResult> = MutableLiveData()
    val networkErrors: LiveData<String> = Transformations.switchMap(hotelResult) {
        it.networkErrors
    }
    val hotels: LiveData<List<Hotel>> = Transformations.switchMap(hotelResult) {
        it.data
    }

    fun getHotels() {
        hotelResult.value = hotelRepository.requestHotels()
    }
}