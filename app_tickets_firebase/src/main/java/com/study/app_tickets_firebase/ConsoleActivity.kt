package com.study.app_tickets_firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_console)

        // Read from the database
        myRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val children = snapshot.children
                children.forEach {
                    when(it.key.toString()) {
                        "discount" -> et_discount.setText(it.value.toString())
                        "price" -> et_price.setText(it.value.toString())
                        "totalAmount" -> et_total_amount.setText(it.value.toString())
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })
    }
}