package com.study.app_qrcode_maker

import android.graphics.Bitmap
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun generate(view: View) {
        val contents = et_message.text.toString()
        val writer = QRCodeWriter()
        val bitMatrix = writer.encode(contents, BarcodeFormat.QR_CODE, 512, 512)
        val width = bitMatrix.width
        val height = bitMatrix.height
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
        // 將 bitMatrix 繪製到 bitmap
        for(x in 0 until width) {
            for(y in 0 until height) {
                // 有資料黑色, 無資料白色
                bitmap.setPixel(x, y, if(bitMatrix.get(x, y)) Color.BLACK else Color.WHITE)
            }
        }
        iv_qrcode.setImageBitmap(bitmap)
    }
}