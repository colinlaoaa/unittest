package com.liao.rxjavapairprogram

import io.reactivex.Observable

open class Repository {
    open fun getApiCall():Observable<MyModule> {
        return Client.getClientInstance().getCall()
    }
}