@file:Suppress("SpellCheckingInspection", "unused", "FunctionName")

package com.dev.handlusernew.network


import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject


class URLApi {

    companion object {
        private val TAG = URLApi::class.java.toString()
        const val SOCKET_URL = "http://45.56.122.34:1028"
        const val BaseUrl = "https://www.handlnow.com/handl-laravel/api/" //Base URL here
        const val BaseImageLink = "https://www.handlnow.com/handl-laravel/"

        // Get Categories Listing
        fun getCategory(): URLApi {
            val apiCall = URLApi()
            apiCall.method = NetworkMethod.GET
            apiCall.path = "category?key=123"
            apiCall.params = JSONObject()
            return apiCall
        }

    }

    private var path: String = ""
    private var params: JSONObject = JSONObject()
    var method: NetworkMethod = NetworkMethod.GET
    fun link(): String {
        return BaseUrl + path
    }

    fun param(): JSONObject {
        return params
    }

    fun paramHas(): HashMap<String, Any>? {
        return Gson().fromJson<HashMap<String, Any>>(
            param().toString(), object : TypeToken<HashMap<String?, Any?>?>() {}.type
        )
    }

    fun paramHashMap(): HashMap<*, *> {
        return Gson().fromJson(params.toString(), HashMap::class.java)
    }

}


