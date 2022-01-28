@file:Suppress("SpellCheckingInspection", "unused", "FunctionName")

package com.example.handlusernew.network


import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject




@Suppress("UNUSED_PARAMETER")
object URLApi {


    private val TAG = URLApi::class.java.toString()

    const val SOCKET_URL = "http://45.56.122.34:1028"
    public const val BaseUrl = "https://www.handlnow.com/handl-laravel/api/" //Base URL here
    const val BaseImageLink = "http://45.56.122.34/"
    private var path: String = ""
    private var params: JSONObject = JSONObject()
    var method: NetworkMethod = NetworkMethod.GET
    fun link(): String {
        return BaseUrl + path
    }

    fun param(): JSONObject {
        return params
    }

    final fun paramHas(): HashMap<String, Any>? {
        return Gson().fromJson<HashMap<String, Any>>(
            param().toString(), object : TypeToken<HashMap<String?, Any?>?>() {}.type
        )
    }

    final fun paramHashMap(): HashMap<*, *> {
        return Gson().fromJson(params.toString(), HashMap::class.java)
    }




    fun getCategory(): URLApi {
        method = NetworkMethod.POST
        path = "category"
        params = JSONObject()
        return this
    }

}


