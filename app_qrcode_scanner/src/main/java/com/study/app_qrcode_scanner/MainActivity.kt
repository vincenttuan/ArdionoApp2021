package com.study.app_qrcode_scanner

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PatternMatcher
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    private val PERMISSION_REQUEST_CODE = 200
    lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this

        checkPermission()
    }

    private fun checkPermission(): Boolean {
        val check = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
        val result = (check == PackageManager.PERMISSION_GRANTED)
        if(result) {
            Toast.makeText(context, "Permission is OK", Toast.LENGTH_SHORT).show()
            return true
        } else {
            Toast.makeText(context, "Permission is not granted", Toast.LENGTH_SHORT).show()
            return false
        }
    }
}