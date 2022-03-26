package com.fs.jayrek.trainingtask.helper

import android.content.Context
import android.content.Intent
import android.util.Log
import android.webkit.JavascriptInterface
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.fs.jayrek.trainingtask.view.activity.SignUpWebViewActivity

class JSInterface(private val mContext: Context) {

    @JavascriptInterface
    fun signup(){
        Log.wtf("URL=3", "URL")
//        val intent = Intent(mContext, SignUpWebViewActivity::class.java)
//        mContext.startActivity(intent)
    }
}