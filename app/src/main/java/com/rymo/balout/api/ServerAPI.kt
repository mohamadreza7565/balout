package com.rymo.ciel.api

import com.rymo.balout.model.News
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.*


interface ServerAPI {

    companion object {
        val BASE_URL: String
            get() = "http://newsapi.org/"
    }


    @GET("/v2/top-headlines?apiKey=fa1ee7f865a44cee963c8e06ae46768c")
    fun getNews(
        @Query("country") country: String
    ): Single<News>


}