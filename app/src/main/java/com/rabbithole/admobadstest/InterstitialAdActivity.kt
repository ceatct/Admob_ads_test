package com.rabbithole.admobadstest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.material.button.MaterialButton
import kotlin.math.log

class InterstitialAdActivity : AppCompatActivity() {

    private companion object{
        private const val TAG = "INTERSTITIAL_AD_TAG"
    }

    private lateinit var showInterstitialAdBtn : MaterialButton
    private var mInterstitialAd: InterstitialAd? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interstitial_ad)

        title = "Interstitial"
        
        MobileAds.initialize(this){status ->
            Log.d(TAG, "onCreate: status: $status")
        }

        MobileAds.setRequestConfiguration(
            RequestConfiguration.Builder()
                .setTestDeviceIds(listOf("", ""))
                .build()
        )

        loadInterstitialAd()

        showInterstitialAdBtn = findViewById(R.id.showInterstitialAdBtn)

        showInterstitialAdBtn.setOnClickListener {
            showInterstitialAd()
        }

    }

    private fun loadInterstitialAd(){
        val adRequest = AdRequest.Builder().build()
        InterstitialAd.load(this, "ca-app-pub-3940256099942544/1033173712", adRequest,
        object : InterstitialAdLoadCallback(){
            override fun onAdFailedToLoad(p0: LoadAdError) {
                super.onAdFailedToLoad(p0)
                Log.d(TAG, "onAdFailedToLoad: ")
                mInterstitialAd = null
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                super.onAdLoaded(interstitialAd)
                Log.d(TAG, "onAdLoaded: ")
                mInterstitialAd = interstitialAd
            }
        })
    }

    private fun showInterstitialAd(){
        if(mInterstitialAd != null){
            mInterstitialAd!!.setFullScreenContentCallback(object: FullScreenContentCallback(){
                override fun onAdClicked() {
                    super.onAdClicked()
                }

                override fun onAdDismissedFullScreenContent() {
                    super.onAdDismissedFullScreenContent()
                    mInterstitialAd = null
                }

                override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                    super.onAdFailedToShowFullScreenContent(adError)
                    mInterstitialAd = null
                }

                override fun onAdImpression() {
                    super.onAdImpression()
                }

                override fun onAdShowedFullScreenContent() {
                    super.onAdShowedFullScreenContent()
                }
            })
            mInterstitialAd!!.show(this)
        }
    }

}