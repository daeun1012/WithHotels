package io.github.daeun1012.withhotels.data.repository

import androidx.paging.PagedList
import io.github.daeun1012.withhotels.data.local.HotelLocalDataSource
import io.github.daeun1012.withhotels.data.local.Hotel
import io.github.daeun1012.withhotels.data.remote.HotelRemoteDataSource
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class PageListHotelBoundaryCallback(private val remoteDataSource: HotelRemoteDataSource,
                                    private val localDataSource: HotelLocalDataSource
) : PagedList.BoundaryCallback<Hotel>() {

    private var isRequestRunning = false
    private var requestedPage = 1

    override fun onZeroItemsLoaded() {
        Timber.i("onZeroItemsLoaded")
        fetchAndStoreHotels()
    }

    override fun onItemAtEndLoaded(itemAtEnd: Hotel) {
        Timber.i("onItemAtEndLoaded")
        fetchAndStoreHotels()
    }

    private fun fetchAndStoreHotels() {
        if (isRequestRunning) return

        isRequestRunning = true
        remoteDataSource.fetchHotels(requestedPage)
            .map { res -> res.data?.hotelList!!.map { it }}
            .doOnSuccess { listHotel ->
                if(listHotel.isNotEmpty()) {
                    localDataSource.storeHotels(listHotel)
                    Timber.i("Inserted: ${listHotel.size}")
                } else {
                    Timber.i("No Inserted")
                }
                requestedPage++
            }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
//            .ignoreElement() // TODO 확인
            .doFinally { isRequestRunning = false }
            .subscribe({ Timber.i("Movies Completed") }, { it.printStackTrace() })
    }
}