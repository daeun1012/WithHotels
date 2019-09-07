package io.github.daeun1012.withhotels.ui.main.like

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import io.github.daeun1012.withhotels.data.local.Like
import io.github.daeun1012.withhotels.data.local.dao.LikeDao

class LikeViewModel(private val likeDao: LikeDao): ViewModel() {

    val pagedListHotel: LiveData<PagedList<Like>> = LivePagedListBuilder(likeDao.allHotels(),  /* page size */ 20).build()

//    fun delete(like: Like) = ioThread { likeDao.delete(like) }
}