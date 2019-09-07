package io.github.daeun1012.withhotels.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.Config
import io.github.daeun1012.withhotels.data.local.Hotel
import io.github.daeun1012.withhotels.data.local.Like
import io.github.daeun1012.withhotels.data.repository.HotelRepository
import io.github.daeun1012.withhotels.data.repository.LikeRepository
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import androidx.paging.PagedList
import androidx.paging.toLiveData


class MainViewModel(
    private val repository: HotelRepository,
    private val likeRepository: LikeRepository
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    var pagedListHotel = MutableLiveData<PagedList<Hotel>>()

//    private lateinit var _temp: LiveData<PagedList<Hotel>>
    var pagedListLike = likeRepository.getAllLike().toLiveData(Config(pageSize = 30, enablePlaceholders = true, maxSize = 200))

    fun getHotels() {
        compositeDisposable.add(
            repository.fetchOrGetHotels()
//                .map { list ->
//                    list.map {
//                        it.isLiked = likeRepository.isLiked(it.id)
//                    }
//
//                    list
//                }
                .subscribe({
                    pagedListHotel.value = it
                }, { it.printStackTrace() })
        )
    }

//    fun getLikes() {
//        _temp =
//    }

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