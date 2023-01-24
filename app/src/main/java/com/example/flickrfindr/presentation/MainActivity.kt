package com.example.flickrfindr.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.flickrfindr.presentation.ui.composescreen.SearchPhotoScreen
import com.example.flickrfindr.presentation.ui.theme.FlickrFindrTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlickrFindrTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Gray
                ) {
//                    val navController = rememberNavController()
//                    NavHost(navController = navController, graph = )
                    SearchPhotoScreen()
                }
            }
        }
    }
}


//TODO: Remove this once done
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FlickrFindrTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
//                    val navController = rememberNavController()
//                    NavHost(navController = navController, graph = )
            SearchPhotoScreen()
        }
    }
}