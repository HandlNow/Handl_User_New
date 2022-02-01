package com.dev.handlusernew.network

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.dev.handlusernew.base.BaseApplication


class LocalPreference(private val mContext: Context?) {
    fun removeAll() {
        editor?.apply {
            clear()
            apply()
        }

    }

    private var preferences: SharedPreferences? = null
    private var editor: SharedPreferences.Editor? = null
    //For usage reference
//    var portNumber: Int?
//        get() = preferences?.getInt("PORT_NUMBER", 0)
//        set(number) {
//            editor?.putInt("PORT_NUMBER", number ?: 0)
//            editor?.apply()
//        }

    var token: String?
        get() = preferences?.getString("AuthToken", "") ?: ""
        set(token) {
            editor?.apply {
                putString("AuthToken", token)
                apply()
            }
        }

    companion object {

        @SuppressLint("StaticFieldLeak")
        var shared: LocalPreference = LocalPreference(BaseApplication.instance)
            private set
    }

    init {
        preferences = mContext?.getSharedPreferences(
            BaseApplication.instance?.packageName, Context.MODE_PRIVATE
        )
        editor = preferences?.edit()
        editor?.apply()
    }
}