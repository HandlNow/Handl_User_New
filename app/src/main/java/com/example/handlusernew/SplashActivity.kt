package com.example.handlusernew

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this , MainActivity::class.java))
            finish()
        },2000)

    }


//        Timer().schedule(object : TimerTask() {
//            override fun run() {
//                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
//                finish()
//                Log.d(
//                    "MainActivity:",
//                    "onCreate: waiting 5 seconds for MainActivity... loading PrimaryActivity.class"
//                )
//            }
//        }, 3000)

//        val intent = Intent(this,MainActivity::class.java)
//
//
//        startActivity(intent);
//
//        finish();

}