package io.github.daeun1012.withhotels.data.local

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Description(
    @field:SerializedName("imagePath") val imagePath: String,
    @field:SerializedName("subject") val subject: String,
    @field:SerializedName("price") val price: Long = 0
    ):Serializable