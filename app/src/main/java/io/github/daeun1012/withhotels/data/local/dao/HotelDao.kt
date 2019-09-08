package io.github.daeun1012.withhotels.data.local.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.github.daeun1012.withhotels.data.local.Hotel

@Dao
interface HotelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(products: List<Hotel>)

//    @Query("SELECT * FROM hotels")
    @Query("SELECT hotels.*, (SELECT 1 FROM likes WHERE hotel_id = hotels.id LIMIT 1) as is_liked_hotel FROM hotels")
    fun allHotels(): DataSource.Factory<Int, Hotel>

}