package io.github.daeun1012.withhotels.ui.main

import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import io.github.daeun1012.withhotels.data.repository.HotelRepository
import io.github.daeun1012.withhotels.data.repository.LocalRepository
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import androidx.paging.PagedList
import io.github.daeun1012.withhotels.data.local.*
import timber.log.Timber
import java.util.*

class MainViewModel(
    private val repository: HotelRepository,
    private val localRepository: LocalRepository
) : ViewModel() {

    private val filter = MutableLiveData<SortType>(SortType.NONE)

    private val compositeDisposable = CompositeDisposable()
    val pagedListHotel = MutableLiveData<PagedList<Hotel>>()

    val pagedListLike: LiveData<PagedList<Hotel>> = filter.switchMap {
        when (it) {
//            NO_FILTER -> LivePagedListBuilder(likeRepository.getAllLike(), 20).build()
            SortType.CREATED_AT_DESC -> LivePagedListBuilder(localRepository.getAllLikeCreatedAtDesc(), 20).build()
            SortType.CREATED_AT_ASC -> LivePagedListBuilder(localRepository.getAllLikeCreatedAtAsc(), 20).build()
            SortType.RATE_DESC -> LivePagedListBuilder(localRepository.getAllLikeRateDesc(), 20).build()
            SortType.RATE_ASC -> LivePagedListBuilder(localRepository.getAllLikeRateAsc(), 20).build()
            else -> LivePagedListBuilder(localRepository.getAllLikeRateDesc(), 20).build()
        }
    }

    val updatePosition = MutableLiveData<Hotel>()

    fun getHotels() {
        compositeDisposable.add(
            repository.fetchOrGetHotels()
                .subscribeOn(Schedulers.io())
                .subscribe({
                    pagedListHotel.value = it
                }, { it.printStackTrace() })
        )
    }

    fun addLikes(hotel: Hotel) {
        hotel.createdAt = Calendar.getInstance()

        compositeDisposable.add(
            localRepository.addLike(hotel)
                .subscribeOn(Schedulers.io())
                .subscribe()
        )

        pagedListHotel.value?.find { it.id == hotel.id }?.isLiked = true
        updatePosition.postValue(hotel)
        Timber.d("test")
    }

    fun deleteLikes(hotel: Hotel) {
        compositeDisposable.add(
            Single.fromCallable { localRepository.deleteLike(hotel) }
                .subscribeOn(Schedulers.io())
                .subscribe()
        )

        pagedListHotel.value?.find { it.id == hotel.id }?.isLiked = false
        updatePosition.postValue(hotel)
        Timber.d("test")
    }

    fun setFilter(type: SortType) {
        filter.value = type
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}