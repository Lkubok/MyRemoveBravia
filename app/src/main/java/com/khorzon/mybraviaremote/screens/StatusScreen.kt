package com.khorzon.mybraviaremote.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.khorzon.mybraviaremote.PowerViewModel
import com.khorzon.mybraviaremote.R

@Composable
fun StatusScreen() {
    val powerStateViewModel: PowerViewModel = viewModel()
    val networkStatus = powerStateViewModel.networkSettings
    val powerStatus = powerStateViewModel.powerState
    val volumeStatus = powerStateViewModel.volumeState

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)

        ) {
            Text(
                color = Color.Black,
                text = stringResource(id = R.string.power_status),
            )
            Text(
                color = Color.Black,
                text = if (powerStatus.value.isOn == true) stringResource(id = R.string.power_on) else stringResource(
                    id = R.string.power_off
                )
            )
        }
        Divider()
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)

        ) {
            Text(
                color = Color.Black,
                text = stringResource(id = R.string.volume_level),
            )
            Text(
                color = Color.Black,
                text = volumeStatus.value.toString()
            )
        }
        Divider()
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)

        ) {
            Text(
                color = Color.Black,
                text = stringResource(id = R.string.ip_address),
            )
            Text(
                color = Color.Black,
                text = networkStatus.value[0].ipAddrV4
            )
        }
        Divider()
    }
}