package com.example.flickrfindr.presentation.ui.composescreen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flickrfindr.domain.model.Photo
import com.example.flickrfindr.presentation.viewmodel.PhotosUiState
import com.example.flickrfindr.presentation.viewmodel.SearchPhotosViewModel
import org.koin.androidx.compose.get

@Preview(showBackground = true)
@Composable
fun SearchPhotoScreen() {
    val searchPhotoViewModel = get<SearchPhotosViewModel>()
    Column(modifier = Modifier.fillMaxSize()) {
        SearchView(searchPhotoViewModel)
        PhotoUi(searchPhotoViewModel)
    }
}

@Composable
fun SearchView(searchPhotoViewModel: SearchPhotosViewModel) {
    val textValue = remember {
        mutableStateOf("")
    }
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
fun PhotoUi(searchPhotoViewModel: SearchPhotosViewModel) {

    val uiState = searchPhotoViewModel.photosUiState
    when (uiState.value) {
        is PhotosUiState.Success -> {
            DisplaySearchedPhotoList((uiState.value as PhotosUiState.Success).photoList)
        }

        is PhotosUiState.Error -> {

        }

        is PhotosUiState.Loading -> {

        }

        else -> {

        }
    }

}


@Composable
fun DisplaySearchedPhotoList(photoList: List<Photo>) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp)
    ) {
        items(photoList) { photo ->
            PhotoItem(photo = photo)
        }
    }
}