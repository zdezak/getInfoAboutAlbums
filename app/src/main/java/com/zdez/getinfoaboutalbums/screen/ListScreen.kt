package com.zdez.getinfoaboutalbums.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.zdez.getinfoaboutalbums.navigation.Screen
import com.zdez.getinfoaboutalbums.viewmodels.ArtistsViewModel

@Composable
fun ListScreen( artistName: String, navController: NavController, viewModel: ArtistsViewModel = ArtistsViewModel(artistName)) {
    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "Result first search") },
            navigationIcon = {
                Icon(Icons.Filled.Home, contentDescription = "Home menu")
            }
        )
    }
    ) {
        var text by remember { mutableStateOf(artistName) }
        Column(verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxHeight()
        ) {
            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(16.dp)) {
                OutlinedTextField(
                    value = text,
                    onValueChange = { text = it },
                    label = { Text(text = " Поиск") },
                    modifier = Modifier.fillMaxWidth(0.65f)
                )
                Spacer(modifier = Modifier.height(6.dp))
                OutlinedButton(onClick = {
                    viewModel.getArtist(text)
                },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Поиск")
                }
            }
            LazyColumn(verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(viewModel.artist) { item ->
                    Card(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(text = item.name,
                            textAlign = TextAlign.Start,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)
                                .clickable {
                                    navController.navigate(Screen.AlbumsScreen.route + "/" + item.name)
                                })
                    }
                }
            }
        }
    }
}