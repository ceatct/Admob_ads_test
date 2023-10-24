package com.rabbithole.admobadstest

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.ads.*
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAd
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAdLoadCallback
import com.google.android.material.button.MaterialButton

class RewardedAdActivity : AppCompatActivity() {

    private companion object{
        private const val TAG = "REWARDED_AD_TAG"
    }

    private lateinit var showAdBtn : MaterialButton
    private lateinit var loadAndShowAdBtn : MaterialButton

    private var mRewardedAd: RewardedAd? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rewarded_ad)

        title = "Rewarded ad"

        MobileAds.initialize(this){ initStatus ->
            Log.d(TAG, "onCreate: status: $initStatus")
        }

        MobileAds.setRequestConfiguration(
            RequestConfiguration.Builder()
                .setTestDeviceIds(listOf("", ""))
                .build()
        )
        loadRewardedAd()

        showAdBtn = findViewById(R.id.showAdBtn)
        loadAndShowAdBtn = findViewById(R.id.loadAndShowAdBtn)


        showAdBtn.setOnClickListener{
            showRewardedAd()
        }

        loadAndShowAdBtn.setOnClickListener{
            loadAndShowRewardedAd()
        }

    }

    private fun loadRewardedAd(){
        RewardedAd.load(
            this,
            "ca-app-pub-3940256099942544/5224354917",
            AdRequest.Builder().build(),
            object: RewardedAdLoadCallback(){
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    super.onAdFailedToLoad(adError)
                    Log.d(TAG, "onAdFailedToLoad: ${adError.message}")
                    mRewardedAd = null
                }

                override fun onAdLoaded(rewardedAd: RewardedAd) {
                    super.onAdLoaded(rewardedAd)
                    mRewardedAd = rewardedAd
                }
            }
        )
    }

    private fun showRewardedAd(){
        if(mRewardedAd != null){
            mRewardedAd!!.fullScreenContentCallback =
                object: FullScreenContentCallback(){
                    override fun onAdClicked() {
                        super.onAdClicked()
                    }

                    override fun onAdDismissedFullScreenContent() {
                        super.onAdDismissedFullScreenContent()
                        mRewardedAd = null
                        loadRewardedAd()
                    }

                    override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                        super.onAdFailedToShowFullScreenContent(p0)
                        mRewardedAd = null
                    }

                    override fun onAdImpression() {
                        super.onAdImpression()
                    }

                    override fun onAdShowedFullScreenContent() {
                        super.onAdShowedFullScreenContent()
                    }
                }
            mRewardedAd!!.show(this){
                Toast.makeText(this, "Reward earned", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadAndShowRewardedAd(){
        val progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please, wait")
        progressDialog.setMessage("Loading...")
        progressDialog.show()

        RewardedAd.load(
            this,
            "ca-app-pub-3940256099942544/5224354917",
            AdRequest.Builder().build(),
            object: RewardedAdLoadCallback(){
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    super.onAdFailedToLoad(adError)
                    Log.d(TAG, "onAdFailedToLoad: ${adError.message}")
                    mRewardedAd = null
                    progressDialog.dismiss()
                }

                override fun onAdLoaded(rewardedAd: RewardedAd) {
                    super.onAdLoaded(rewardedAd)
                    mRewardedAd = rewardedAd
                    progressDialog.dismiss()
                    showRewardedAd()
                }
            }
        )
    }

}