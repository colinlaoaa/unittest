package com.liao.rxjavapairprogram

import io.reactivex.Observable
import retrofit2.http.GET

interface EndPoints {
    @GET("posts")
    fun getCall(): Observable<MyModule>
}