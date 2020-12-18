package com.liao.rxjavapairprogram

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object Client {
    private val BASE_URL = "https://jsonplaceholder.typicode.com/"
    private val endpoint by lazy {
        val client =
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()

        client.create(EndPoints::class.java)

    }
    fun getClientInstance():EndPoints = endpoint
}