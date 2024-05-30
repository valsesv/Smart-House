package com.valsesv.smarthome.ui.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.valsesv.smarthome.model.MainPage
import com.valsesv.smarthome.model.Scenario
import com.valsesv.smarthome.ui.alerts.scenario.AlertAddDeviceInScenario
import com.valsesv.smarthome.ui.components.scenario.ItemSaveScenarioButton
import com.valsesv.smarthome.ui.components.scenario.ScenarioCondition
import com.valsesv.smarthome.ui.components.scenario.ScenarioDevice
import com.valsesv.smarthome.ui.components.scenario.SettingScenarioTopBar

@Composable
fun SettingScenarios(
    navController: NavController,
    mainPage: MainPage,
    mainScenario: MutableState<Scenario>
) {

    val newScenario = remember {
        mutableStateOf(mainScenario.value)
    }
    val openAddDevice = remember {
        mutableStateOf(false)
    }
    Scaffold(
        topBar = { SettingScenarioTopBar(navController) },
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
                    modifier = Modifier
                        .weight(5f)
                        .padding(8.dp, 0.dp)
                ) {
                    item {
                        Column(
                            modifier = Modifier
                                .weight(5f)
                                .padding(0.dp, 20.dp, 0.dp, 0.dp)
                        ) {
                            val messageName = remember { mutableStateOf(newScenario.value.name) }
                            Text(text = "Название сценария:", fontSize = 20.sp)
                            Row {
                                TextField(
                                    value = messageName.value,
                                    onValueChange = { newText ->
                                        messageName.value = newText
                                        newScenario.value.name = newText
                                    },
                                    shape = RoundedCornerShape(10.dp)
                                )
                            }

                        }
                    }
                    item {
                        Column(
                            modifier = Modifier
                                .weight(5f)
                                .padding(0.dp, 20.dp, 0.dp, 0.dp)
                        ) {
                            val messageName = remember { mutableStateOf(newScenario.value.name) }
                            Text(text = "Описание сценария:", fontSize = 20.sp)
                            Row {
                                TextField(
                                    value = messageName.value,
                                    onValueChange = { newText ->
                                        messageName.value = newText
                                        newScenario.value.description = newText
                                    },
                                    shape = RoundedCornerShape(10.dp)
                                )
                            }

                        }
                    }
                    item {
                        Column(
                            modifier = Modifier
                                .weight(5f)
                                .padding(0.dp, 20.dp, 0.dp, 0.dp)
                        ) {
                            ScenarioCondition(newScenario, mainPage.mainRoom.value.devices)
                        }
                    }
                    item {
                        Column(
                            modifier = Modifier
                                .weight(5f)
                                .padding(0.dp, 20.dp, 0.dp, 0.dp)
                        ) {
                            Text(text = "Действия:", fontSize = 20.sp)
                        }
                    }
                    items(newScenario.value.devices) { device ->
                        ScenarioDevice(device, newScenario.value.devices)
                    }
                    item {
                        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                            .padding(10.dp)
                            .clickable {
                                openAddDevice.value = true
                            }) {
                            Icon(
                                Icons.Default.Add,
                                contentDescription = "Действие",
                                tint = Color(0xFF6650a4)
                            )
                            Text(
                                color = Color(0xFF6650a4),
                                text = "Действие",
                                fontSize = 18.sp
                            )
                        }
                    }
                    item {
                        Spacer(modifier = Modifier.height(60.dp))
                    }
                }

            }
            if (openAddDevice.value) {
                AlertAddDeviceInScenario(
                    openAddDevice,
                    mainPage.mainRoom.value.devices,
                    newScenario.value.devices
                )
            }
            ItemSaveScenarioButton(mainPage.mainRoom.value.scenarios, navController, newScenario)
        }
    }
}