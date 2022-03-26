package com.fs.jayrek.trainingtask.view.activity

import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import com.fs.jayrek.trainingtask.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpWebViewActivity (): AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_webview)
//        val webView = findViewById<WebView>(R.id.webView)

    }
}