package com.rymo.balout.view_model

import com.rymo.balout.model.News
import com.rymo.ciel.api.ServerAPI
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject

class HomeViewModel(var serverAPI: ServerAPI) {

    var behaviorSubject = BehaviorSubject.create<Boolean>()

    fun showLoading(): BehaviorSubject<Boolean> = behaviorSubject


    fun getNews(country: String, loading: Boolean): Single<News> {
        if (loading)
            behaviorSubject.onNext(true)
        return serverAPI.getNews(country).doFinally {
            behaviorSubject.onNext(false)
        }

    }

}