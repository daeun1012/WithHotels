package io.github.daeun1012.withhotels.data.remote.res

import com.google.gson.annotations.SerializedName
import io.github.daeun1012.withhotels.data.local.Hotel

data class ResGetHotelList(
    @SerializedName("totalCount") val total: Int = 0,
    @SerializedName("product") val hotelList: List<Hotel> = emptyList()
    )