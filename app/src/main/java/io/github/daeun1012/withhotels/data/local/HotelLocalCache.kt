package io.github.daeun1012.withhotels.data.local

import androidx.lifecycle.LiveData
import io.github.daeun1012.withhotels.data.local.dao.HotelDao
import timber.log.Timber
import java.util.concurrent.Executor

class HotelLocalCache(
    private val hotelDao: HotelDao,
    private val ioExecutor: Executor
) {

    fun insert(hotels: List<Hotel>, insertFinished: () -> Unit) {
        ioExecutor.execute {
            Timber.d("inserting ${hotels.size} hotels")
            hotelDao.insert(hotels)
            insertFinished()
        }
    }

    fun loadHotels(): LiveData<List<Hotel>> {
        return hotelDao.loadHotels()
    }
}