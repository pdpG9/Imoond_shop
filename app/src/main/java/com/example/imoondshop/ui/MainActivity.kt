package com.example.imoondshop.ui


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.imoondshop.R
import com.example.imoondshop.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var isFinish = 0
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_container)
        navController = navHostFragment!!.findNavController()
        val bottomNavView = binding.bottomNavView
        setupWithNavController(bottomNavView, navController)

        navController.addOnDestinationChangedListener { _, destionation, _ ->
            when (destionation.id) {
                R.id.homeFragment -> showBottomNav()

                R.id.categoryFragment -> showBottomNav()

                R.id.basketFragment -> showBottomNav()

                R.id.accountFragment -> showBottomNav()

                else -> hideBottomNav()
            }
        }

    }


    private fun showBottomNav() {
        binding.bottomNavView.visibility = View.VISIBLE
    }

    private fun hideBottomNav() {
        binding.bottomNavView.visibility = View.GONE

    }
    private fun changeColorStatusBar(color:Int){
        val window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = color
    }

}