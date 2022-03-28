package com.fs.jayrek.trainingtask.model.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TweetModel::class], version = 1, exportSchema = false)
abstract class TwitterRoomDatabase : RoomDatabase() {

    abstract fun tweetDao(): TweetDao

    companion object {
        @Volatile
        private var INSTANCE: TwitterRoomDatabase? = null

        fun getDatabase(context: Context): TwitterRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, TwitterRoomDatabase::class.java,
                    "tweet_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}