package com.study.app_tickets_firebase

class TicketsStock {
    companion object {
        var price: Int = 0
            set(value) {
                price = value
            }
        var discount: Double = 0.0
            set(value) {
                discount = value
            }
        var totalAmount = 0
            set(value) {
                totalAmount = value
            }
        fun subAmount(amount: Int) {
            totalAmount = totalAmount - amount
        }
    }
}