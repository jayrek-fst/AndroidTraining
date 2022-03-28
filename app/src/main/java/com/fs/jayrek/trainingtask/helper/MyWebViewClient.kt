package com.fs.jayrek.trainingtask.helper

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.content.ContextCompat.startActivity

class MyWebViewClient(private val context: Context) : WebViewClient() {
//
//    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
//        if (Uri.parse(url).host == StringHelper.homeWebUrl) {
//            // This is my web site, so do not override; let my WebView load the page
//            return false
//        }
//        // Otherwise, the link is not for a page on my site, so launch another Activity that handles URLs
//        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
////            .apply {
////            startActivity(context)
////        }
//        return true
//    }
}