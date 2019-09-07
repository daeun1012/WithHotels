package io.github.daeun1012.withhotels.data.remote

import io.github.daeun1012.withhotels.data.remote.res.ResCommon
import io.github.daeun1012.withhotels.data.remote.res.ResGetHotelList
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface HotelService {

    @GET("/App/json/{page}.json")
    fun getHotelList(@Path("page") page: Int): Single<ResCommon<ResGetHotelList>>
}