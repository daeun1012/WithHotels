package io.github.daeun1012.withhotels.ui.main

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
import timber.log.Timber

class MainViewModel(
    private val repository: HotelRepository,
    private val likeRepository: LikeRepository
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val _pagedListHotel = MutableLiveData<PagedList<Hotel>>()
    val pagedListHotel = Transformations.map(_pagedListHotel) {
        it.map { hotel ->
            hotel.isLiked = likeRepository?.isLiked(hotel.id)
        }
        it
    }

    private val _pagedListLike = likeRepository.getAllLike().toLiveData(Config(pageSize = 30, enablePlaceholders = true, maxSize = 200))
    val pagedListLike = Transformations.map(_pagedListLike) {
        it.map { hotel ->
            hotel.isLiked = MutableLiveData(true)
        }

        it
    }

    fun getHotels() {
        compositeDisposable.add(
            repository.fetchOrGetHotels()
                .doOnNext { list ->
                    isLiked(list)
                    list
                }
                .subscribe({
                    _pagedListHotel.value = it
                }, { it.printStackTrace() })
        )
    }

    private fun isLiked(hotelList: List<Hotel>) {
        compositeDisposable.add(
            Single.fromCallable {
                hotelList.map {
                    it.isLiked = MutableLiveData(likeRepository?.isLikedSync(it.id))
                    Timber.d("${it.id} : ${likeRepository?.isLikedSync(it.id)}")
                }

            }
                .subscribeOn(Schedulers.io())
                .subscribe()
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