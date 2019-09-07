package io.github.daeun1012.withhotels.data.local.dao

import androidx.paging.DataSource
import androidx.room.*
import io.github.daeun1012.withhotels.data.local.Like
import io.reactivex.Completable

@Dao
interface LikeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(like: Like): Completable

    @Query("SELECT * FROM likes")
    fun allHotels(): DataSource.Factory<Int, Like>

    @Query("DELETE FROM likes WHERE hotel_id = :id")
    fun delete(id: Long)
}