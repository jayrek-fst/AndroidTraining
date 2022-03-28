package com.fs.jayrek.trainingtask.model.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tweet_tbl")
data class TweetModel(
    @PrimaryKey val id: Long,
    val text: String,
    val screenName: String,
    val userName: String,
    val date: String,
    val source: String,
    val profileImage: String
)
