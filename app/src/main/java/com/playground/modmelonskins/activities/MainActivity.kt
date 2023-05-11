package com.playground.modmelonskins.activities

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.playground.modmelonskins.R
import com.playground.modmelonskins.databinding.ActivityMainBinding
import com.playground.modmelonskins.fragments.base.DetailsToolbarListener
import com.playground.modmelonskins.fragments.base.HomeToolbarListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), DetailsToolbarListener, HomeToolbarListener {

    private val mainViewModel: MainViewModel by viewModels()

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val navView by lazy {
        binding.navView
    }

    private val navController by lazy {
        findNavController(R.id.nav_host_fragment_activity_main)
    }

    private val appBarConfiguration by lazy {
        AppBarConfiguration(
            setOf(
                R.id.navigation_skins, R.id.navigation_mods, R.id.navigation_info
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarLayout.toolbarMaterial)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        mainViewModel.apply {
            loadBanner(binding.toolbarLayout.adView)
            setObservable()
        }
    }

    private fun MainViewModel.setObservable() {
        showRateDialog.observe(this@MainActivity){
            navController.navigate(R.id.dialogRateFragment)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun setCenterTextToolbar(boolean: Boolean){
        binding.toolbarLayout.toolbarMaterial.apply {
            isTitleCentered = boolean
        }
    }

    override fun setDetailsToolbar(newTitle: String) {
        setCenterTextToolbar(true)
        binding.toolbarLayout.toolbarMaterial.apply {
            title = newTitle
            setNavigationIconTint(Color.WHITE)
            setNavigationIcon(R.drawable.ic_back_toolbar)
        }
    }

    override fun setHomeToolbar() {
        setCenterTextToolbar(false)
    }

    companion object{

        fun newInstance(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }

    }
}