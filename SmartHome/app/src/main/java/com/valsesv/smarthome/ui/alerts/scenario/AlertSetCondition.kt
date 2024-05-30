package com.valsesv.smarthome.ui.alerts.scenario

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.clock.ClockDialog
import com.maxkeppeler.sheets.clock.models.ClockConfig
import com.maxkeppeler.sheets.clock.models.ClockSelection
import com.valsesv.smarthome.model.Scenario
import com.valsesv.smarthome.model.devices.Device
import java.time.LocalTime.of

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AlertSetCondition(openDialog: MutableState<Boolean>, newScenario: MutableState<Scenario>, devices: SnapshotStateList<Device>) {
    val openTime = remember { mutableStateOf(false) }

    val selectedTime = remember { mutableStateOf(of(8, 20, 0)) }
    ClockDialog(
        state = rememberUseCaseState(
            visible = true,
            onCloseRequest = { openTime.value = false }),
        selection = ClockSelection.HoursMinutes { hours, minutes ->
            selectedTime.value = of(hours, minutes, 0)
            newScenario.value.modeDescription.value = selectedTime.value.toString()
        },
        config = ClockConfig(
            boundary = of(0, 0, 0)..of(12, 59, 0),
            defaultTime = selectedTime.value,
            is24HourFormat = true
        ),
    )
}