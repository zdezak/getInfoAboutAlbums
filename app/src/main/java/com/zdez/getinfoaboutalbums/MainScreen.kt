package com.zdez.getinfoaboutalbums

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.zdez.getinfoaboutalbums.navigation.Screen
import com.zdez.getinfoaboutalbums.screen.AlbumsScreen
import com.zdez.getinfoaboutalbums.screen.HomeScreen
import com.zdez.getinfoaboutalbums.screen.ListScreen

@ExperimentalFoundationApi
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(Screen.HomeScreen.route) { HomeScreen(navController = navController) }
        composable(Screen.ListScreen.route + "/{artistName}", arguments = listOf(
            navArgument("artistName") {
                type = NavType.StringType
                defaultValue = "amatory"
            }
        )) { entry ->
            ListScreen(artistName = entry.arguments!!.getString("artistName")!!,
                navController = navController)
        }
        composable(Screen.AlbumsScreen.route + "/{artistName}", arguments = listOf(
            navArgument("artistName") {
                type = NavType.StringType
                defaultValue = "amatory"
            }
        )) { entry ->
            AlbumsScreen(artistName = entry.arguments!!.getString("artistName")!!,
                navController = navController)
        }
    }
}