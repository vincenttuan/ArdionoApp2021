package com.study.app_tickets

class TicketsStock {
    companion object {
        var totalAmount = 30
        fun subAmount(amount: Int) {
            totalAmount = totalAmount - amount
        }
    }
}