package com.valsesv.smarthome.ui.alerts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.valsesv.smarthome.model.Room

@Composable
fun AlertAddRoom(open: MutableState<Boolean>, rooms: MutableList<Room>) {
    var newRoom: Room
    var newName = "Кухня"

    AlertDialog(
        onDismissRequest = { open.value = false },
        confirmButton = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        open.value = false
                        newRoom = Room(newName, mutableStateListOf(),mutableStateListOf())
                        rooms.add(newRoom)
                    }
                ) {
                    Text("Добавить", fontSize = 22.sp)
                }
            }
        },
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Новая комната", textAlign = TextAlign.Center)
                Button(
                    onClick = {
                        open.value = false
                    }) {
                    Icon(
                        Icons.Filled.ArrowBack,
                        contentDescription = "Выход",
                    )
                }
            }
        },
        text = {
            Column {
                val messageName = remember { mutableStateOf(newName) }
                Text(text = "Название дома:", fontSize=20.sp)
                Row {
                    TextField(
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