package com.valsesv.smarthome.ui.components.device

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.valsesv.smarthome.ui.alerts.device.AlertAddDevices
import com.valsesv.smarthome.model.devices.Device

@Composable
fun ItemAddDeviceButton(devices: SnapshotStateList<Device>) {
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
                openDialog.value = true;
                Log.d("Add", devices.toString())
            }) {
            Text(
                text = "Добавить устройство")
        }
        if (openDialog.value) {
            AlertAddDevices(openDialog, devices)
        }
    }
}

