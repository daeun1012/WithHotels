package io.github.daeun1012.withhotels.utils

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import io.github.daeun1012.withhotels.data.local.HotelLocalDataSource
import io.github.daeun1012.withhotels.data.local.HotelDatabase
import io.github.daeun1012.withhotels.data.remote.HotelRemoteDataSource
import io.github.daeun1012.withhotels.data.repository.HotelRepository
import io.github.daeun1012.withhotels.data.remote.HotelService
import io.github.daeun1012.withhotels.ui.main.hotels.HotelListViewModelFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val BASE_URL = "https://withinnovation.co.kr"

object InjectorUtils {

    fun provideHotelViewModelFactory(context: Context): ViewModelProvider.Factory = HotelListViewModelFactory(provideHotelListRepository(context))

    private fun provideHotelListRepository(context: Context) = HotelRepository(HotelRemoteDataSource(getService()), provideHotelsDatabase(context))

    private fun getService(): HotelService = createRetrofit().create(HotelService::class.java)

    private fun createRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(provideOkHttpClient())
        .build()

    private fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

    private fun provideHotelsDatabase(context: Context): HotelLocalDataSource =
        HotelLocalDataSource(
            HotelDatabase.getInstance(
                context
            ).hotelsDao()
        )
}