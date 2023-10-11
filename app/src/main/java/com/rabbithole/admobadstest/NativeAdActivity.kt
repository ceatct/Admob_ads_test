package com.rabbithole.admobadstest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration

class NativeAdActivity : AppCompatActivity() {

    private companion object{
        private const val TAG = "NATIVE_AD_TAG"
    }

    private lateinit var productsRv: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_native_ad)

        title = "Native ad"

        MobileAds.initialize(this){InitStatus ->
            Log.d(TAG, "onCreate: status: $InitStatus")
        }

        MobileAds.setRequestConfiguration(
            RequestConfiguration.Builder()
                .setTestDeviceIds(listOf("", ""))
                .build()
        )

        productsRv = findViewById(R.id.productsRv)

        loadProducts()

    }

    private fun loadProducts() {
        val titles = arrayOf("title 1", "title 2", "title 3","title 4","title 5","title 6","title 7","title 8","title 9","title 10", "title 11")
        val descriptions = arrayOf("description 1", "description 2", "description 3","description 4","description 5","description 6","description 7","description 8","description 9","description 10", "description 11")

        val productsArrayList= ArrayList<ModelProduct>()
        for(i in titles.indices){
            val model = ModelProduct(R.drawable.ic_android_black, titles[i], descriptions[i], 3.2f)
            productsArrayList.add(model)
        }
        val adapterProduct = AdapterProduct(this, productsArrayList)
        productsRv.adapter = adapterProduct
    }
}