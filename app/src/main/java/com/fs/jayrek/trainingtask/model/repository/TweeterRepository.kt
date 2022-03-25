package com.fs.jayrek.trainingtask.model.repository

import com.fs.jayrek.trainingtask.helper.StringConstants
import com.fs.jayrek.trainingtask.model.model.TweetModel
import twitter4j.TwitterFactory

class TweeterRepository{

    private val twitter = TwitterFactory().instance
    fun getTweets(): MutableList<TweetModel> {
        val tweets = mutableListOf<TweetModel>()
        val timelineTweets = twitter.getUserTimeline(StringConstants.userAccount)

            timelineTweets.forEach {
                val photoUrl = if (!it.mediaEntities.isNullOrEmpty()) {
                    "${it.mediaEntities[0].mediaURLHttps}:small"
                } else null
                tweets.add(
                    TweetModel(
                        it.text, "@${it.user.screenName}", it.user.name, it.createdAt.toString(),
                        it.id, it.source, it.user.profileImageURLHttps, photo = photoUrl
                    )
                )
            }
        return tweets
    }
}


