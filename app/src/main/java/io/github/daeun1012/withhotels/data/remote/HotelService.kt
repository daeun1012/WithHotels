package io.github.daeun1012.withhotels.data.remote

import io.github.daeun1012.withhotels.data.remote.res.ResCommon
import io.github.daeun1012.withhotels.data.remote.res.ResGetHotelList
import retrofit2.Call
import retrofit2.http.GET

interface HotelService {

    @GET("/App/json/1.json")
    fun getHotelList(): Call<ResCommon<ResGetHotelList>>
}