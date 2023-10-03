package com.dhananjay.imgsearch.data.model

data class BaseResponse(
    val total: Int,
    val totalHits: Int,
    val hits: List<ImagesData>
)
