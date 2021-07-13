package com.rymo.ciel.app

import com.rymo.ciel.api.ServerAPI
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitRequestOptions {

    companion object {
        public fun getAPI(): ServerAPI {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(ServerAPI.BASE_URL)
                .build()

            return retrofit.create(ServerAPI::class.java)
        }
    }


}