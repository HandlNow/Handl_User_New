package com.dev.handlusernew.models

import java.io.Serializable

data class SubCategoryModel(
    val category_id: Int? = 0,
    val created_by_id: Int? = 0,
    val created_on: String? = "",
    val description: String? = "",
    val id: Int? = 0,
    val image: String? = "",
    val state_id: Int? = 0,
    val title: String? = "",
    val type_id: Int? = 0
) : Serializable