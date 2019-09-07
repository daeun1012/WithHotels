package io.github.daeun1012.withhotels.data.local

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "likes")
data class Like(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @field:SerializedName("name") val name: String,
    @field:SerializedName("thumbnail") val thumbnail: String,
    @field:SerializedName("description") @Embedded val description: Description? = null,
    @field:SerializedName("rate") val rate: Float = 0f
)