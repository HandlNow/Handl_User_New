@file:Suppress("unused")

package com.example.handlusernew.network

import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.androidnetworking.interfaces.StringRequestListener
import org.json.JSONException
import org.json.JSONObject
import java.io.File
import java.text.MessageFormat

class NetworkClass {
    private var callBack: Response? = null

    private fun handleResponse(response: JSONObject) {
        val status: Boolean = response.optBoolean("status", false)
        val message: String = response.optString("message", "") ?: ""
        val result: Any = if (response.has("data")) response.get("data") else ""
        if (callBack != null) {
            if (status) {
                if (result is String && result == "Invalid User Token") {
                    Log.w(TAG, MessageFormat.format("APICall{0}", response))
                    callBack?.onErrorResponse("Invalid User Token")
                } else {
                    callBack?.onSuccessResponse(result.toString(), message)
                }
            } else {
                Log.w(TAG, MessageFormat.format("APICall{0}", response))
                callBack?.onErrorResponse(message, result.toString())
            }
        }
    }

    private fun handleError(error: ANError) {
        Log.w(TAG, MessageFormat.format("APICall{0}", error))
        if (error.errorBody != null) {
            if (callBack != null) {
                try {
                    val jsonObject = JSONObject(error.errorBody)
                    val message =
                        jsonObject.optString("message") ?: jsonObject.optString("exception") ?: ""
                    val result: Any = if (jsonObject.has("data")) jsonObject.get("data") else ""
                    callBack?.onErrorResponse(
                        if (message.isEmpty()) jsonObject.toString() else message,
                        response = result.toString()
                    )
                } catch (e: JSONException) {
                    e.printStackTrace()
                    callBack?.onErrorResponse(error.errorBody)
                }
            }
        } else {
            if (callBack != null) {
                callBack?.onErrorResponse(error.errorDetail)
            }
        }
    }


    @Deprecated("not Using right now")
    private var responseStringListener: StringRequestListener = object : StringRequestListener {
        override fun onResponse(responseValue: String) {
            val response = JSONObject(responseValue)
            handleResponse(response)
        }

        override fun onError(error: ANError) {
            handleError(error)
        }
    }

    private var responseListener: JSONObjectRequestListener = object : JSONObjectRequestListener {
        override fun onResponse(response: JSONObject) {
            handleResponse(response)
        }

        override fun onError(error: ANError) {
            handleError(error)
        }
    }


    companion object {
        val TAG = NetworkClass::class.java.toString()
        fun callFileUpload(
            baseLink: URLApi,
            file: ArrayList<File>,
            name: String = "notes_images[]",
            callBack: Response?
        ) {
            val call = NetworkClass()
            call.callBack = callBack
            val token = LocalPreference.shared.token
            val headers = HashMap<String, String>()
            if (token?.isNotEmpty() == true) {
                headers["Authorization"] = "Bearer ${token.trim()}"
//                headers["session_token"] = "$token"
            }
//            headers["Accept"] = "application/json"
//            headers["Content-Type"] = "application/json"
            Log.w(TAG, MessageFormat.format("APICall{0} : {1}", baseLink.param(), baseLink))
            AndroidNetworking.upload(baseLink.link())
                .addHeaders(headers)
                .addMultipartFileList(name, file)
//                .addPathParameter(headers)
                .addMultipartParameter(baseLink.paramHas())
//                .addMultipartParameter(headers)
                .setTag(baseLink)
//                .setContentType("application/json")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(call.responseListener)
//                .addMulti
        }



        fun callApi(
            baseLink: URLApi, callBack: Response
        ) {
            val call = NetworkClass()
            call.callBack = callBack
            val token = LocalPreference.shared.token
            val headers = HashMap<String, String>()
            if (token?.isNotEmpty() == true) {
                headers["Authorization"] = "Bearer ${token.trim()}"
//                headers["session_token"] = "$token"
            }
            headers["Accept"] = "application/json"
            headers["Content-Type"] = "application/json"

            Log.d(
                TAG,
                MessageFormat.format(
                    "APICall{0} : {1} , {2}",
                    baseLink.param(),
                    baseLink.link(),
                    baseLink.method
                )
            )
            when (baseLink.method) {
                NetworkMethod.POST -> AndroidNetworking.post(baseLink.link())
                    .addHeaders(headers)
//                    .addUrlEncodeFormBodyParameter(baseLink.paramHashMap())
                    .addJSONObjectBody(baseLink.param())
                    .setTag(baseLink.link())
//                    .setContentType("application/json")
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(call.responseListener)
                NetworkMethod.GET -> {
                    AndroidNetworking.get(baseLink.link())
                        .addHeaders(headers)
                        .setTag(baseLink.link())
                        .setPriority(Priority.MEDIUM)
                        .build()
                        .getAsJSONObject(call.responseListener)
                }
                NetworkMethod.DELETE -> AndroidNetworking.delete(baseLink.link())
                    .addHeaders(headers)
                    .setTag(baseLink.link())
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(call.responseListener)
                NetworkMethod.PUT -> AndroidNetworking.put(baseLink.link())
                    .addHeaders(headers)
                    .addJSONObjectBody(baseLink.param())
                    .setTag(baseLink.link())
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(call.responseListener)
                NetworkMethod.PATCH -> AndroidNetworking.patch(baseLink.link())
                    .addHeaders(headers)
                    .addJSONObjectBody(baseLink.param())
                    .setTag(baseLink.link())
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(call.responseListener)
            }
        }
    }
}

enum class NetworkMethod {
    POST, GET, DELETE, PUT, PATCH
}

interface Response {
    fun onSuccessResponse(response: String?, message: String)
    fun onErrorResponse(error: String?, response: String? = "")
}