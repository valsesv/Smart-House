package com.valsesv.smarthome.ui.components.scenario

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.valsesv.smarthome.model.Scenario

@SuppressLint("UnrememberedMutableState")
@Composable
fun ItemAddScenarioButton(scenarios: SnapshotStateList<Scenario>, navController: NavController, mainScenario: MutableState<Scenario>) {
    if(scenarios.isEmpty()){
        mainScenario.value = Scenario(1, "Новый сценарий", "Описание", mutableStateListOf())
    }
    else{
        mainScenario.value = Scenario(scenarios.last().id+1, "", "", mutableStateListOf())
    }
    val openDialog = remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .background(Color.Transparent),
        contentAlignment = Alignment.Center
    ) {
        Button(
            modifier = Modifier
                .padding(15.dp),
            onClick = {

                navController.navigate("settingScen")
                Log.d("Add", scenarios.toString())
            }) {
            Icon(
                Icons.Filled.Add,
                contentDescription = "Добавить новый сценарий",
                modifier = Modifier
                    .size(35.dp)
            )
        }
        if (openDialog.value) {
            //AlertAddDevices(openDialog, scenarios)
        }
    }
}