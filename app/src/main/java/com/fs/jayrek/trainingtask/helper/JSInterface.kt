package com.fs.jayrek.trainingtask.helper

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.webkit.JavascriptInterface
import com.fs.jayrek.trainingtask.view.activity.AuthActivity

class JSInterface(private val activity: Activity) {

    @JavascriptInterface
    fun signup() {
        Log.wtf("URL=3", "URL")
        val intent = Intent(activity, AuthActivity::class.java)
        intent.putExtra("type", "signUp")
        activity.startActivity(intent)
        activity.finish()
    }
}