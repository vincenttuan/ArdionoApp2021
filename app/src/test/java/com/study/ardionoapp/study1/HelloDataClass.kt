package com.study.ardionoapp.study1

import java.lang.Exception

// 錢包
data class Wallet(val name: String, var amount: Int) {
    // 如何檢查傳進來的參數值是否正確 ?
    init {
        // case 1
        //amount = if (amount >= 0) amount else 0
        // case 2
        require(amount >= 0) // 鑑別函式
    }
}
fun main() {
    val w1 = Wallet("John", 100)
    println(w1)
    try {
        val w2 = Wallet("Mary", -200)
        println(w2)
    } catch (e: Exception) {
        println("錢包錯誤: ${e}")
        e.printStackTrace(System.out) // 列出詳細錯誤
    }


}