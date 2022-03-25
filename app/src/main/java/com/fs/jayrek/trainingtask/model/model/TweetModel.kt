package com.fs.jayrek.trainingtask.model.model

data class TweetModel(
    val text: String,
    val screenName: String,
    val userName: String,
    val date: String,
    val id: Long,
    val source: String,
    val profileImage: String
)
