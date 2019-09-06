package io.github.daeun1012.withhotels.data.remote.res

import com.google.gson.annotations.SerializedName

data class ResCommon<T>(
    @SerializedName("msg") val message: String,
    val code: Int = 0,
    val data: T? = null
    )