package com.valsesv.smarthome.ui.components.scenarioSettings

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.valsesv.smarthome.model.Scenario

@Composable
fun ItemConfirmScenarioButton(
    scenarios: SnapshotStateList<Scenario>,
    navController: NavController,
    newScenario: MutableState<Scenario>
) {
    Box(
        modifier = Modifier
            .background(Color.Transparent),
        contentAlignment = Alignment.Center
    ) {
        Row {
            Button(
                modifier = Modifier
                    .padding(15.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                onClick = {
                    navController.navigate("scen")
                    scenarios.remove(newScenario.value)
                }
            ) {
                Text("Удалить", fontSize = 22.sp)
            }
            Button(
                modifier = Modifier
                    .padding(15.dp),
                onClick = {
                    for (scen in scenarios) {
                        if (scen.id == newScenario.value.id) {
                            navController.navigate("scen")
                            return@Button
                        }
                    }
                    scenarios.add(newScenario.value)
                    navController.navigate("scen")
                    Log.d("Add", scenarios.toString())
                }) {
                Text("Сохранить", fontSize = 22.sp)
            }
        }
    }
}