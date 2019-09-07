package io.github.daeun1012.withhotels.data.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Entity(tableName = "hotels")
data class Hotel(
    @PrimaryKey @ColumnInfo(name = "id") val id: Long,
    @field:SerializedName("name") val name: String,
    @field:SerializedName("thumbnail") val thumbnail: String,
    @field:SerializedName("description") @Embedded val description: Description? = null,
    @field:SerializedName("rate") val rate: Float = 0f
): Serializable {
    @Ignore var isLiked: LiveData<Boolean>? = null
}