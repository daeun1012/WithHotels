package io.github.daeun1012.withhotels.utils

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import io.github.daeun1012.withhotels.data.local.HotelDatabase
import io.github.daeun1012.withhotels.data.local.HotelLocalCache
import io.github.daeun1012.withhotels.data.remote.HotelRepository
import io.github.daeun1012.withhotels.data.remote.HotelService
import io.github.daeun1012.withhotels.viewmodel.ViewModelFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors

val BASE_URL = "https://withinnovation.co.kr"

object InjectorUtils {

    private fun provideHotelService(): HotelService {
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(HotelService::class.java)
    }

    private fun provideCache(context: Context): HotelLocalCache {
        val database = HotelDatabase.getInstance(context)
        return HotelLocalCache(database.hotelsDao(), Executors.newSingleThreadExecutor())
    }

    private fun provideGithubRepository(context: Context): HotelRepository {
        return HotelRepository(provideHotelService(), provideCache(context))
    }

    fun provideViewModelFactory(context: Context): ViewModelProvider.Factory {
        return ViewModelFactory(provideGithubRepository(context))
    }
}