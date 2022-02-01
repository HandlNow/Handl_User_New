@file:Suppress("unused")

package com.dev.handlusernew.network

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

    //handle response if a base structure is coming from backend
    private fun handleResponse(response: JSONObject) {
        val status: Int = response.optInt("status")
        val message: String = response.optString("message", "") ?: ""
        val result: Any = if (response.has("data")) response.get("data") else ""
        if (callBack != null) {
            if (status == 200) {
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

    // handle network or other kind of api call error
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
            }
            Log.w(TAG, MessageFormat.format("APICall{0} : {1}", baseLink.param(), baseLink))
            AndroidNetworking.upload(baseLink.link())
                .addHeaders(headers)
                .addMultipartFileList(name, file)
                .addMultipartParameter(baseLink.paramHas())
                .setTag(baseLink)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(call.responseListener)
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
                    .addJSONObjectBody(baseLink.param())
                    .setTag(baseLink.link())
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