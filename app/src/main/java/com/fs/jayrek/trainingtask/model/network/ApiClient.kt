package com.fs.jayrek.trainingtask.model.network

import com.fs.jayrek.trainingtask.helper.StringConstants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private var apiInterface: ApiInterface? = null

    fun randomUserApi(): ApiInterface {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        if (apiInterface == null)
            apiInterface = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(StringConstants.apiBaseUrlTwit)
                .client(OkHttpClient.Builder().addInterceptor(interceptor).build())
                .build()
                .create(ApiInterface::class.java)
        return apiInterface as ApiInterface
    }
}