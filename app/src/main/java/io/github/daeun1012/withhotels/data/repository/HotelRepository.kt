package io.github.daeun1012.withhotels.data.repository

import androidx.lifecycle.switchMap
import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import androidx.paging.toLiveData
import io.github.daeun1012.withhotels.data.local.Hotel
import io.github.daeun1012.withhotels.data.local.Listing
import io.github.daeun1012.withhotels.data.remote.HotelDataSourceFactory
import io.github.daeun1012.withhotels.data.remote.HotelService
import io.reactivex.Observable
import java.util.concurrent.Executor

class HotelRepository(
//    private val remoteDataSource: HotelRemoteDataSource,
    private val localRepository: LocalRepository,
    hotelService: HotelService,
    retryExecutor: Executor
) {

    val config = PagedList.Config.Builder()
        .setInitialLoadSizeHint(20)
        .setPageSize(10)
        .setPrefetchDistance(5)
        .setEnablePlaceholders(true)
        .build()

    val factory = HotelDataSourceFactory(hotelService, retryExecutor)

    val builder = RxPagedListBuilder<Int, Hotel>(factory, config)

    init {
        factory.mapByPage { list ->
            list.forEach { hotel ->
                val temp = try {
                    localRepository.isLiked(hotel.id).isLiked.value
                } catch (e: NullPointerException) {
                    false
                }
                hotel.isLiked.postValue(temp)
            }

            list
        }
    }

    fun fetchOrGetHotels(): Observable<PagedList<Hotel>> = builder.buildObservable()

//    fun fetchOrGetHotels(): Listing<Hotel> {
//        val sourceFactory = HotelDataSourceFactory(hotelService, retryExecutor)
//
//        // We use toLiveData Kotlin ext. function here, you could also use LivePagedListBuilder
//        val livePagedList = sourceFactory.toLiveData(
//            // we use Config Kotlin ext. function here, could also use PagedList.Config.Builder
//            config = config,
//            // provide custom executor for network requests, otherwise it will default to
//            // Arch Components' IO pool which is also used for disk access
//            fetchExecutor = retryExecutor)
//
//        val refreshState = sourceFactory.sourceLiveData.switchMap {
//            it.initialLoad
//        }
//
//        return Listing(
//            pagedList = livePagedList,
//            networkState = sourceFactory.sourceLiveData.switchMap {
//                it.networkState
//            },
//            retry = {
//                sourceFactory.sourceLiveData.value?.retryAllFailed()
//            },
//            refresh = {
//                sourceFactory.sourceLiveData.value?.invalidate()
//            },
//            refreshState = refreshState
//        )
//    }


//    fun fetchOrGetHotels() : Observable<PagedList<LikeHotel>> = RxPagedListBuilder(localDataSource.allHotelsWithLike(), 20)
//       .setBoundaryCallback(PageListHotelBoundaryCallback(remoteDataSource, localDataSource))
//       .buildObservable()
//
//    fun fetchHotels() = localDataSource.allHotelsWithLike()
}