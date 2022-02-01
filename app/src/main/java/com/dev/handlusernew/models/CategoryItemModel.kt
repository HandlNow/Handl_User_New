package com.dev.handlusernew.models

import java.io.Serializable

data class CategoryItemModel(

    val subcategory: ArrayList<SubCategoryModel>? = ArrayList(),
    val catgorey: SubCategoryModel? = null

) : Serializable {
    val nameTitle: String
        get() {
            return catgorey?.title ?: title ?: ""
        }

    var category_id: Int? = 0
    var created_by_id: Int? = 0
    var created_on: String? = ""
    var description: String? = ""
    var id: Int? = 0
    var image: String? = ""
    var state_id: Int? = 0
    var title: String? = ""
    var type_id: Int? = 0
}