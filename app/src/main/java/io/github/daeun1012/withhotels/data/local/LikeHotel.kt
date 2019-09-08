package io.github.daeun1012.withhotels.data.local

import androidx.room.ColumnInfo
import androidx.room.Embedded
import java.util.*

data class LikeHotel(
    @Embedded val hotel: Hotel,

    @ColumnInfo(name = "created_at")
    val createdAt: Calendar?,

    @ColumnInfo(name = "is_liked")
    val isLiked: Boolean
)
