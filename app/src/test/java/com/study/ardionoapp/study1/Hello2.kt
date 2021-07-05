package com.study.ardionoapp.study1

fun main() {
    val s = "100"
    val i = 100
    //print(s == i) 不同型別不可以直接比較
    println(s == i.toString())
    // "100" == 100 經過 toString() 變成 "100" == "100"
    println(s.equals(i.toString()))
    // 將字串的"100"變成數字100
    println(s.toInt() == i)
}