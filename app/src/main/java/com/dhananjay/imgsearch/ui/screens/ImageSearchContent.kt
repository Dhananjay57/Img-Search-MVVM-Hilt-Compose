package com.dhananjay.imgsearch.ui.screens

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.dhananjay.imgsearch.R
import com.dhananjay.imgsearch.data.model.ImagesData
import com.dhananjay.imgsearch.ui.component.ImageSearchViewModel
import com.dhananjay.imgsearch.utils.network.DataState.*
import com.dhananjay.imgsearch.utils.networkconnections.ConnectionState
import com.dhananjay.imgsearch.utils.networkconnections.connectivityState
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalCoroutinesApi::class, FlowPreview::class)
@Preview
@Composable
fun ImageSearchContent(imageSearchViewModel: ImageSearchViewModel = hiltViewModel()) {
    val query: MutableState<String> = remember { mutableStateOf("") }
    val result = imageSearchViewModel.imageList.value
    // default view is grid view
    var checked by remember { mutableStateOf(true) }
    // internet connection
    val connection by connectivityState()
    val isConnected = connection === ConnectionState.Available


    Surface(Modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(4.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = query.value, onValueChange = {
                        query.value = it
                        imageSearchViewModel.getImageList(query.value)

                    }, enabled = true,
                    singleLine = true,
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Search, contentDescription = null)
                    },
                    label = { Text(text = "Search here...") },
                    modifier = Modifier.weight(2f)
                )
                Switch(
                    checked = checked,
                    onCheckedChange = {
                        checked = it
                    },
                    thumbContent = if (checked) {
                        {
                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.baseline_view_list_24),
                                contentDescription = "List",
                                modifier = Modifier
                                    .size(SwitchDefaults.IconSize)
                                    .padding(4.dp),
                            )
                        }
                    } else {
                        {
                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.baseline_grid_view_24),
                                contentDescription = "Grid",
                                modifier = Modifier.size(SwitchDefaults.IconSize),
                            )
                        }
                    }
                )
            }
            if(isConnected.not()){
                Box(
                     modifier = Modifier.fillMaxSize()
                ) {
                    Text(text = stringResource(R.string.there_is_no_internet))
                }
            }

            if (result.isLoading) {
                Log.d("TAG", "MainContent: in the loading")
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }

            if (result.error.isNotBlank()) {
                Log.d("TAG", "MainContent: ${result.error}")
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = imageSearchViewModel.imageList.value.error
                    )
                }
            }

            if (result.data.isNotEmpty()) {
                if (checked) {
                    LazyVerticalGrid(GridCells.Fixed(2)) {
                        Log.d("TAG", "MainContent: Your Token")
                        imageSearchViewModel.imageList.value.data?.let {
                            items(it) {
                                MainContentItem(it)
                            }
                        }

                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(1.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        imageSearchViewModel.imageList.value.data?.let {
                            items(it) {
                                MainContentItem(imageList = it)
                            }
                        }
                    }
                }

            }

        }
    }


}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainContentItem(imageList: ImagesData) {
    val currentLocalDateTime = LocalDateTime.now()
    val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yy hh:mm am/pm")
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Card(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .height(200.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = imageList.largeImageURL),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            )
        }
        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Total views: ${imageList.views} ${currentLocalDateTime.format(dateFormatter)}", // Replace with your title text
            style = TextStyle(fontWeight = FontWeight.SemiBold),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
    }
}
