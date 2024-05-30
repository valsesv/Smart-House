package com.valsesv.smarthome.model

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.valsesv.smarthome.model.devices.Device

class Room(var name: String, var devices: SnapshotStateList<Device>, var scenarios: SnapshotStateList<Scenario>) {
}