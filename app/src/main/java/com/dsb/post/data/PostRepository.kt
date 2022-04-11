package com.dsb.post.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.dsb.post.data.database.PostDatabase
import com.dsb.post.data.database.PostRemoteMediator
import com.dsb.post.model.Comment
import com.dsb.post.model.Post
import com.dsb.post.model.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val api: PostApi,
    private val database: PostDatabase
) {
    companion object {
        private const val PAGE_SIZE = 20
    }

    @ExperimentalPagingApi
    fun getPosts(): Flow<PagingData<Post>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                prefetchDistance = PAGE_SIZE * 2,
                enablePlaceholders = false,
            ),
            remoteMediator = PostRemoteMediator(api, database)
        ) {
            database.postDao().getPost()
        }.flow
    }

    // todo: Implement fetch strategy to either get data eagerly or local first.
    suspend fun getCommentsOnPost(postId: Int): List<Comment> {
        val comments = database.commentDao().getCommentsOfPost(postId)
        return if (comments.isNullOrEmpty()) {
            api.getPostComments(postId).also { database.commentDao().insertAll(it) }
        } else {
            comments
        }
    }

    suspend fun fetchAndSaveUsersInLocalDatabase() {
        val users = api.getAllUsers()
        database.userDao().insertAll(users)
    }

    suspend fun getUserById(userId: Int): User {
        val user = getUserFromDatabase(userId)
        return user ?: api.getUserById(userId).also {
            database.userDao().insertSingle(it)
        }
    }

    private fun getUserFromDatabase(userId: Int): User? {
        return database.userDao().getUserById(userId)
    }

}