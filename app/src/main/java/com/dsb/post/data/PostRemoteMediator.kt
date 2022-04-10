package com.dsb.post.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.dsb.post.model.Post
import com.dsb.post.data.models.PostDatabase
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class PostRemoteMediator(
    private val api: PostApi,
    private val database: PostDatabase
): RemoteMediator<Int, Post>() {
    override suspend fun initialize(): InitializeAction {
        return InitializeAction.SKIP_INITIAL_REFRESH
    }

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Post>): MediatorResult {
        // As the api does not have paging we load everything once and save it in database.
        return try {
            val posts = api.getPosts()
            database.userDao().insertAll(posts = posts)
            MediatorResult.Success(endOfPaginationReached = true)
        } catch (exception: Exception) {
            exception.printStackTrace()
            MediatorResult.Error(exception)
        }
    }
}