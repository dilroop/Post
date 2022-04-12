package com.dsb.post.data.database

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.dsb.post.data.PostApi
import com.dsb.post.model.PostWithUser

@OptIn(ExperimentalPagingApi::class)
class PostRemoteMediator(
    private val api: PostApi,
    private val database: PostDatabase
) : RemoteMediator<Int, PostWithUser>() {
    companion object {
        private const val MAX_REMOTE_POST = 100
    }

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.SKIP_INITIAL_REFRESH
    }

    override suspend fun load(loadType: LoadType, state: PagingState<Int, PostWithUser>): MediatorResult {
        // As the api does not have paging we load everything once and save it in database.
        if (database.postDao().countPost() >= MAX_REMOTE_POST) {
            return MediatorResult.Success(endOfPaginationReached = true)
        }

        return try {
            val posts = api.getPosts()
            val users = api.getAllUsers()
            database.withTransaction {
                database.postDao().insertAll(posts = posts)
                database.userDao().insertAll(users = users)
            }
            MediatorResult.Success(endOfPaginationReached = true)
        } catch (exception: Exception) {
            exception.printStackTrace()
            MediatorResult.Error(exception)
        }
    }
}