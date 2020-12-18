package com.liao.rxjavapairprogram

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.Callable


@RunWith(MockitoJUnitRunner::class)
class ViewModelMainActivityTest {
    lateinit var viewModelMainActivity : ViewModelMainActivity
    lateinit var repository: Repository
    @Before
    fun init(){
        //when mutilpthread running the test, in order to make them linear.
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler: Callable<Scheduler?>? -> Schedulers.trampoline() }
        //arrange
        //viewModelMainActivity = Mockito.mock(ViewModelMainActivity::class.java)
        repository = Mockito.mock(Repository::class.java)
        viewModelMainActivity = ViewModelMainActivity(repository)
    }

    //looper not available
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun test_myLiveData(){
        //arrange
        val myModule = MyModule()
        val myModuleItem = MyModuleItem("", 123456, "", 123)
        myModule.add(myModuleItem)
        //action
        viewModelMainActivity.myLiveData.value = myModule
        viewModelMainActivity.myLiveData.observeForever {
            //assert
            Assert.assertEquals(123456, it[0].id)
            Assert.assertEquals(123, it[0].userId)
        }
    }

    @Test
    fun test_getData(){
        //arrange
        val myModule = MyModule()
        val myModuleItem = MyModuleItem("", 123456, "", 123)
        myModule.add(myModuleItem)
        val observable  = io.reactivex.Observable.create<MyModule> { emitter ->
            emitter.onNext(myModule)
        }
        Mockito.`when`(repository.getApiCall()).thenReturn(observable)

        //action
        viewModelMainActivity.getData()
        viewModelMainActivity.myLiveData.observeForever {
            //assert
            Assert.assertEquals(123456, it[0].id)
            Assert.assertEquals(123, it[0].userId)
        }

    }
}


