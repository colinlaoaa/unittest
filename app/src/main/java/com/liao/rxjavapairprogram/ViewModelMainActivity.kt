package com.liao.rxjavapairprogram


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

open class ViewModelMainActivity(private var repository: Repository) : ViewModel() {

    private var myDisposable: Disposable? = null
    //private val repository = Repository()
    val myLiveData:MutableLiveData<MyModule> by lazy {
        MutableLiveData<MyModule>()
    }
    open fun getData(){
        val myObservable = repository.getApiCall()

        val myObserver = object : Observer<MyModule> {
            override fun onSubscribe(d: Disposable) {
                myDisposable = d
            }

            override fun onNext(t: MyModule) {
                myLiveData.postValue(t)
            }

            override fun onError(e: Throwable) {
            }

            override fun onComplete() {
            }

        }

        myObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(myObserver)
    }


    fun getDispose():Disposable? = myDisposable




}

 class MyViewModelFactory(val arg:Repository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ViewModelMainActivity::class.java))
            return ViewModelMainActivity(arg) as T
        throw IllegalArgumentException("It is only create MainViewModel object")
    }
}


