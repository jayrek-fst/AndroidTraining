package com.fs.jayrek.trainingtask.view.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.fs.jayrek.trainingtask.R
import com.fs.jayrek.trainingtask.vmodel.AuthViewModel

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val viewModel : AuthViewModel by viewModels()
        viewModel.checkUserLogIn()
        viewModel.user.observe(this, ){
            if (it != null) {
                startActivity(Intent(Intent(this, MainActivity::class.java)))
                finish()
            } else {
                startActivity(Intent(Intent(this, AuthActivity::class.java)))
                finish()
            }
        }

    }
}