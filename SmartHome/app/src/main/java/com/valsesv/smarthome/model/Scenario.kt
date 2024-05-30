package com.valsesv.smarthome.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.valsesv.smarthome.R
import com.valsesv.smarthome.model.devices.Device

class Scenario(var id: Int, var name: String, var description: String, var devices: SnapshotStateList<Device>) {
    var modeDescription: MutableState<String> = mutableStateOf("Не задано")
    var switch: MutableState<Boolean> = mutableStateOf(false)
    var imgId = R.drawable.scen
}