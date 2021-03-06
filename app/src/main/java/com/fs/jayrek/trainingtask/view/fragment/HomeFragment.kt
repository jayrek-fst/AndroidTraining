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
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.fs.jayrek.trainingtask.R
import com.fs.jayrek.trainingtask.databinding.FragmentHomeBinding
import com.fs.jayrek.trainingtask.helper.JSInterface
import com.fs.jayrek.trainingtask.helper.StringConstants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    @SuppressLint("SetJavaScriptEnabled", "JavascriptInterface")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentHomeBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        binding.webView.loadUrl(StringConstants.HOME_WEB_URL)

        val webSettings: WebSettings = binding.webView.settings
        webSettings.javaScriptEnabled = true
        webSettings.domStorageEnabled = true

        binding.webView.addJavascriptInterface(JSInterface(requireActivity()), "Android")
        binding.webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                if (url!!.contains("cloudfront")) {
                    return navigateItem(url.toString())
                }
                return navigateIntent(url)
            }
        }
        return binding.root
    }

    private fun navigateItem(url: String): Boolean {
        val splitter = url.split("=")
        val urlMovie = splitter[1]
        return navigateIntent(urlMovie)
    }

    private fun navigateIntent(url: String): Boolean {
        Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
            startActivity(this)
            return true
        }
    }
}