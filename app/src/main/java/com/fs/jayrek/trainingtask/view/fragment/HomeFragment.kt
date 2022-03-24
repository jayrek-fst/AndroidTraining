package com.fs.jayrek.trainingtask.view.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import androidx.fragment.app.Fragment
import com.fs.jayrek.trainingtask.R
import com.fs.jayrek.trainingtask.helper.JSInterface
import com.fs.jayrek.trainingtask.helper.StringHelper


class HomeFragment : Fragment() {

    @SuppressLint("SetJavaScriptEnabled", "JavascriptInterface")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val webView = view.findViewById<WebView>(R.id.webView)
        webView.loadUrl(StringHelper.homeWebUrl)

        val webSettings: WebSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webSettings.domStorageEnabled = true
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                if (Uri.parse(url).host == StringHelper.homeWebUrl) {
                    return false
                }

                Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
                    startActivity(this)
                    return true
                }
            }
        }
        webView.addJavascriptInterface(JSInterface(requireActivity()), "Android")

        return view
    }
}