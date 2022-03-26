package com.fs.jayrek.trainingtask.model.repository

import com.fs.jayrek.trainingtask.helper.Resource
import com.fs.jayrek.trainingtask.helper.StringConstants
import com.fs.jayrek.trainingtask.model.model.TweetModel
import twitter4j.TwitterFactory
import javax.inject.Inject

class TweeterRepository @Inject constructor(private val _twitterFactory: TwitterFactory) {

    fun getTweets(): Resource<MutableList<TweetModel>> {
        val tweets = mutableListOf<TweetModel>()
        val timelineTweets = _twitterFactory.instance.getUserTimeline(StringConstants.userAccount)
        timelineTweets.forEach {
            tweets.add(
                TweetModel(
                    it.id, it.text, "@${it.user.screenName}", it.user.name, it.createdAt.toString(),
                    it.source, it.user.profileImageURLHttps
                )
            )
        }
        return Resource.Success(tweets)
    }
}


