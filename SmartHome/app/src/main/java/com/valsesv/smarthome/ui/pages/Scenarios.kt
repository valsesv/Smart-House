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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.valsesv.smarthome.model.MainPage
import com.valsesv.smarthome.model.Scenario
import com.valsesv.smarthome.ui.components.scenario.ItemAddScenarioButton
import com.valsesv.smarthome.ui.components.scenario.ItemScenario
import com.valsesv.smarthome.ui.components.ItemTopBar

@Composable
fun Scenarios(
    navController: NavController,
    mainPage: MainPage,
    mainScenario: MutableState<Scenario>
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        scrimColor = Color.White,
        drawerState = drawerState,
        drawerContent = {
        },
    ) {
        Scaffold(
            topBar = { ItemTopBar(navController, mainPage, false) },
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
                        items(mainPage.mainRoom.value.scenarios) { scenario ->
                            ItemScenario(
                                scenario,
                                mainPage.mainRoom.value.scenarios,
                                navController,
                                mainScenario
                            )
                        }
                        item {
                            Spacer(modifier = Modifier.height(60.dp))
                        }
                    }

                }
                ItemAddScenarioButton(
                    mainPage.mainRoom.value.scenarios,
                    navController,
                    mainScenario
                )
            }
        }
    }
}
