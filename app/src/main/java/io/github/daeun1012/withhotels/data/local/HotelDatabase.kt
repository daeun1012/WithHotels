package io.github.daeun1012.withhotels.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import io.github.daeun1012.withhotels.data.local.dao.HotelDao

@Database(
    entities = [Hotel::class],
    version = 1,
    exportSchema = false
)
abstract class HotelDatabase : RoomDatabase() {

    abstract fun hotelsDao(): HotelDao

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