package com.study.app_qrcode_scanner

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
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val PERMISSION_REQUEST_CODE = 200
    private lateinit var context: Context
    private lateinit var codeScanner: CodeScanner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
                    .setPositiveButton("Exit", DialogInterface.OnClickListener { dialogInterface, i -> finish() })
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