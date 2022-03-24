package com.fs.jayrek.trainingtask.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.fs.jayrek.trainingtask.R

class AuthActivity: AppCompatActivity() {

    private lateinit var navController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_auth) as NavHostFragment
        navController = navHostFragment.navController

        setupActionBarWithNavController(navController)
    }
    override fun onSupportNavigateUp(): Boolean = navController.navigateUp() || super.onSupportNavigateUp()

}