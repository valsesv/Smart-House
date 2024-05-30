package com.valsesv.smarthome.ui.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.valsesv.smarthome.ui.components.device.ItemAddDeviceButton
import com.valsesv.smarthome.ui.components.device.ItemDevice
import com.valsesv.smarthome.ui.components.ItemTopBar
import com.valsesv.smarthome.model.MainPage

@Composable
fun Main(navController: NavController, mainPage: MainPage) {
    if (mainPage.rooms.size == 0) {
        mainPage.rooms.add(mainPage.mainRoom.value)
    }

    Scaffold(
        topBar = { ItemTopBar(navController, mainPage, true) },
    ) { innerPadding ->
        Box(
            contentAlignment = Alignment.BottomCenter
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                LazyColumn(
                    modifier = Modifier.weight(5f)
                ) {
                    items(mainPage.mainRoom.value.devices) { device ->
                        ItemDevice(item = device, mainPage.mainRoom.value.devices)
                    }
                    item {
                        Spacer(modifier = Modifier.height(60.dp))
                    }
                }
            }
            ItemAddDeviceButton(mainPage.mainRoom.value.devices)
        }
    }
}