package io.github.daeun1012.withhotels.utils

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import io.github.daeun1012.withhotels.data.local.HotelDatabase
import io.github.daeun1012.withhotels.data.repository.HotelRepository
import io.github.daeun1012.withhotels.data.remote.HotelService
import io.github.daeun1012.withhotels.data.repository.LocalRepository
import io.github.daeun1012.withhotels.ui.main.MainViewModelFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors

//const val BASE_URL = "https://withinnovation.co.kr"
const val BASE_URL = "https://www.gccompany.co.kr"

object InjectorUtils {

    fun provideMainViewModelFactory(context: Context): ViewModelProvider.Factory =
        MainViewModelFactory(
            provideHotelListRepository(context), provideLikeRepository(context)
        )

    private fun provideHotelListRepository(context: Context) = HotelRepository(provideLikeRepository(context), getService(), Executors.newFixedThreadPool(5))

    private fun provideLikeRepository(context: Context) = LocalRepository.getInstance(HotelDatabase.getInstance(context).hotelDao())

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
}