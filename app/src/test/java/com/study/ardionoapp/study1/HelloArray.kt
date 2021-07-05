package com.study.ardionoapp.study1

import java.util.*

fun main() {
    // 數組陣列
    val num1 = arrayOf(1, 5, "7", 3.0) // 隱式
    val num2 = arrayOf<Int>(1, 5, 7, 3) // 顯式
    // for-loop
    for(i in 0..num1.size-1) {
        println(num1[i])
    }
    for(i in num1.indices) { // num1.indices = 0..num1.size-1
        println(num1[i])
    }
    // contentToString 直接印出陣列內容 (Arrays.toString(xx))
    println(num1.contentToString())
    println(num2.contentToString())
    // 使用 lambda 語法創建陣列
    val num3 = Array(5, {i -> i * 1})
    println(num3.contentToString())
    val num4 = Array(5) { i -> i * 1 }
    println(num4.contentToString())
}