package com.example.posts.di

import android.content.Context
import androidx.room.Room
import com.example.posts.data.db.PostDao
import com.example.posts.data.db.PostDatabase
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
    fun provideDatabase(@ApplicationContext appContext: Context): PostDatabase {
        return Room.databaseBuilder(
            appContext,
            PostDatabase::class.java,
            "post_db"
        ).build()
    }

    @Provides
    fun providePostDao(database: PostDatabase): PostDao {
        return database.postDao()
    }
}