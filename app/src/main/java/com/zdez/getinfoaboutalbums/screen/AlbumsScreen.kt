package com.zdez.getinfoaboutalbums.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@ExperimentalFoundationApi
@Composable
fun AlbumsScreen(artistName: String, navController: NavController) {
    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "List of albums") },
            navigationIcon = {
                IconButton(onClick = {
                    navController.navigateUp()
                }
                ) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Albums result list")
                }

            }
        )
    }
    ) {
        LazyVerticalGrid(
            cells = GridCells.Adaptive(minSize = 128.dp)
        ) {
//            items(photos) { photo ->
//                PhotoItem(photo)
//            }
        }
    }
}