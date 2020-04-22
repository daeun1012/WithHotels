package io.github.daeun1012.withhotels.ui.main

import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import io.github.daeun1012.withhotels.data.repository.HotelRepository
import io.github.daeun1012.withhotels.data.repository.LikeRepository
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import androidx.paging.PagedList
import io.github.daeun1012.withhotels.data.local.*

class MainViewModel(
    private val repository: HotelRepository,
    private val likeRepository: LikeRepository
) : ViewModel() {

    private val filter = MutableLiveData<SortType>(SortType.NONE)

    private val compositeDisposable = CompositeDisposable()
    val pagedListHotel = MutableLiveData<PagedList<LikeHotel>>()
//    val pagedListLike = LivePagedListBuilder(likeRepository.getAllLike(), 20).build()
    val pagedListLike: LiveData<PagedList<LikeHotel>> = filter.switchMap {
        when (it) {
//            NO_FILTER -> LivePagedListBuilder(likeRepository.getAllLike(), 20).build()
            SortType.CREATED_AT_DESC -> LivePagedListBuilder(likeRepository.getAllLikeCreatedAtDesc(), 20).build()
            SortType.CREATED_AT_ASC -> LivePagedListBuilder(likeRepository.getAllLikeCreatedAtAsc(), 20).build()
            SortType.RATE_DESC -> LivePagedListBuilder(likeRepository.getAllLikeRateDesc(), 20).build()
            SortType.RATE_ASC -> LivePagedListBuilder(likeRepository.getAllLikeRateAsc(), 20).build()
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

    fun setFilter(type: SortType) {
        filter.value = type
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}