package io.github.daeun1012.withhotels.data.local

import androidx.room.*
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

//@Entity(tableName = "hotels")
//data class Hotel(
//    @PrimaryKey @ColumnInfo(name = "id") val id: Long,
//    @field:SerializedName("name") val name: String,
//    @field:SerializedName("thumbnail") val thumbnail: String,
//    @field:SerializedName("description") @Embedded val description: Description? = null,
//    @field:SerializedName("rate") val rate: Float = 0f,
//    @ColumnInfo(name = "created_at") var createdAt: Calendar? = null
//): Serializable, BaseObservable() {
//
//    @get:Bindable
//    var isLiked = false
//        set(value) {
//            field = value
//            notifyPropertyChanged(BR.isLiked)
//        }
//}

@Entity(tableName = "hotels")
data class Hotel(
    @PrimaryKey @ColumnInfo(name = "id") val id: Long,
    @field:SerializedName("name") val name: String,
    @field:SerializedName("thumbnail") val thumbnail: String,
    @field:SerializedName("description") @Embedded val description: Description? = null,
    @field:SerializedName("rate") val rate: Float = 0f,
    @ColumnInfo(name = "created_at") var createdAt: Calendar? = null,
    @ColumnInfo(name = "is_liked") var isLiked:Boolean = false
): Serializable {

//    constructor(id: Long, name: String, thumbnail: String, description: Description?, rate: Float, createdAt: Calendar?) : this(id, name, thumbnail, description, rate, createdAt, false)
}