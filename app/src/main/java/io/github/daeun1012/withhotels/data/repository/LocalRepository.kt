package io.github.daeun1012.withhotels.data.repository

import io.github.daeun1012.withhotels.data.local.Hotel
import io.github.daeun1012.withhotels.data.local.dao.HotelDao

class LocalRepository private constructor(
    private val hotelDao: HotelDao
) {

    fun addLike(hotel: Hotel) = hotelDao.insert(hotel)

    fun deleteLike(hotel: Hotel) = hotelDao.delete(hotel)

    fun getAllLike() = hotelDao.allHotels()

    fun getAllLikeCreatedAtDesc() = hotelDao.getAllCreatedAtDesc()

    fun getAllLikeCreatedAtAsc() = hotelDao.getAllCreatedAtAsc()

    fun getAllLikeRateDesc() = hotelDao.getAllRateDesc()

    fun getAllLikeRateAsc() = hotelDao.getAllRateAsc()

    fun isLiked(hotelId: Long) = hotelDao.get(hotelId)

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: LocalRepository? = null

        fun getInstance(hotelDao: HotelDao) =
            instance ?: synchronized(this) {
                instance ?: LocalRepository(hotelDao).also { instance = it }
            }
    }
}