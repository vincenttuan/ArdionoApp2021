package com.study.ardionoapp.study2

import kotlin.math.round

/*
假設只有台北到高雄的票，單程票價1000元，來回票為2000元再打九折
請設計Kotlin讓它執行後完成以下規範 :
1. 詢問使用者要購買張數，如下:
Please enter number of tickets:
2. 取得使用者輸入的張數後，再詢問幾張來回票，如下:
How many round-trip tickets:
3. 最後印出本次的張數、來回票數與總金額，如下:
Total tickets: 5 (5-2*2 = 1, 1 * 1000 = 2000)
Round-trip: 2 (2000*2*0.9 = 3600)
Total: 5600
* */

class Tickets(var oneWay: Int, var roundTrip: Int) {
    val price: Int = 1000
    val discountRate: Double = 0.9
    fun total(): Int {
        // 單程票價1000元，來回票為2000元再打九折
        val total = oneWay * price + (roundTrip * (price * 2)) * discountRate
        return round(total).toInt()
    }
}

fun main() {

}

