package com.fs.jayrek.trainingtask.model.repository

import android.util.Log
import com.fs.jayrek.trainingtask.helper.Resource
import com.fs.jayrek.trainingtask.helper.StringConstants
import com.fs.jayrek.trainingtask.helper.safeApiCall
import com.fs.jayrek.trainingtask.model.model.TweetDao
import com.fs.jayrek.trainingtask.model.model.TweetModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import twitter4j.TwitterFactory
import javax.inject.Inject

class TwitterRepository @Inject constructor(
    private val _twitterFactory: TwitterFactory,
    private val _tweetDao: TweetDao
) {

    fun checkResourceData(): Resource<MutableList<TweetModel>> {
        val tweetCount = getTweetCount()
        return if (tweetCount > 0) fetchTweetFromDao() else getTweets()
    }

    suspend fun refreshTweets() {
        withContext(Dispatchers.IO) {
            Log.wtf("INITIAL", _tweetDao.getAllTweets().size.toString())
            deleteTweetFromDao()
            Log.wtf("REFRESH", _tweetDao.getAllTweets().size.toString())
            checkResourceData()
            Log.wtf("CURRENT", _tweetDao.getAllTweets().size.toString())
        }
    }

    private fun getTweetCount(): Int =
        _tweetDao.getAllTweets().size

    private fun getTweets(): Resource<MutableList<TweetModel>> {
        return safeApiCall {
            val tweets = mutableListOf<TweetModel>()
            val timelineTweets =
                _twitterFactory.instance.getUserTimeline(StringConstants.USER_ACCOUNT)
            timelineTweets.forEach {
                val tweet = it
                tweets.add(
                    TweetModel(
                        tweet.id, tweet.text, "@${tweet.user.screenName}",
                        tweet.user.name, tweet.createdAt.toString(), tweet.source,
                        tweet.user.profileImageURLHttps
                    )
                )
            }
            saveTweetToDao(tweets)
            Resource.Success(tweets)
        }
    }

    private fun saveTweetToDao(tweets: MutableList<TweetModel>) =
        _tweetDao.addAllTweets(tweets)

    private fun fetchTweetFromDao(): Resource<MutableList<TweetModel>> =
        Resource.Success(_tweetDao.getAllTweets())

    private fun deleteTweetFromDao() = _tweetDao.deleteTweets()
}


