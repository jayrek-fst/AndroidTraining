package com.fs.jayrek.trainingtask.model.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TweetDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTweets(tweetModel: TweetModel)

    @Query("SELECT * FROM tweet_tbl ORDER BY date DESC")
    fun getAllTweets(): Flow<List<TweetModel>>

    @Query("DELETE FROM tweet_tbl")
    suspend fun deleteTweets()
}