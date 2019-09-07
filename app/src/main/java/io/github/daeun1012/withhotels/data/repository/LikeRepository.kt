package io.github.daeun1012.withhotels.data.repository

import io.github.daeun1012.withhotels.data.local.Like
import io.github.daeun1012.withhotels.data.local.dao.LikeDao
import io.reactivex.Completable

class LikeRepository private constructor(
    private val likeDao: LikeDao
) {

    fun addLike(like: Like): Completable {
        return likeDao.insert(like)
    }

    fun deleteLike(id: Long) {
        return likeDao.delete(id)
    }

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: LikeRepository? = null

        fun getInstance(likeDao: LikeDao) =
            instance ?: synchronized(this) {
                instance ?: LikeRepository(likeDao).also { instance = it }
            }
    }
}