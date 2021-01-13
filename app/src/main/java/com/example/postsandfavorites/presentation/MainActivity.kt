package com.example.postsandfavorites.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.postsandfavorites.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initNavigation()
    }

    private fun initNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.child_container) as NavHostFragment
        val navController = navHostFragment.navController
        bottom_navigation_view.setupWithNavController(navController)
        val actionBarTitle = AppBarConfiguration(setOf(R.string.posts ,R.string.favorites))
        setupActionBarWithNavController(navController ,actionBarTitle)
    }
}