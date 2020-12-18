package com.liao.rxjavapairprogram

class MyModule : ArrayList<MyModuleItem>()

data class MyModuleItem(
    var body: String,
    var id: Int,
    var title: String,
    var userId: Int
)