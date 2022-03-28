package com.fs.jayrek.trainingtask.vmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fs.jayrek.trainingtask.helper.Resource
import com.fs.jayrek.trainingtask.model.model.TweetModel
import com.fs.jayrek.trainingtask.model.repository.TwitterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TweetViewModel @Inject constructor(private val _repo: TwitterRepository) : ViewModel() {

    private val _tweets: MutableLiveData<Resource<MutableList<TweetModel>>> = MutableLiveData()
    val tweets: LiveData<Resource<MutableList<TweetModel>>> = _tweets

    fun getTweets() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _tweets.postValue(Resource.Loading())
                val tweet = _repo.checkResourceData()
                _tweets.postValue(tweet)
            } catch (e: Exception) {
                _tweets.postValue(Resource.Error(e.message.toString()))
            }
        }
    }

    fun refresh(){
        viewModelScope.launch {
            _repo.refreshTweets()
        }
    }

}