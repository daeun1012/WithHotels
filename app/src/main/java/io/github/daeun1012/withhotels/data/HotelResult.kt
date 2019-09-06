package io.github.daeun1012.withhotels.data

import androidx.lifecycle.LiveData
import io.github.daeun1012.withhotels.data.local.Hotel

data class HotelResult(
    val data: LiveData<List<Hotel>>,
    val networkErrors: LiveData<String>
)
