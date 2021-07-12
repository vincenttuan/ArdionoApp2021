package com.study.app_tickets_firebase

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.NavUtils
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter
import kotlinx.android.synthetic.main.activity_order_list.*
import kotlinx.android.synthetic.main.order.*

class OrderListActivity : AppCompatActivity(), RecyclerViewAdapter.RowOnItemClickListener {
    val database = Firebase.database
    val myRef = database.getReference("ticketsStock")
    lateinit var userName: String
    lateinit var context: Context
    lateinit var recyclerViewAdapter: RecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_list)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        context = this

        // 取得上一頁傳來的 userName 參數資料
        userName = intent.getStringExtra("userName").toString()
        if (userName == null || userName.equals("") || userName.equals("null")) {
            title = "雲端購票全紀錄"
        } else {
            title = "Hi " + userName + " 的雲端購票紀錄"
        }
        // Read from the database
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val children = snapshot.children
                val orderList = mutableListOf<Order>()
                children.forEach {
                    if (it.key.toString() == "orders") {
                        if (userName == null || userName.equals("") || userName.equals("null")) {
                            // 未指名 userName --------------------------------
                            it.children.forEach {
                                val userName = it.key.toString()
                                it.children.forEach {
                                    try {
                                        val order = Order(
                                            userName,
                                            it.key.toString(),
                                            it.child("allTickets").value.toString().toInt(),
                                            it.child("roundTrip").value.toString().toInt(),
                                            it.child("oneWay").value.toString().toInt(),
                                            it.child("total").value.toString().toInt()
                                        )
                                        orderList.add(order)
                                    } catch (e: Exception) {

                                    }
                                }

                            }
                        } else {
                            // 指名 userName   --------------------------------
                            tv_info.setText(it.child(userName).toString())
                            it.child(userName).children.forEach {
                                try {
                                    val order = Order(
                                        userName,
                                        it.key.toString(),
                                        it.child("allTickets").value.toString().toInt(),
                                        it.child("roundTrip").value.toString().toInt(),
                                        it.child("oneWay").value.toString().toInt(),
                                        it.child("total").value.toString().toInt()
                                    )
                                    orderList.add(order)
                                } catch (e: Exception) {

                                }
                            }
                        }
                    }
                }
                // 更新 recycler view 的資訊
                recyclerViewAdapter.setOrders(orderList)
                // 通知 UI 變更
                recyclerViewAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })


        // init recycler view
        recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            recyclerViewAdapter = RecyclerViewAdapter(this@OrderListActivity)
            adapter = recyclerViewAdapter
            // 分隔線
            //val divider = DividerItemDecoration(context, StaggeredGridLayoutManager.VERTICAL)
            //addItemDecoration(divider)
        }

    }

    override fun onItemClickListener(order: Order) {
        val key = order.key
        val userName = order.userName
        val alert = AlertDialog.Builder(context)
        alert.setTitle("票券處置")
        alert.setMessage("票券: ${userName} [ $key ]")
        alert.setPositiveButton("退票") { dialog, which ->
            // 刪除訂單記錄
            myRef.child("orders/" + userName + "/" + key).removeValue()
            // 票數加回
            // 從 order.allTickets 加回到 firebase's totalAmount 欄位中
            // addListenerForSingleValueEvent 監聽單一欄位資料
            myRef.child("totalAmount").addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val returnTickets = snapshot.value.toString().toInt() + order.allTickets
                    myRef.child("totalAmount").setValue(returnTickets)
                }
                override fun onCancelled(error: DatabaseError) {

                }
            })
        }
        alert.setNeutralButton("ORCode") { dialog, which ->
            // 產生 QRCode
            val encodedText = userName + "/" + key
            val writer = QRCodeWriter()
            val bitMatrix = writer.encode(encodedText, BarcodeFormat.QR_CODE, 512, 512)
            val width = bitMatrix.width
            val height = bitMatrix.height
            val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
            for (x in 0 until width) {
                for (y in 0 until height) {
                    bitmap.setPixel(x, y, if (bitMatrix.get(x, y)) Color.BLACK else Color.WHITE)
                }
            }
            // 將 QRCode 顯示在 AlertDialog 中
            val qrcodeImageView = ImageView(context)
            qrcodeImageView.setImageBitmap(bitmap)
            AlertDialog.Builder(context)
                .setView(qrcodeImageView)
                .create()
                .show()
        }
        alert.setNegativeButton("否", null)
        alert.show()
        //Toast.makeText(context, order.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

}