package com.dhananjay.imgsearch.data.datasource

import com.dhananjay.imgsearch.data.model.BaseResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ImgApiService {
    @GET("api/")
    suspend fun getImageListData(
        @Query("q") query: String,
        @Query("key") apiKey: String,
        @Query("image_type") imageType: String
    ): BaseResponse
}