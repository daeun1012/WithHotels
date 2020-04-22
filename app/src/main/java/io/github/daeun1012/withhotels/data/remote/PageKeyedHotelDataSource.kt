package io.github.daeun1012.withhotels.data.remote

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import androidx.paging.PositionalDataSource
import io.github.daeun1012.withhotels.data.local.Hotel
import timber.log.Timber
import java.util.concurrent.Executor

class PageKeyedHotelDataSource(
    private val hotelApi: HotelService,
    private val retryExecutor: Executor
): PageKeyedDataSource<Int, Hotel>() {

    /**
     * There is no sync on the state because paging will always call loadInitial first then wait
     * for it to return some success value before calling loadAfter.
     */
    val networkState = MutableLiveData<NetworkState>()

    val initialLoad = MutableLiveData<NetworkState>()

    private var currentPage = 0

    // keep a function reference for the retry event
    private var retry: (() -> Any)? = null

    fun retryAllFailed() {
        val prevRetry = retry
        retry = null
        prevRetry?.let {
            retryExecutor.execute {
                it.invoke()
            }
        }
    }

//    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Hotel>) {
//        networkState.postValue(NetworkState.Loading())
//
//        hotelApi.getHotelList(currentPage + 1)
//            .subscribe({ result ->
//                retry = null
//                result.data?.let {
//                    currentPage += 1
//                    networkState.postValue(NetworkState.Loaded())
//                    initialLoad.postValue(NetworkState.Loaded())
//                    callback.onResult(it.hotelList)
//                }
//            }, { t ->
//                retry = {
//                    loadRange(params, callback)
//                }
//
//                val error = NetworkState.Error(msg = t.message ?: "unknown error")
//                networkState.postValue(error)
//            })
//    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Hotel>) {
//        val firstLoadPosition = computeInitialLoadPosition(params, Int.MAX_VALUE)
//        val firstLoadSize = computeInitialLoadSize(params, firstLoadPosition, Int.MAX_VALUE)
//
//        Timber.d("$firstLoadPosition + ${firstLoadPosition + firstLoadSize}")

        networkState.postValue(NetworkState.Loading())
        initialLoad.postValue(NetworkState.Loading())

        hotelApi.getHotelList(currentPage + 1)
            .subscribe({ result ->
                retry = null
                Timber.d("loadInitial response : $result")
                result.data?.let {
                    currentPage += 1
                    networkState.postValue(NetworkState.Loaded())
                    initialLoad.postValue(NetworkState.Loaded())
                    callback.onResult(it.hotelList, 0, currentPage + 1)
                }
            }, { t ->
                retry = {
                    loadInitial(params, callback)
                }

                val error = NetworkState.Error(msg = t.message ?: "unknown error")
                networkState.postValue(error)
                initialLoad.postValue(error)
            })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Hotel>) {
        networkState.postValue(NetworkState.Loading())

        hotelApi.getHotelList(currentPage + 1)
            .subscribe({ result ->
                retry = null
                Timber.d("loadAfter response : $result")
                result.data?.let {
                    currentPage += 1
                    networkState.postValue(NetworkState.Loaded())
                    initialLoad.postValue(NetworkState.Loaded())
                    callback.onResult(it.hotelList, currentPage + 1)
                }
            }, { t ->
                retry = {
                    loadAfter(params, callback)
                }

                val error = NetworkState.Error(msg = t.message ?: "unknown error")
                networkState.postValue(error)
                initialLoad.postValue(error)
            })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Hotel>) {
        // do nothing
    }

}