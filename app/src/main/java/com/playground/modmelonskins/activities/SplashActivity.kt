package com.playground.modmelonskins.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.playground.modmelonskins.databinding.ActivitySplashBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private val splashViewModel: SplashViewModel by viewModels()

    private val binding by lazy {
        ActivitySplashBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        splashViewModel.apply {
            setObservable()
        }
    }

    private fun SplashViewModel.setObservable(){
        endTimer.observe(this@SplashActivity){
            showInterstitial(this@SplashActivity)
        }
        nextScreen.observe(this@SplashActivity){
            startActivity(MainActivity.newInstance(this@SplashActivity))
            finish()
        }
    }

}