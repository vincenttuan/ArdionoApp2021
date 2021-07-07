package com.study.app_tickets_firebase

import kotlin.math.round

class Tickets(var allTickets: Int, var roundTrip: Int) {
    var price: Int = 0
        set(value) {
            price = value
        }
    var discountRate: Double = 0.0
        set(value) {
            discountRate = value
        }

    var oneWay: Int = 0
    init {
        oneWay = allTickets - roundTrip * 2
    }
    fun total(): Int {
        // 單程票價1000元，來回票為2000元再打九折
        val total = oneWay * price + (roundTrip * (price * 2)) * discountRate
        return round(total).toInt()
    }
}