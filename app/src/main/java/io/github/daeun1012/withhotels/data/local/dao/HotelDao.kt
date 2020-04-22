package io.github.daeun1012.withhotels.data.local.dao

import androidx.paging.DataSource
import androidx.room.*
import io.github.daeun1012.withhotels.data.local.Hotel
import io.reactivex.Completable

@Dao
interface HotelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(product: Hotel): Completable

    @Delete
    fun delete(product: Hotel): Int

    @Query("SELECT * FROM hotels WHERE id = :id")
    fun get(id: Long): Hotel

    @Query("SELECT * FROM hotels")
    fun allHotels(): DataSource.Factory<Int, Hotel>

    @Query("SELECT * FROM hotels ORDER BY created_at DESC")
    fun getAllCreatedAtDesc(): DataSource.Factory<Int, Hotel>

    @Query("SELECT * FROM hotels ORDER BY created_at ASC")
    fun getAllCreatedAtAsc(): DataSource.Factory<Int, Hotel>

    @Query("SELECT * FROM hotels ORDER BY rate DESC")
    fun getAllRateDesc(): DataSource.Factory<Int, Hotel>

    @Query("SELECT * FROM hotels ORDER BY rate ASC")
    fun getAllRateAsc(): DataSource.Factory<Int, Hotel>
}