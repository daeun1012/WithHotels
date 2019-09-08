package io.github.daeun1012.withhotels.data.local

import androidx.room.*
import java.util.*

@Entity(
    tableName = "likes",
    foreignKeys = [ForeignKey(entity = Hotel::class, parentColumns = ["id"], childColumns = ["hotel_id"])],
    indices = [Index("hotel_id", unique = true)]
)
data class Like(
    @ColumnInfo(name = "hotel_id") val id: Long,
    @ColumnInfo(name = "created_at") val createdAt: Calendar = Calendar.getInstance()
){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var likeId: Long = 0
}