package io.github.daeun1012.withhotels.data.remote

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import io.github.daeun1012.withhotels.data.local.Hotel
import java.util.concurrent.Executor

class HotelDataSourceFactory(
    private val hotelApi: HotelService,
    private val retryExecutor: Executor
): DataSource.Factory<Int, Hotel>() {

    val sourceLiveData = MutableLiveData<PageKeyedHotelDataSource>()

    override fun create(): DataSource<Int, Hotel> {
        val source = PageKeyedHotelDataSource(hotelApi, retryExecutor)
        sourceLiveData.postValue(source)
        return source
    }
}