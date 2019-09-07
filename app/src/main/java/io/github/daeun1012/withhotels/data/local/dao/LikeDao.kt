package io.github.daeun1012.withhotels.data.local.dao

import androidx.paging.DataSource
import androidx.room.*
import io.github.daeun1012.withhotels.data.local.Like

@Dao
interface LikeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(like: Like)

    @Query("SELECT * FROM likes")
    fun allHotels(): DataSource.Factory<Int, Like>

    @Delete
    fun delete(like: Like)
}