package io.github.daeun1012.withhotels.data.local.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import io.github.daeun1012.withhotels.data.local.Like
import io.github.daeun1012.withhotels.data.local.LikeHotel
import io.reactivex.Completable

@Dao
interface LikeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(like: Like): Completable

    @Query("SELECT hotels.*, likes.created_at, 1 as is_liked FROM hotels INNER JOIN likes ON likes.hotel_id = hotels.id")
    fun allLikes(): DataSource.Factory<Int, LikeHotel>

    @Query("SELECT hotels.*, likes.created_at, 1 as is_liked FROM hotels INNER JOIN likes ON likes.hotel_id = hotels.id ORDER BY created_at DESC")
    fun getAllLikeCreatedAtDesc(): DataSource.Factory<Int, LikeHotel>

    @Query("SELECT hotels.*, likes.created_at, 1 as is_liked FROM hotels INNER JOIN likes ON likes.hotel_id = hotels.id ORDER BY created_at ASC")
    fun getAllLikeCreatedAtAsc(): DataSource.Factory<Int, LikeHotel>

    @Query("SELECT hotels.*, likes.created_at, 1 as is_liked FROM hotels INNER JOIN likes ON likes.hotel_id = hotels.id ORDER BY rate DESC")
    fun getAllLikeRateDesc(): DataSource.Factory<Int, LikeHotel>

    @Query("SELECT hotels.*, likes.created_at, 1 as is_liked FROM hotels INNER JOIN likes ON likes.hotel_id = hotels.id ORDER BY rate ASC")
    fun getAllLikeRateAsc(): DataSource.Factory<Int, LikeHotel>

    @Query("SELECT EXISTS(SELECT 1 FROM likes WHERE hotel_id = :id LIMIT 1)")
    fun isLiked(id: Long): LiveData<Boolean>

    @Query("SELECT EXISTS(SELECT 1 FROM likes WHERE hotel_id = :id LIMIT 1)")
    fun isLikedSync(id: Long): Boolean

    @Query("DELETE FROM likes WHERE hotel_id = :id")
    fun delete(id: Long): Int
}