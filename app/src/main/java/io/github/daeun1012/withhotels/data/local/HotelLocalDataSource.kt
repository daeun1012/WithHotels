package io.github.daeun1012.withhotels.data.local

import io.github.daeun1012.withhotels.data.local.Hotel
import io.github.daeun1012.withhotels.data.local.dao.HotelDao

class HotelLocalDataSource(private val hotelDao: HotelDao) {
    fun storeHotels(hotelList: List<Hotel>) = hotelDao.insert(hotelList)

    fun getHotels() = hotelDao.allHotels()
}