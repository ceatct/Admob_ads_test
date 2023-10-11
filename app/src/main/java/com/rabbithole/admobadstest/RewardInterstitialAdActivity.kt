package com.rabbithole.admobadstest

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.ads.*
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAd
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAdLoadCallback
import com.google.android.material.button.MaterialButton

class RewardInterstitialAdActivity : AppCompatActivity() {

    private companion object{
        private const val TAG = "REWARD_INTERSTITIAL_AD_TAG"
    }

    private lateinit var showAdBtn : MaterialButton
    private lateinit var loadAndShowAdBtn : MaterialButton

    private var mRewardedInterstitialAd: RewardedInterstitialAd? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reward_interstitial_ad)

        title = "Reward interstitial"

        MobileAds.initialize(this){ initStatus ->
            Log.d(TAG, "onCreate: status: $initStatus")
            loadRewardedInterstitialAd()
        }

        MobileAds.setRequestConfiguration(
            RequestConfiguration.Builder()
                .setTestDeviceIds(listOf("", ""))
                .build()
        )

        showAdBtn = findViewById(R.id.showAdBtn)
        loadAndShowAdBtn = findViewById(R.id.loadAndShowAdBtn)

        showAdBtn.setOnClickListener{
            showRewardedInterstitialAd()
        }

        loadAndShowAdBtn.setOnClickListener{
            loadAndShowRewardedInterstitialAd()
        }

    }

    private fun loadRewardedInterstitialAd(){
        RewardedInterstitialAd.load(
            this,
            "ca-app-pub-7629141945370293/7365552414",
            AdRequest.Builder().build(),
            object: RewardedInterstitialAdLoadCallback(){
                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                    super.onAdFailedToLoad(loadAdError)
                    Log.d(TAG, "onAdFailedToLoad: ${loadAdError.message}")
                    mRewardedInterstitialAd = null
                }

                override fun onAdLoaded(rewardedInterstitialAd: RewardedInterstitialAd) {
                    super.onAdLoaded(rewardedInterstitialAd)
                    Log.d(TAG, "onAdLoaded: ")
                    mRewardedInterstitialAd = rewardedInterstitialAd
                }
            }
        )
    }

    private fun showRewardedInterstitialAd(){
        if(mRewardedInterstitialAd != null){
            mRewardedInterstitialAd!!.fullScreenContentCallback =
                object: FullScreenContentCallback(){
                    override fun onAdClicked() {
                        super.onAdClicked()
                    }

                    override fun onAdDismissedFullScreenContent() {
                        super.onAdDismissedFullScreenContent()
                        mRewardedInterstitialAd = null
                        loadRewardedInterstitialAd()
                    }

                    override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                        super.onAdFailedToShowFullScreenContent(p0)
                        mRewardedInterstitialAd = null
                    }

                    override fun onAdImpression() {
                        super.onAdImpression()
                    }

                    override fun onAdShowedFullScreenContent() {
                        super.onAdShowedFullScreenContent()
                    }
                }
            mRewardedInterstitialAd!!.show(this){
                Toast.makeText(this, "Reward earned", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadAndShowRewardedInterstitialAd(){
        val progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please, wait")
        progressDialog.setMessage("Loading...")
        progressDialog.show()

        RewardedInterstitialAd.load(
            this,
            "ca-app-pub-7629141945370293/7365552414",
            AdRequest.Builder().build(),
            object: RewardedInterstitialAdLoadCallback(){
                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                    super.onAdFailedToLoad(loadAdError)
                    Log.d(TAG, "onAdFailedToLoad: ${loadAdError.message}")
                    mRewardedInterstitialAd = null
                    progressDialog.dismiss()
                }

                override fun onAdLoaded(rewardedInterstitialAd: RewardedInterstitialAd) {
                    super.onAdLoaded(rewardedInterstitialAd)
                    Log.d(TAG, "onAdLoaded: ")
                    mRewardedInterstitialAd = rewardedInterstitialAd
                    progressDialog.dismiss()
                    showRewardedInterstitialAd()
                }
            }
        )
    }

}