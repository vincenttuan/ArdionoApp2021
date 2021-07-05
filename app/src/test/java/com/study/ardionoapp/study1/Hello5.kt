package com.study.ardionoapp.study1

/*
* 雞 + 兔 有 83 隻
* 雞的腳 + 兔的腳 有 240 隻
* 求 雞 與 兔 各有幾隻 ?
*
    83 * 2 = 166
    240 - 166 = 74
    74/(4-2) = 37 .. 兔子
    83 - 37  = 46 .. 雞
*/
fun main() {
    val amount = 83
    val feets = 240

    val rabbit = (feets - (amount * 2))/2
    val chichen = amount - rabbit

    println("雞: ${chichen} 兔子: ${rabbit}")

}