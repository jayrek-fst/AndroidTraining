package com.fs.jayrek.trainingtask.view.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.fs.jayrek.trainingtask.R
import com.fs.jayrek.trainingtask.databinding.ActivitySplashBinding
import com.fs.jayrek.trainingtask.vmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivitySplashBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_splash)
        supportActionBar?.hide()
        screenNavigation()
    }

    private fun screenNavigation() {
        val viewModel: AuthViewModel by viewModels()
        viewModel.checkUserLogIn()
        viewModel.user.observe(this) {
            Handler().postDelayed({
                if (it != null)
                    startActivity(Intent(Intent(this, MainActivity::class.java)))
                else
                    startActivity(Intent(Intent(this, AuthActivity::class.java)))
                finish()
            }, 3000)
            /**delay for 3 seconds, for the visibility of splash*/
        }
    }
}