package com.study.app_tickets_firebase

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter
import kotlinx.android.synthetic.main.activity_login.*
import java.nio.charset.StandardCharsets
import java.util.*

class LoginActivity : AppCompatActivity() {
    lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        context = this
        title = "雲端購票 - 登入"

        val content = "Firebase 雲端購票"
        val content_textByte = content.toByteArray(StandardCharsets.UTF_8);
        val encoder = Base64.getEncoder();
        val encodedText = encoder.encodeToString(content_textByte);
        Log.d("LoginActivity", encodedText)

    }

    fun login(view: View) {
        val userName = et_username.text.toString()
        val intent = Intent(context, MainActivity::class.java)
        intent.putExtra("userName", userName)
        startActivity(intent)
    }

    fun consoleLogin(view: View) {
        val userName = et_username.text.toString()
        if(userName.equals("admin")) {
            val intent = Intent(context, ConsoleActivity::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(context, "無權限進入 !", Toast.LENGTH_SHORT).show()
        }
    }
}