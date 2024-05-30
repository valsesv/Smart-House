package com.valsesv.smarthome.ui.components.scenario

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.valsesv.smarthome.model.devices.Device

@Composable
fun ScenarioDevice(item: Device, devices: SnapshotStateList<Device>) {
    Card(
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val openDialog = remember { mutableStateOf(false) }
            Row(
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .clickable {
                        openDialog.value = true;
                    },
                verticalAlignment = Alignment.CenterVertically,
            ) {
                if (openDialog.value) {
                    //AlertRenameDevice(openDialog = openDialog, device = item, devices, true)
                }
                Image(
                    painter = painterResource(id = item.imgId),
                    contentDescription = "Scenario image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(8.dp)
                        .size(64.dp)
                        .clip(CircleShape)
                )
                Column(
                    modifier = Modifier.padding(5.dp)
                ) {
                    Text(fontWeight = FontWeight.Bold, text = item.name)
                }
            }
            val checkedState = remember { mutableStateOf(item.switch) }
            Log.d("Switchhhh", item.switch.toString() + "and " + checkedState.value.toString())
            Switch(
                modifier = Modifier.padding(5.dp),
                checked = item.switch.value,
                onCheckedChange = {
                    item.switch.value = it
                }
            )
        }
    }
}