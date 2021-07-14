package com.study.app_tickets_firebase

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PatternMatcher
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_qrcode.*
import java.text.SimpleDateFormat
import java.util.*

class QRCodeActivity : AppCompatActivity() {
    val database = Firebase.database
    val myRef = database.getReference("ticketsStock")

    private val PERMISSION_REQUEST_CODE = 200
    private lateinit var context: Context
    private lateinit var codeScanner: CodeScanner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qrcode)
        context = this

        if(checkPermission()) {
            // 正常執行程式
            runProgram()
        } else {
            // 啟動動態核准對話框
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.CAMERA),
                PERMISSION_REQUEST_CODE)

        }
    }

    fun runProgram() {
        // 正常執行程式
        title = "正常執行程式"

        codeScanner = CodeScanner(context, scanner_view)
        // 設定 Scanner 預設參數
        codeScanner.camera = CodeScanner.CAMERA_BACK
        codeScanner.formats = CodeScanner.ALL_FORMATS
        codeScanner.autoFocusMode = AutoFocusMode.SAFE
        codeScanner.scanMode = ScanMode.SINGLE
        codeScanner.isAutoFocusEnabled = true
        codeScanner.isFlashEnabled = false

        // 解碼
        codeScanner.decodeCallback = DecodeCallback {
            runOnUiThread {
                val result_text = it.text
                AlertDialog.Builder(context)
                    .setMessage("QRCode: $result_text")
                    .setPositiveButton("使用", DialogInterface.OnClickListener { dialogInterface, i ->
                        val order = Gson().fromJson<Order>(result_text, Order::class.java)
                        val path = order.userName + "/" + order.key
                        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                        val currentTimeString = sdf.format(Date())
                        // 移除 orders
                        myRef.child("orders/" + path).removeValue()
                        // 新建 orders_history
                        myRef.child("orders_history/" + path + "/ts").setValue(currentTimeString)
                        myRef.child("orders_history/" + path + "/key").setValue(order.key)
                        myRef.child("orders_history/" + path + "/userName").setValue(order.userName)
                        myRef.child("orders_history/" + path + "/allTickets").setValue(order.allTickets)
                        myRef.child("orders_history/" + path + "/oneWay").setValue(order.oneWay)
                        myRef.child("orders_history/" + path + "/roundTrip").setValue(order.roundTrip)
                        myRef.child("orders_history/" + path + "/total").setValue(order.total)
                        finish()
                    })
                    .setNegativeButton("取消", DialogInterface.OnClickListener { dialogInterface, i -> finish() })
                    .create()
                    .show()
            }
        }

        // 錯誤
        codeScanner.errorCallback = ErrorCallback {
            runOnUiThread {
                val result_message = it.message
                AlertDialog.Builder(context)
                    .setMessage("Error: $result_message")
                    .create()
                    .show()
            }
        }

        codeScanner.startPreview()

    }

    override fun onStop() {
        super.onStop()
        try {
            codeScanner.releaseResources()
        } catch (e: Exception) {

        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode == PERMISSION_REQUEST_CODE) {
            // 正常執行程式
            runProgram()
        }
    }


    // 使用者是否有同意使用權限(Ex:Camera)
    private fun checkPermission(): Boolean {
        val check = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
        val result = (check == PackageManager.PERMISSION_GRANTED)
        if(result) {
            title = "Permission is OK"
            Toast.makeText(context, "Permission is OK", Toast.LENGTH_SHORT).show()
            return true
        } else {
            title = "Permission is not granted"
            Toast.makeText(context, "Permission is not granted", Toast.LENGTH_SHORT).show()
            return false
        }
    }
}