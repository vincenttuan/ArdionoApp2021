package com.study.app_tickets

class TicketsStock {
    companion object {
        var totalAmount = 20
        fun subAmount(amount: Int) {
            totalAmount = totalAmount - amount
        }
    }
}