package io.github.daeun1012.withhotels.data.repository

import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import io.github.daeun1012.withhotels.data.local.HotelLocalDataSource
import io.github.daeun1012.withhotels.data.local.Hotel
import io.github.daeun1012.withhotels.data.local.LikeHotel
import io.github.daeun1012.withhotels.data.remote.HotelRemoteDataSource
import io.reactivex.Observable

class HotelRepository(
    private val remoteDataSource: HotelRemoteDataSource,
    private val localDataSource: HotelLocalDataSource
) {

   fun fetchOrGetHotels() : Observable<PagedList<LikeHotel>> = RxPagedListBuilder(localDataSource.allHotelsWithLike(), 20)
       .setBoundaryCallback(PageListHotelBoundaryCallback(remoteDataSource, localDataSource))
       .buildObservable()

    fun fetchHotels() = localDataSource.allHotelsWithLike()
}