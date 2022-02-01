package com.dev.handlusernew.utils

import com.google.gson.Gson


fun <T> generateList(response: String, type: Class<Array<T>>): ArrayList<T> {
    val arrayList = ArrayList<T>()
    if (response.isEmpty() || response == "null" || response == "\"[]\"") {
        return arrayList
    }
    arrayList.addAll(listOf(*Gson().fromJson(response, type)))
    return arrayList
}