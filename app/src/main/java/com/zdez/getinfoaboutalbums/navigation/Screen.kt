package com.zdez.getinfoaboutalbums.navigation

sealed class Screen(val route: String) {
    object HomeScreen : Screen("home_screen")
    object ListScreen : Screen("list_screen")
    object AlbumsScreen : Screen("albums_screen")
}

