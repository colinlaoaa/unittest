package com.liao.rxjavapairprogram

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    lateinit var viewModel: ViewModelMainActivity
    lateinit var myAdapter: AdapterRecyclerView
    private var myModule: MyModule = MyModule()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this, MyViewModelFactory(Repository())).get(
            ViewModelMainActivity::class.java
        )

        init()
    }

    private fun init() {
        //setup recyclerView adapter
        myAdapter = AdapterRecyclerView(this, myModule)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = myAdapter
        recycler_view.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )

        //get data
        viewModel.getData()
        viewModel.myLiveData.observe(this) {
            myModule = it
            myAdapter.refresh(myModule)
        }
    }

    override fun onDestroy() {
        viewModel.getDispose()?.dispose()
        super.onDestroy()
    }
}