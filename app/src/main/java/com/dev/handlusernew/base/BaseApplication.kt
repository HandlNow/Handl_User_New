@file:Suppress("unused")

package com.dev.handlusernew.base

import android.annotation.SuppressLint
import android.app.Application
import android.graphics.Typeface
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.androidnetworking.AndroidNetworking
import okhttp3.OkHttpClient
import okhttp3.Protocol
import java.util.concurrent.TimeUnit

open class BaseApplication : Application(), LifecycleEventObserver {


    companion object {
        @SuppressLint("StaticFieldLeak")
        var instance: BaseApplication? = null
            private set
        var inBackground = false
            private set
        var fontInterSemiBold: Typeface? = null
        var fontLatoRegular: Typeface? = null
        var fontLatoBold: Typeface? = null
        var fontInterRegular: Typeface? = null
        var fontSFPRORegular: Typeface? = null
        var fontSFPROSemiBold: Typeface? = null

    }


    override fun onCreate() {
        super.onCreate()
        instance = this
        AndroidNetworking.initialize(this)
        val okHttpClient = OkHttpClient()
            .newBuilder()
            .protocols(listOf(Protocol.HTTP_1_1))
            .connectTimeout(12000, TimeUnit.SECONDS)
            .readTimeout(12000, TimeUnit.SECONDS)
            .writeTimeout(12000, TimeUnit.SECONDS)
        AndroidNetworking.initialize(this, okHttpClient.build())

    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_CREATE -> {}
            Lifecycle.Event.ON_START -> {
                inBackground = false
            }
            Lifecycle.Event.ON_RESUME -> {
                inBackground = false
            }
            Lifecycle.Event.ON_PAUSE -> {
                inBackground = true
            }
            Lifecycle.Event.ON_STOP -> {
                inBackground = true
            }
            Lifecycle.Event.ON_DESTROY -> {
                inBackground = true
            }
            Lifecycle.Event.ON_ANY -> {}
        }
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