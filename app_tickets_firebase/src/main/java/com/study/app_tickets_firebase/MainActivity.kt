package com.study.app_tickets_firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        refreshUI()
    }

    fun refreshUI() {
        tv_ticket_price.setText(TicketsStock.price.toString())
        tv_ticket_discount.setText((TicketsStock.discount * 10).toString())
        tv_total_amount.setText(TicketsStock.totalAmount.toString())
    }
}