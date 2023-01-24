package com.example.flickrfindr.presentation.ui.composescreen

sealed class Screen(val route: String) {
    object SearchScreen : Screen("main_screen")
    object DetailScreen : Screen("detail_screen")
}