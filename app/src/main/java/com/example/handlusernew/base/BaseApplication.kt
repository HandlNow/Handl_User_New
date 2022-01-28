@file:Suppress("unused")

package com.example.handlusernew.base

import android.annotation.SuppressLint
import android.app.Application
import android.graphics.Typeface
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.androidnetworking.AndroidNetworking
import okhttp3.OkHttpClient
import okhttp3.Protocol
import java.util.*
import java.util.concurrent.TimeUnit

open class BaseApplication : Application(), LifecycleObserver {


    companion object {
        @SuppressLint("StaticFieldLeak")
        var instance: BaseApplication? = null
        var inBackground = false
        var fontInterSemiBold: Typeface? = null
        var fontLatoRegular: Typeface? = null
        var fontLatoBold: Typeface? = null
        var fontInterRegular: Typeface? = null
        var fontSFPRORegular: Typeface? = null
        var fontSFPROSemibold: Typeface? = null

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    open fun onMoveToForeground() {
        // app moved to foreground
        Log.e("inBackground", "false")
        inBackground = false
//        updateOnlineStatus(1)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    open fun onMoveToBackResume() {
        // app moved to foreground
        Log.e("inBackground", "false")
//        inBackground = false
//        updateOnlineStatus(1)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    open fun onMoveToBackPause() {
        // app moved to foreground
        Log.e("inBackground", "false")
//        inBackground = false
//        updateOnlineStatus(0)
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    open fun onMoveToBackground() {
        // app moved to background
        Log.e("inBackground", "true")
//        updateOnlineStatus(0)
        inBackground = true
    }

    var count = 0


    override fun onCreate() {
        super.onCreate()
        instance = this
        AndroidNetworking.initialize(this)
        val okHttpClient = OkHttpClient()
            .newBuilder()
            .protocols(Arrays.asList(Protocol.HTTP_1_1))
            .connectTimeout(12000, TimeUnit.SECONDS)
            .readTimeout(12000, TimeUnit.SECONDS)
            .writeTimeout(12000, TimeUnit.SECONDS)
        AndroidNetworking.initialize(this, okHttpClient.build())

    }

}

//
//@GlideModule
//class YourAppGlideModule : AppGlideModule() {
//    override fun applyOptions(context: Context, builder: GlideBuilder) {
//        builder.setLogLevel(Log.WARN)
//        super.applyOptions(context, builder)
//    }
////    override fun applyOptions(context: Context?, builder: GlideBuilder) {
////        builder.setLogLevel(Log.DEBUG)
////    }
//}