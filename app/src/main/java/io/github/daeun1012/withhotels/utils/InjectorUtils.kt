package io.github.daeun1012.withhotels.utils

import android.content.Context
import io.github.daeun1012.withhotels.viewmodel.HotelListViewModelFactory

object InjectorUtils {

    fun provideHotelListViewModelFactory(context: Context): HotelListViewModelFactory {
//        val repository = getPlantRepository(context)
        return HotelListViewModelFactory(/*repository*/)
    }
}