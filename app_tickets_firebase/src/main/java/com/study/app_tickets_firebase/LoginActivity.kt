package com.study.app_tickets_firebase

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        context = this
        title = "雲端購票 - 登入"
    }

    fun login(view: View) {
        val userName = et_username.text.toString()
        val intent = Intent(context, MainActivity::class.java)
        intent.putExtra("userName", userName)
        startActivity(intent)
    }

    fun consoleLogin(view: View) {
        
    }
}