package com.example.flickrfindr.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.flickrfindr.presentation.ui.composescreen.PhotoDetailScreen
import com.example.flickrfindr.presentation.ui.composescreen.Screen
import com.example.flickrfindr.presentation.ui.composescreen.SearchPhotoScreen
import com.example.flickrfindr.presentation.ui.theme.FlickrFindrTheme
import com.example.flickrfindr.presentation.viewmodel.SearchPhotosViewModel
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlickrFindrTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.LightGray
                ) {
                    Navigation()
                }
            }
        }
    }

    @Composable
    private fun Navigation() {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Screen.SearchScreen.route
        ) {
            composable(route = Screen.SearchScreen.route) {
                val searchPhotoViewModel = koinViewModel<SearchPhotosViewModel>()
                SearchPhotoScreen(navController, searchPhotoViewModel)
            }

            composable(
                route = Screen.DetailScreen.route + "/{photo_url}",
                arguments = listOf(navArgument(name = "photo_url") {
                    type = NavType.StringType
                })
            ) {
                PhotoDetailScreen(
                    navController = navController,
                    it.arguments?.getString("photo_url")
                )
            }
        }
    }
}
