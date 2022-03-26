package com.fs.jayrek.trainingtask.model.model

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TweetModel::class], version = 1)
abstract class TwitterRoomDatabase : RoomDatabase(){
}