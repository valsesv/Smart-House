package com.valsesv.smarthome.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList

class MainPage(var mainRoom: MutableState<Room>, var rooms: SnapshotStateList<Room>) {
}