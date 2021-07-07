package com.study.app_tickets_firebase

class TicketsStock {
    companion object {
        var totalAmount = 0
            set(value) {
                totalAmount = value
            }
        fun subAmount(amount: Int) {
            totalAmount = totalAmount - amount
        }
    }
}