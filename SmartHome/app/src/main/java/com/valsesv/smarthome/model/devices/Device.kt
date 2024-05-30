package com.valsesv.smarthome.model.devices

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.valsesv.smarthome.R

open class Device(var id: Int, var name: String)  {
    var switch: MutableState<Boolean> = mutableStateOf(false)
    open var imgId = R.drawable.lamp
}