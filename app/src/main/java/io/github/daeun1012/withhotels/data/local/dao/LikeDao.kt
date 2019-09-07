package io.github.daeun1012.withhotels.data.local.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import io.github.daeun1012.withhotels.data.local.Hotel
import io.github.daeun1012.withhotels.data.local.Like
import io.reactivex.Completable

@Dao
interface LikeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(like: Like): Completable

    @Query("SELECT * FROM hotels INNER JOIN likes ON likes.hotel_id = hotels.id")
    fun allLikes(): DataSource.Factory<Int, Hotel>

    @Query("SELECT EXISTS(SELECT 1 FROM likes WHERE hotel_id = :id LIMIT 1)")
    fun isLiked(id: Long): LiveData<Boolean>

    @Query("DELETE FROM likes WHERE hotel_id = :id")
    fun delete(id: Long): Int
}