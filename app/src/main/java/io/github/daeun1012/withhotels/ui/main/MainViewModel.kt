package io.github.daeun1012.withhotels.ui.main

import androidx.lifecycle.*
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

    private val filter = MutableLiveData<Int>(CREATD_AT_DESC)

    private val compositeDisposable = CompositeDisposable()
    val pagedListHotel = MutableLiveData<PagedList<LikeHotel>>()
//    val pagedListLike = LivePagedListBuilder(likeRepository.getAllLike(), 20).build()
    val pagedListLike: LiveData<PagedList<LikeHotel>> = filter.switchMap {
        when (it) {
//            NO_FILTER -> LivePagedListBuilder(likeRepository.getAllLike(), 20).build()
            CREATD_AT_DESC -> LivePagedListBuilder(likeRepository.getAllLikeCreatedAtDesc(), 20).build()
            CREATD_AT_ASC -> LivePagedListBuilder(likeRepository.getAllLikeCreatedAtAsc(), 20).build()
            RATE_DESC -> LivePagedListBuilder(likeRepository.getAllLikeRateDesc(), 20).build()
            RATE_ASC -> LivePagedListBuilder(likeRepository.getAllLikeRateAsc(), 20).build()
            else -> LivePagedListBuilder(likeRepository.getAllLikeRateDesc(), 20).build()
        }
    }

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

    fun isLiked(id: Long) = likeRepository.isLiked(id)

    fun setFilter(num: Int) {
        filter.value = num
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    companion object {
        private const val NO_FILTER = -1
        const val CREATD_AT_DESC = 1
        const val CREATD_AT_ASC = 2
        const val RATE_DESC = 3
        const val RATE_ASC = 4
    }
}