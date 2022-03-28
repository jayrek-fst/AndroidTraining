package com.fs.jayrek.trainingtask.model.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TweetDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAllTweets(tweetList: MutableList<TweetModel>)

    @Query("SELECT * FROM tweet_tbl ORDER BY date DESC")
    fun getAllTweets(): MutableList<TweetModel>

    @Query("DELETE FROM tweet_tbl")
    fun deleteTweets()
}