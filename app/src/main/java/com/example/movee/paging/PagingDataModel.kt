package com.example.movee.paging

import com.google.gson.annotations.SerializedName

data class PagingDataModel<T>(
    val data: List<T>,
    @SerializedName("pageCount", alternate = ["total_pages"])
    val pageCount: Int,
    val isSuccess: Boolean = true,
    val errorBody: Exception? = null
)