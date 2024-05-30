package com.valsesv.smarthome.ui.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.valsesv.smarthome.model.MainPage
import com.valsesv.smarthome.ui.alerts.AlertAddRoom

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemTopBar(navController: NavController, mainPage: MainPage, isMainPage: Boolean) {
    var expanded by remember { mutableStateOf(false) }
    val openNewHome = remember { mutableStateOf(false) }

    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Absolute.Right,
                modifier = Modifier.clickable {
                expanded = true
            }) {
                Text(
                    mainPage.mainRoom.value.name,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Icon(Icons.Default.KeyboardArrowDown, contentDescription = "Показать меню")
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    for (home in mainPage.rooms) {
                        Row(horizontalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    mainPage.mainRoom.value = home
                                    Log.d("home", mainPage.rooms.size.toString())
                                    expanded = false
                                }
                        ) {
                            Text(
                                text = home.name, fontSize = 20.sp, modifier = Modifier
                                    .padding(8.dp)
                            )
                        }
                    }

                    Row(horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                Log.d("home", mainPage.rooms.size.toString())
                                expanded = false
                                openNewHome.value = true
                            }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "add"
                        )
                    }
                }
            }
        },
        actions = {
            IconButton(onClick = {
                if (isMainPage) {
                    navController.navigate("scen")
                } else {
                    navController.navigate("main")
                }
            }) {
                Icon(
                    imageVector = if (isMainPage) {
                        Icons.Filled.PlayArrow
                    } else {
                        Icons.Filled.Home
                    },
                    contentDescription = "Localized description"
                )
            }
        },
    )
    if (openNewHome.value){
        AlertAddRoom(open = openNewHome, mainPage.rooms)
    }
}
