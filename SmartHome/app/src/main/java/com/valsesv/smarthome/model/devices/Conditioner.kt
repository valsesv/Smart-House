package com.valsesv.smarthome.model.devices

import com.valsesv.smarthome.R

class Conditioner(id: Int, name: String) : Device(id, name){
    override var imgId = R.drawable.conditioner
    var temperature: Float = 25f
    val tempMax: Float = 30f
    val tempMin: Float = 16f
    val tempDescription = "Температура воздуха:"
}