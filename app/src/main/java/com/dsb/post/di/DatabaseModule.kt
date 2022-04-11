package com.dsb.post.di

import android.content.Context
import androidx.room.Room
import com.dsb.post.data.database.PostDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext appContext: Context
    ): PostDatabase {
        return Room.databaseBuilder(appContext, PostDatabase::class.java, "posts.db").build()
    }
}