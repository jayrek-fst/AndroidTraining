package com.fs.jayrek.trainingtask.model.module

import android.app.Application
import com.fs.jayrek.trainingtask.model.model.TweetDao
import com.fs.jayrek.trainingtask.model.model.TwitterRoomDatabase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import twitter4j.TwitterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun firebaseAuthProvider(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun firebaseFirestoreProvider(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun twitterProvider(): TwitterFactory = TwitterFactory()

    @Provides
    @Singleton
    fun setDatabase(context: Application): TwitterRoomDatabase =
        TwitterRoomDatabase.getDatabase(context)

    @Provides
    @Singleton
    fun setDao(twitterRoomDatabase: TwitterRoomDatabase): TweetDao = twitterRoomDatabase.tweetDao()
}