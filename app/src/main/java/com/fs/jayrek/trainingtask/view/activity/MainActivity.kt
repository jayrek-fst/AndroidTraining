package com.fs.jayrek.trainingtask.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.fs.jayrek.trainingtask.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navController = findNavController(R.id.nav_host_dashboard)

        val appBarConfig = AppBarConfiguration(setOf(R.id.homeFragment, R.id.feedsFragment, R.id.othersFragment))
        setupActionBarWithNavController(navController, appBarConfig)

        bottomNavigationView.setupWithNavController(navController)
    }
}