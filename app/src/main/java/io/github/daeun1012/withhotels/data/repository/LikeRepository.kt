package io.github.daeun1012.withhotels.data.repository

import io.github.daeun1012.withhotels.data.local.Like
import io.github.daeun1012.withhotels.data.local.dao.LikeDao

class LikeRepository private constructor(
    private val likeDao: LikeDao
) {

    fun addLike(like: Like) = likeDao.insert(like)

    fun deleteLike(id: Long) = likeDao.delete(id)

    fun getAllLike() = likeDao.allLikes()

    fun isLiked(hotelId: Long) = likeDao.isLiked(hotelId)

    fun isLikedSync(hotelId: Long) = likeDao.isLikedSync(hotelId)

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: LikeRepository? = null

        fun getInstance(likeDao: LikeDao) =
            instance ?: synchronized(this) {
                instance ?: LikeRepository(likeDao).also { instance = it }
            }
    }
}