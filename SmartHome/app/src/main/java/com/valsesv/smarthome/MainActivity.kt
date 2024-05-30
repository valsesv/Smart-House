package com.valsesv.smarthome

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.valsesv.smarthome.model.Room
import com.valsesv.smarthome.model.devices.Buld
import com.valsesv.smarthome.model.devices.Device
import com.valsesv.smarthome.model.MainPage
import com.valsesv.smarthome.model.Scenario
import com.valsesv.smarthome.ui.pages.Main
import com.valsesv.smarthome.ui.pages.Scenarios
import com.valsesv.smarthome.ui.pages.SettingScenarios

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint(
        "MutableCollectionMutableState", "UnusedMaterial3ScaffoldPaddingParameter",
        "UnrememberedMutableState"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val rooms = remember {
                mutableStateListOf<Room>()
            }

            val mainScenario = remember {
                mutableStateOf(Scenario(1, "Новый сценарий", "Описание", mutableStateListOf()))
            }

            val scenarios = remember {
                mutableStateListOf<Scenario>(
                    Scenario(1, "Сценарий", "", mutableStateListOf<Device>())
                )
            }

            val mainRoom = remember {
                mutableStateOf(
                    Room(
                        "Гостиная", mutableStateListOf(
                            Buld(1, "Лампочка в углу", )
                        ),
                        scenarios
                    )
                )
            }

            val mainPage by remember { mutableStateOf(MainPage(mainRoom, rooms)) }
            if (mainPage.rooms.size == 0) {
                mainPage.rooms.add(mainPage.mainRoom.value)
            }
            val isMainPage by remember { mutableStateOf(true) }
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = "main"
            ) {
                composable("main") {
                    Main(navController, mainPage)
                }
                composable("scen") {
                    Scenarios(navController, mainPage, mainScenario)
                }
                composable("settingScen") {
                    SettingScenarios(navController, mainPage, mainScenario)
                }
            }
        }
    }
}