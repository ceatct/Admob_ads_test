package com.rabbithole.admobadstest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    private lateinit var bannerAdBtn: MaterialButton
    private lateinit var interstitialAdBtn: MaterialButton
    private lateinit var rewardInterstitialAdBtn: MaterialButton
    private lateinit var rewardedAdBtn: MaterialButton
    private lateinit var nativeAdBtn: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bannerAdBtn = findViewById(R.id.bannerAdBtn)
        interstitialAdBtn = findViewById(R.id.interstitialAdBtn)
        rewardInterstitialAdBtn = findViewById(R.id.rewardInterstitialAdBtn)
        rewardedAdBtn = findViewById(R.id.rewardedAdBtn)
        nativeAdBtn = findViewById(R.id.nativeAdBtn)

        bannerAdBtn.setOnClickListener {
            startActivity(Intent(this, BannerAdActivity::class.java))
        }

        interstitialAdBtn.setOnClickListener {
            startActivity(Intent(this, InterstitialAdActivity::class.java))
        }

        rewardInterstitialAdBtn.setOnClickListener {
            startActivity(Intent(this, RewardInterstitialAdActivity::class.java))
        }

        rewardedAdBtn.setOnClickListener {
            startActivity(Intent(this, RewardedAdActivity::class.java))
        }

        nativeAdBtn.setOnClickListener {
            startActivity(Intent(this, NativeAdActivity::class.java))
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        moveTaskToBack(true)
    }

}