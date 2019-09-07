package io.github.daeun1012.withhotels.data.repository

import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import io.github.daeun1012.withhotels.data.local.HotelLocalDataSource
import io.github.daeun1012.withhotels.data.local.Hotel
import io.github.daeun1012.withhotels.data.remote.HotelRemoteDataSource
import io.reactivex.Observable

const val PAGE_SIZE = 20

class HotelRepository(
    private val remoteDataSource: HotelRemoteDataSource,
    private val localDataSource: HotelLocalDataSource
) {

   fun fetchOrGetHotels() : Observable<PagedList<Hotel>> = RxPagedListBuilder(localDataSource.getHotels(), PAGE_SIZE)
       .setBoundaryCallback(PageListHotelBoundaryCallback(remoteDataSource, localDataSource))
       .buildObservable()
}