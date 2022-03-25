package com.fs.jayrek.trainingtask.model.repository

import com.fs.jayrek.trainingtask.helper.StringConstants
import com.fs.jayrek.trainingtask.model.model.TweetModel
import twitter4j.TwitterFactory
import kotlin.coroutines.suspendCoroutine

class TweeterRepository{

    private val twitterFactory = TwitterFactory().instance
    fun getTweets(): MutableList<TweetModel>{
        val tweets = mutableListOf<TweetModel>()
        val timelineTweets = twitterFactory.getUserTimeline(StringConstants.userAccount)
            timelineTweets.forEach {
                tweets.add(
                    TweetModel(
                        it.text, "@${it.user.screenName}", it.user.name, it.createdAt.toString(),
                        it.id, it.source, it.user.profileImageURLHttps
                    )
                )
            }
        return tweets
    }
}

