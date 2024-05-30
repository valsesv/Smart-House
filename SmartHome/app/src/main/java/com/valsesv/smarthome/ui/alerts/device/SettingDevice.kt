package com.valsesv.smarthome.ui.alerts.device

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.valsesv.smarthome.model.devices.Device

@Composable
fun SettingDevice(openSetting: MutableState<Boolean>, device: Device, devices: SnapshotStateList<Device>) {
    var newName = device.name
    AlertDialog(
        onDismissRequest = { openSetting.value = false },

        confirmButton = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        openSetting.value = false
                    }
                ) {
                    Text("Назад", fontSize = 22.sp)
                }
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = {
                        openSetting.value = false
                        device.name = newName
                    }
                ) {
                    Text("Сохранить", fontSize = 22.sp)
                }
            }
        },
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    modifier = Modifier.width(190.dp),
                    softWrap = false,
                    text = device.name,
                    textAlign = TextAlign.Center,
                    overflow = TextOverflow.Ellipsis
                )
                Button(
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                    onClick = {
                        openSetting.value = false
                        devices.remove(device)
                    }) {
                    Icon(
                        Icons.Filled.Delete,
                        contentDescription = "Удалить",
                    )
                }
            }
        },
        text = {
            Column {
                val messageName = remember { mutableStateOf(device.name) }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = device.imgId),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(8.dp)
                            .size(100.dp)
                            .clip(CircleShape)
                    )
                }
                Row {
                    TextField(
                        placeholder = { Text("Введите название девайса") },
                        value = messageName.value,
                        onValueChange = { newText ->
                            messageName.value = newText
                            newName = newText
                        })
                }
            }
        }
    )
}