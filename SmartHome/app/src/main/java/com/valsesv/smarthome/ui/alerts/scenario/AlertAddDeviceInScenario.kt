package com.valsesv.smarthome.ui.alerts.scenario

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.valsesv.smarthome.model.devices.Device
import com.valsesv.smarthome.ui.components.scenario.ItemDeviceInScenarioAlert

@Composable
fun AlertAddDeviceInScenario(
    openDialog: MutableState<Boolean>,
    devices: SnapshotStateList<Device>,
    scenarioDevices: SnapshotStateList<Device>
) {
    AlertDialog(
        onDismissRequest = { openDialog.value = false },
        confirmButton = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = { openDialog.value = false }
                ) {
                    Text("Назад", fontSize = 22.sp)
                }
            }
        },
        title = { Text(text = "Выберите устройство") },
        text = {
            LazyColumn(
            ) {
                items(devices) { device ->
                    Row(modifier = Modifier.clickable {
                        scenarioDevices.add(device)
                        Log.d("Temp:", device.name)
                    }) {
                        ItemDeviceInScenarioAlert(device)
                    }
                }
            }
        }
    )
}