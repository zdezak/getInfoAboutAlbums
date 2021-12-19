package com.zdez.getinfoaboutalbums.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.zdez.getinfoaboutalbums.navigation.Screen

@Composable
fun HomeScreen(navController: NavController){
    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "Home") },
            navigationIcon = {
                Icon(Icons.Filled.Home, contentDescription = "Home menu")
            }
        )
    }
    ) {
        var text by remember { mutableStateOf("Введите название") }
        Column(verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxHeight()
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(16.dp)) {
                OutlinedTextField(
                    value = text,
                    onValueChange = { text = it },
                    label = { Text(text = " Поиск") },
                    modifier = Modifier.fillMaxWidth(0.65f)
                )
                Spacer(modifier = Modifier.height(6.dp))
                OutlinedButton(onClick = {
                    navController.navigate(Screen.ListScreen.route+"/"+text)
                },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Поиск")
                }
            }
        }
    }
}