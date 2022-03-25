package com.fs.jayrek.trainingtask.vmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fs.jayrek.trainingtask.model.model.TweetModel
import com.fs.jayrek.trainingtask.model.repository.TweeterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TweetViewModel() : ViewModel() {

    private val _tweets: MutableLiveData<List<TweetModel>> = MutableLiveData()
    val tweets: LiveData<List<TweetModel>> = _tweets

    fun getTweets(){
        viewModelScope.launch(Dispatchers.IO) {
             try{
                 val tweet = TweeterRepository().getTweets()
                 _tweets.postValue(tweet)
             } catch (e: Exception){
                 Log.wtf("tweetException", e.message)
             }
        }
    }

}