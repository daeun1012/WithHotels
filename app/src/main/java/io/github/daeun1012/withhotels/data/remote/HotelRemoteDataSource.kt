package io.github.daeun1012.withhotels.data.remote

class HotelRemoteDataSource(private val hotelService: HotelService) {
    fun fetchHotels(page: Int) = hotelService.getHotelList(page)
}