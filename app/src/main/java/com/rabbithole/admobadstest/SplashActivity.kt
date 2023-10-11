package com.rabbithole.admobadstest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView
import com.google.android.material.button.MaterialButton

class SplashActivity : AppCompatActivity() {

    private lateinit var timerTv: TextView
    private var secondsRemaining: Long = 0

    private companion object{
        private const val TAG = "SPLASH_TAG"
        private const val COUNTER_TIMER: Long = 5
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        timerTv = findViewById(R.id.timerTv)
        
        createTimer(COUNTER_TIMER)

    }

    private fun createTimer(seconds: Long) {
        val countDownTimer: CountDownTimer = object : CountDownTimer(seconds * 1000,1000){
            override fun onTick(millisecondsFinished: Long) {
                secondsRemaining = millisecondsFinished / 1000+1
                timerTv.text = "Loading in $secondsRemaining"
            }

            override fun onFinish() {
                secondsRemaining = 0
                timerTv.text = "Loading..."

                var application = application
                if(application !is MyApplication){
                    startMainActivity()
                    return
                }
                application.showAdIfAvailable(this@SplashActivity,
                    object : MyApplication.OnShowAdCompleteListener {
                        override fun onShowAdComplete() {
                            startMainActivity()
                        }
                    })
            }
        }
        countDownTimer.start()
    }

    private fun startMainActivity(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}