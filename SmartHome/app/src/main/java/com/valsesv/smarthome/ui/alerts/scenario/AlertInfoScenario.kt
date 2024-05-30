package com.valsesv.smarthome.ui.alerts.scenario

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.valsesv.smarthome.model.Scenario

@Composable
fun AlertInfoScenario(
    openDialog: MutableState<Boolean>,
    scenario: Scenario,
    scenarios: SnapshotStateList<Scenario>,
    navController: NavController,
    mainScenario: MutableState<Scenario>
) {
    val openSetting = remember { mutableStateOf(false) }
    AlertDialog(
        onDismissRequest = { openDialog.value = false },
        confirmButton = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                    onClick = {
                        openDialog.value = false
                        scenarios.remove(scenario)
                    }
                ) {
                    Text("Удалить", fontSize = 22.sp)
                }
                Button(
                    onClick = { openDialog.value = false }
                ) {
                    Text("OK", fontSize = 22.sp)
                }
            }
        },
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.width(190.dp),
                    softWrap = false,
                    overflow = TextOverflow.Ellipsis,
                    text = scenario.name,
                    textAlign = TextAlign.Center,
                )
                Button(
                    onClick = {
                        mainScenario.value = scenario
                        openSetting.value = true
                    }) {
                    Icon(
                        Icons.Filled.Settings,
                        contentDescription = "Настройка устройсва",
                    )
                }
            }
        },
        text = {
            if (openSetting.value) {
                mainScenario.value = scenario
                navController.navigate("settingScen")
            }
            Column {
                val messageName = remember { mutableStateOf(scenario.name) }
                val messageDescription = remember { mutableStateOf(scenario.description) }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = scenario.imgId),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                    )
                }
                Row {
                    Text(fontWeight = FontWeight.Bold, text = "Название: ")
                    Text(text = scenario.name)
                }
                Row(
                    modifier = Modifier.padding(vertical = 15.dp)
                ) {
                    Text(fontWeight = FontWeight.Bold, text = "Описание: ")
                    Text(text = scenario.description)
                }
            }
        }
    )
}