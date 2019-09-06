package io.github.daeun1012.withhotels.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.github.daeun1012.withhotels.data.local.Hotel

@Dao
interface HotelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(products: List<Hotel>)

    @Query("SELECT * FROM hotels")
    fun loadHotels(): LiveData<List<Hotel>>

}