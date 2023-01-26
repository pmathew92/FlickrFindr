package com.example.flickrfindr.presentation.ui.composescreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.flickrfindr.R
import com.example.flickrfindr.domain.model.Photo
import com.example.flickrfindr.presentation.viewmodel.PhotosUiState
import com.example.flickrfindr.presentation.viewmodel.SearchPhotosViewModel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun SearchPhotoScreen(navController: NavController, searchPhotoViewModel: SearchPhotosViewModel) {
    val textValue = remember {
        mutableStateOf("")
    }
    Column(modifier = Modifier.fillMaxSize()) {
        SearchView(searchPhotoViewModel, textValue)
        RecentSearchHistoryView(searchPhotoViewModel, textValue)
        PhotoUi(searchPhotoViewModel, itemClick = { photo ->
            val encodedUrl = URLEncoder.encode(photo.imageUrl, StandardCharsets.UTF_8.toString())
            navController.navigate(Screen.DetailScreen.route + "/$encodedUrl")
        })
    }
}

@Composable
fun SearchView(searchPhotoViewModel: SearchPhotosViewModel, textValue: MutableState<String>) {
    val focusManager = LocalFocusManager.current
    Row(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = textValue.value, onValueChange = {
                textValue.value = it
            },
            modifier = Modifier
                .fillMaxWidth(),
            textStyle = TextStyle(color = Color.Black, fontSize = 18.sp),
            trailingIcon = {
                if (textValue.value != "") {
                    IconButton(
                        onClick = {
                            if (textValue.value.isNotEmpty()) {
                                searchPhotoViewModel.fetchPhotos(textValue.value)
                                textValue.value = ""
                                focusManager.clearFocus()
                            }
                        }
                    ) {
                        Icon(
                            Icons.Filled.Search,
                            contentDescription = "",
                            modifier = Modifier
                                .padding(15.dp)
                                .size(24.dp)
                        )
                    }
                }
            },
            singleLine = true,
            shape = RectangleShape,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.Red,
                cursorColor = Color.Black,
                trailingIconColor = Color.Black,
                backgroundColor = Color.White,
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Black
            )

        )
    }
}

@Composable
fun RecentSearchHistoryView(
    searchPhotoViewModel: SearchPhotosViewModel,
    textValue: MutableState<String>
) {
    val searchHistoryList = searchPhotoViewModel.recentSearchQueries
    if (searchHistoryList.value.isNotEmpty()) {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 8.dp)
        ) {
            items(searchHistoryList.value) {
                SearchHistoryItem(text = it) {
                    textValue.value = it
                }
            }
        }
    }
}

@Composable
fun PhotoUi(
    searchPhotoViewModel: SearchPhotosViewModel,
    itemClick: (Photo) -> Unit
) {

    val uiState = searchPhotoViewModel.photosUiState
    when (uiState.value) {
        is PhotosUiState.Success -> {
            DisplaySearchedPhotoList(
                (uiState.value as PhotosUiState.Success).photoList,
                onItemClick = itemClick
            )
        }

        is PhotosUiState.Error -> {
            ErrorScreen(
                errorText = (uiState.value as PhotosUiState.Error).errorMessage
                    ?: stringResource(id = R.string.unknown_error)
            )
        }

        is PhotosUiState.Loading -> {
            LoadingProgress()
        }

        else -> {

        }
    }

}


@Composable
fun DisplaySearchedPhotoList(photoList: List<Photo>, onItemClick: (Photo) -> Unit) {
    if (photoList.isEmpty())
        ErrorScreen(errorText = stringResource(id = R.string.empty_result))
    else
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp)
        ) {
            items(photoList) { photo ->
                PhotoItem(photo = photo, itemClick = onItemClick)
            }
        }
}