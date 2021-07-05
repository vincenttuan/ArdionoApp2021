package com.study.ardionoapp.study1

fun main() {
    var x:Int = 100
    var y = 200
    println(x + y)

    // val 變數不可重新指派(=)
    val a = 10
    //a = a + 1
    // var 變數可重新指派(=)
    // 限制：必須是相同的型別
    var b = 20
    b = b + 1
    //b = "Java" // 錯誤

}
