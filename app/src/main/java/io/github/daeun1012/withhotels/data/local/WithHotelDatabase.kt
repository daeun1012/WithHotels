package io.github.daeun1012.withhotels.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.github.daeun1012.withhotels.data.local.dao.HotelDao
import io.github.daeun1012.withhotels.data.local.dao.LikeDao

@Database(
    entities = [Hotel::class, Like::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class HotelDatabase : RoomDatabase() {

    abstract fun hotelDao(): HotelDao

    abstract fun likeDao(): LikeDao

    companion object {

        @Volatile
        private var INSTANCE: HotelDatabase? = null

        fun getInstance(context: Context): HotelDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                HotelDatabase::class.java, "WithHotels.db")
                .build()
    }
}