package com.example.flickrfindr.presentation.ui.composescreen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement.Absolute.Center
import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.flickrfindr.R

@Composable
fun PhotoDetailScreen(navController: NavController, photoUrl: String?) {
    BackHandler {
        navController.navigateUp()
    }
    Box {
        DisplayPhoto(photoUrl)
        Row {
            BackIcon(onClick = {
                navController.navigateUp()
            })
        }
    }

}

@Composable
private fun DisplayPhoto(photoUrl: String?) {
    if (photoUrl != null) {
        AsyncImage(
            model = photoUrl, contentDescription = null, modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    } else {
        ErrorScreen(errorText = stringResource(id = R.string.null_url))
    }
}

@Composable
private fun BackIcon(onClick: () -> Unit) {
    Icon(
        Icons.Filled.ArrowBack,
        contentDescription = "",
        modifier = Modifier
            .padding(15.dp)
            .size(32.dp)
            .clickable {
                onClick()
            }
    )
}