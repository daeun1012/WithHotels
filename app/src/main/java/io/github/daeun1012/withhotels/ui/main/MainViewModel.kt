package io.github.daeun1012.withhotels.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import io.github.daeun1012.withhotels.data.local.Hotel
import io.github.daeun1012.withhotels.data.local.Like
import io.github.daeun1012.withhotels.data.repository.HotelRepository
import io.github.daeun1012.withhotels.data.repository.LikeRepository
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import androidx.paging.PagedList
import io.github.daeun1012.withhotels.data.local.LikeHotel

class MainViewModel(
    private val repository: HotelRepository,
    private val likeRepository: LikeRepository
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    val pagedListHotel = MutableLiveData<PagedList<LikeHotel>>()
//    val pagedListHotel = LivePagedListBuilder(repository.fetchHotels(), 20).build()
    val pagedListLike = LivePagedListBuilder(likeRepository.getAllLike(), 20).build()

    fun getHotels() {
        compositeDisposable.add(
            repository.fetchOrGetHotels()
                .subscribe({
                    pagedListHotel.value = it
                }, { it.printStackTrace() })
        )
    }

    fun addLikes(id: Long) {
        compositeDisposable.add(
            likeRepository.addLike(Like(id))
                .subscribeOn(Schedulers.io())
                .subscribe()
        )
    }

    fun deleteLikes(id: Long) {
        compositeDisposable.add(
            Single.fromCallable { likeRepository.deleteLike(id) }
                .subscribeOn(Schedulers.io())
                .subscribe()
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}