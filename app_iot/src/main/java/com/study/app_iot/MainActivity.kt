package com.study.app_iot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch {
            val client = OkHttpClient()
            val area = "Taoyuan"
            val country = "TW"
            val key = resources.getString(R.string.open_weather_key)
            var base_url = resources.getString(R.string.open_weather_base_url)
            base_url = String.format(base_url, area, country, key)
            Log.d("MainActivity", base_url)

            val request = Request.Builder()
                .url(base_url)
                .build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Log.d("MainActivity", e.toString())
                }

                override fun onResponse(call: Call, response: Response) {
                    val json = response.body?.string()
                    Log.d("MainActivity", json.toString())
                }

            })
        }
    }
}