package com.example.flickrfindr.presentation.ui.composescreen

import android.graphics.Paint.Align
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.flickrfindr.domain.model.Photo

@Composable
fun PhotoItem(photo: Photo, itemClick: (Photo) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color.White)
            .clickable {
                itemClick(photo)
            },
    ) {
        AsyncImage(
            model = photo.thumbnailUrl,
            contentDescription = null,
            modifier = Modifier
                .width(70.dp)
                .height(70.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = photo.title,
            style = TextStyle(
                color = Color.Black,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                fontStyle = FontStyle.Italic
            ),
            modifier = Modifier.align(alignment = Alignment.CenterVertically),
            maxLines = 1
        )
    }
}
