package com.example.posts.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.posts.data.model.PostEntity

@Database(entities = [PostEntity::class], version = 1, exportSchema = false)
abstract class PostDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao

}