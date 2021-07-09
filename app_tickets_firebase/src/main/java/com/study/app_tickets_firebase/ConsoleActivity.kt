package com.study.app_tickets_firebase

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_console.*
import kotlinx.android.synthetic.main.activity_order_list.*

class ConsoleActivity : AppCompatActivity() {
    val database = Firebase.database
    val myRef = database.getReference("ticketsStock")
    lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_console)
        context = this
        title = "雲端購票 - 後台"
        // Read from the database
        myRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val children = snapshot.children
                // 統計資料累計
                var sumAllTickets = 0
                var sumOneWay = 0
                var sumRoundTrip = 0
                var sumTotal = 0

                children.forEach {
                    when(it.key.toString()) {
                        "discount" -> et_discount.setText(it.value.toString())
                        "price" -> et_price.setText(it.value.toString())
                        "totalAmount" -> et_total_amount.setText(it.value.toString())
                        // 訂單明細
                        "orders" -> {
                            it.children.forEach { // 訂購人
                                it.children.forEach { // 訂票日期
                                    it.children.forEach { // 訂票內容
                                        //Log.d("MainActivity", it.key.toString())
                                        when(it.key.toString()) { // 項目
                                            "allTickets" -> sumAllTickets += it.value.toString().toInt()
                                            "oneWay" -> sumOneWay += it.value.toString().toInt()
                                            "roundTrip" -> sumRoundTrip += it.value.toString().toInt()
                                            "total" -> sumTotal += it.value.toString().toInt()
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                // 顯示統計資料
                tv_stat.text = "總賣票數：${String.format("%,d", sumAllTickets)} 張\n" +
                               "總單程票：${String.format("%,d", sumOneWay)} 張\n" +
                               "總來回票：${String.format("%,d", sumRoundTrip * 2)} 張 （${String.format("%,d", sumRoundTrip)} 組）\n" +
                               "總銷售金額：$${String.format("%,d", sumTotal)} 元"
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

    fun update(view: View) {
        val tag = view.tag.toString()
        var value = 0.0
        when(tag) {
            "discount" -> value = et_discount.text.toString().toDouble()
            "price"    -> value = et_price.text.toString().toDouble()
            "totalAmount" -> value = et_total_amount.text.toString().toDouble()
        }
        myRef.child(tag).setValue(value)
        Toast.makeText(context, tag + " 修改成功", Toast.LENGTH_SHORT).show()
    }
}