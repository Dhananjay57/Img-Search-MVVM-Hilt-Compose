package com.dhananjay.imgsearch.utils.network

import com.dhananjay.imgsearch.data.model.ImagesData

data class DataState(
    val isLoading: Boolean = false,
    val data: List<ImagesData> = emptyList(),
    val error: String = ""
)
