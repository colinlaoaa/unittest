package com.liao.rxjavapairprogram

fun main() {
    intersection(listOf(listOf(1, 2, 3), listOf(1, 3), listOf(1, 2, 3, 5)))
}

fun intersection(lists: List<List<Int>>) {
    if (lists.isNotEmpty()) {
        var res = lists[0]
        for (index in 0 until lists.size - 1) {
            res = res.intersect(lists[index + 1]).toList()
        }
        println(res)
    } else {
        println(null)
    }
}