package com.dhananjay.imgsearch.data.repository

import com.dhananjay.imgsearch.data.datasource.ApiURL
import com.dhananjay.imgsearch.data.datasource.ImgApiService
import com.dhananjay.imgsearch.data.model.BaseResponse
import com.dhananjay.imgsearch.utils.network.Resource
import javax.inject.Inject

class ImageRepository @Inject constructor(private val apiService: ImgApiService) {
    suspend fun search(q: String): Resource<BaseResponse> {
        return try {

            val result =
                apiService.getImageListData(
                    query = q,
                    apiKey = ApiURL.API_KEY,
                    imageType = ApiURL.IMG_TYPE
                )
            Resource.Success(data = result)
        } catch (e: Exception) {
            Resource.Error(message = e.message.toString())
        }
    }
}