package com.dhananjay.imgsearch.ui.component

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dhananjay.imgsearch.data.repository.ImageRepository
import com.dhananjay.imgsearch.utils.network.DataState
import com.dhananjay.imgsearch.utils.network.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageSearchViewModel @Inject constructor(private val repository: ImageRepository) :
    ViewModel() {
    val imageList: MutableState<DataState> = mutableStateOf(DataState())

    @ExperimentalCoroutinesApi
    @FlowPreview
    fun getImageList(query: String) = viewModelScope.launch {
        imageList.value = DataState(isLoading = true)

        try {
            when (val result = repository.search(query)) {
                is Resource.Error -> {
                    imageList.value = DataState(error = "Something went wrong")
                }

                is Resource.Success -> {
                    result.data?.hits?.let {
                        imageList.value = DataState(data = it)
                    }
                }

                is Resource.Loading -> imageList.value = DataState(isLoading = true)
            }
        } catch (e: Exception) {
            imageList.value = DataState(error = "Something went wrong")
        }
    }

}