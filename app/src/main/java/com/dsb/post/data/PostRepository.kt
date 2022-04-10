package com.dsb.post.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.dsb.post.data.models.PostDatabase
import com.dsb.post.model.Post
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val api: PostApi,
    private val database: PostDatabase
) {
    companion object {
        const val PAGE_SIZE = 20
    }

    @ExperimentalPagingApi
    suspend fun getPosts(): Flow<PagingData<Post>> {
        val pagingSourceFactory = { database.userDao().getPost() }

        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                prefetchDistance = PAGE_SIZE * 3,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = pagingSourceFactory,
            remoteMediator = PostRemoteMediator(api, database)
        ).flow
    }
}