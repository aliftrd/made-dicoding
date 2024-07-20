package com.github.aliftrd.moovai.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.github.aliftrd.core.utils.ext.gone
import com.github.aliftrd.core.utils.ext.show
import com.github.aliftrd.moovai.R
import com.github.aliftrd.moovai.databinding.ActivityMainBinding
import com.github.aliftrd.home.R as homeR

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val navHostBottomBar = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navControllerBottomBar = navHostBottomBar.navController

        binding.bottomNavigationView.setupWithNavController(navHostBottomBar.navController)
        navControllerBottomBar.addOnDestinationChangedListener {_, destination,_ ->
            val isHomeFragment = destination.id == homeR.id.homeFragment

            if (isHomeFragment) {
                binding.bottomNavigationView.show()
            } else {
                binding.bottomNavigationView.gone()
            }
        }
    }
}