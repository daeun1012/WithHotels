package io.github.daeun1012.withhotels.data.remote

import androidx.lifecycle.MutableLiveData
import io.github.daeun1012.withhotels.data.HotelResult
import io.github.daeun1012.withhotels.data.local.HotelLocalCache
import io.github.daeun1012.withhotels.data.remote.res.ResCommon
import io.github.daeun1012.withhotels.data.remote.res.ResGetHotelList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class HotelRepository(
    private val service: HotelService,
    private val cache: HotelLocalCache
) {

    // LiveData of network errors.
    private val networkErrors = MutableLiveData<String>()

    // avoid triggering multiple requests in the same time
    private var isRequestInProgress = false

    fun requestHotels() : HotelResult {
        requestAndSaveData()

        // Get data from the local cache
        val data = cache.loadHotels()

        return HotelResult(data, networkErrors)
    }

    private fun requestAndSaveData() {
        if (isRequestInProgress) return

        isRequestInProgress = true
        service.getHotelList().enqueue(
            object : Callback<ResCommon<ResGetHotelList>> {
                override fun onFailure(call: Call<ResCommon<ResGetHotelList>>, t: Throwable) {
                    Timber.d("fail to get data")
                    networkErrors.postValue(t.message ?: "unknown error")
                    isRequestInProgress = false
                }

                override fun onResponse(
                    call: Call<ResCommon<ResGetHotelList>>,
                    response: Response<ResCommon<ResGetHotelList>>
                ) {
                    Timber.d("got a response $response")
                    if (response.isSuccessful) {
                        val hotels = response.body()?.data?.hotelList ?: emptyList()
                        cache.insert(hotels) {
                            isRequestInProgress = false
                        }
                    } else {
                        networkErrors.postValue(response.errorBody()?.string() ?: "Unknown error")
                        isRequestInProgress = false
                    }

                }
            }
        )
    }

}