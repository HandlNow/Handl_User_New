package com.example.handlusernew.adapter.dto

import java.io.Serializable

data class CategoryModelDTOItem(
    val created_by_id: Int?=0,
    val created_on: String ?="",
    val description: String ?= "",
    val id: Int?=0,
    val image: String?="",
    val state_id: Int?=0,
    val subcategory: ArrayList<Subcategory> = ArrayList(),
    val title: String?="",
    val type_id: Int ?=0
):Serializable