package io.github.daeun1012.withhotels.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import io.github.daeun1012.withhotels.data.local.Hotel
import io.github.daeun1012.withhotels.data.local.Like
import io.github.daeun1012.withhotels.data.repository.HotelRepository
import io.github.daeun1012.withhotels.data.repository.LikeRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: HotelRepository,
    private val likeRepository: LikeRepository
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    var pagedListHotel = MutableLiveData<PagedList<Hotel>>()

    fun getHotels() {
        compositeDisposable.add(
            repository.fetchOrGetHotels()
                .subscribe({ pagedListHotel.value = it }, { it.printStackTrace() })
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
//        compositeDisposable.add(
            likeRepository.deleteLike(id)
//                .subscribe()
//        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}