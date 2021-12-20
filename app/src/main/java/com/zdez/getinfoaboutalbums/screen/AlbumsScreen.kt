package com.zdez.getinfoaboutalbums.screen


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.glide.rememberGlidePainter
import com.zdez.getinfoaboutalbums.viewmodels.AlbumsViewModel

@ExperimentalFoundationApi
@Composable
fun AlbumsScreen(
    artistName: String,
    navController: NavController,
    viewModel: AlbumsViewModel = AlbumsViewModel(artistName, ""),
) {
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
            cells = GridCells.Fixed(3),
            modifier = Modifier.fillMaxSize()
                .padding(16.dp)
        ) {
            items(viewModel.albums) { album ->
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .border(1.dp, color = Color.Black)
                        .padding(6.dp)
                ) {
                    if (album.images[2].text.isEmpty()) {
                        Text(
                            text = "No image",
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxSize()
                        )
                    } else {
                        Image(
                            painter = rememberGlidePainter(album.images[2].text),
                            contentDescription = album.name,
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                }
            }
        }
    }
}