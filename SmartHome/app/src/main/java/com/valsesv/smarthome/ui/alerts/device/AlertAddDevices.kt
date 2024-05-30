package com.valsesv.smarthome.ui.alerts.device

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.valsesv.smarthome.model.devices.Buld
import com.valsesv.smarthome.model.devices.Switcher
import com.valsesv.smarthome.model.devices.Conditioner
import com.valsesv.smarthome.model.devices.Device

@Composable
fun AlertAddDevices(openDialog: MutableState<Boolean>, devices: SnapshotStateList<Device>) {
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
            val newId = if (devices.isEmpty()) {
                0
            } else {
                devices.last().id + 1
            }
            Column {
                Row {
                    DrawDeviceInAlert(devices, Buld(newId, "Лампочка"))
                    DrawDeviceInAlert(devices, Switcher(newId, "Выключатель"))
                    DrawDeviceInAlert(
                        devices,
                        Conditioner(newId, "Кондиционер")
                    )
                }
            }
        }
    )
}

@Composable
fun DrawDeviceInAlert(devices: SnapshotStateList<Device>, dv: Any) {
    val device = dv as Device;
    Image(
        painter = painterResource(id = device.imgId),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(8.dp)
            .size(64.dp)
            .clip(CircleShape)
            .clickable {
                devices.add(device)
            }
    )
}