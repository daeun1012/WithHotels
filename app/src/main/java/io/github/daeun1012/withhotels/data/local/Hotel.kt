package io.github.daeun1012.withhotels.data.local

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

@Entity(tableName = "hotels")
data class Hotel(
    @PrimaryKey @ColumnInfo(name = "id") val id: Long,
    @field:SerializedName("name") val name: String,
    @field:SerializedName("thumbnail") val thumbnail: String,
    @field:SerializedName("description") @Embedded val description: Description? = null,
    @field:SerializedName("rate") val rate: Float = 0f,
    @ColumnInfo(name = "created_at") var createdAt: Calendar? = null
): Serializable {

    @Ignore var isLiked = MutableLiveData<Boolean>(false)
}