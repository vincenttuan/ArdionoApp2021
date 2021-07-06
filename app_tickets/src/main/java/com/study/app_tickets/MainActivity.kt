package com.study.app_tickets

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this
        initUI()
    }

    fun initUI() {
        // 配置剩餘張數
        tv_total_amount.setText(TicketsStock.totalAmount.toString())
    }

    fun buyTicket(view: View) {
        val allTickets = et_all_tickets.text.toString().toInt()
        val roundTrip  = et_round_trip.text.toString().toInt()
        val tickets = Tickets(allTickets, roundTrip)
        //  result = 總票數：%d\n來回票：%d\n單程票：%d\n總金額：$%,d
        var result = resources.getString(R.string.tickets_result)
        result = String.format(result,
                                tickets.allTickets,
                                tickets.roundTrip,
                                tickets.oneWay,
                                tickets.total())
        tv_result.setText(result)
    }
}