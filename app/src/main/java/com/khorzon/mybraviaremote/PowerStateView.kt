package com.khorzon.mybraviaremote

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.khorzon.mybraviaremote.helpers.irccCodes
import com.khorzon.mybraviaremote.ui.theme.MyBraviaRemoteTheme

@Composable
fun PowerStateView() {

    val powerStateViewModel: PowerViewModel = viewModel()
    val powerState by powerStateViewModel.powerState
    Column(
        modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Button(
                onClick = { powerStateViewModel.sendSoapRequest(irccCodes["Options"] ?: "") },
                shape = RoundedCornerShape(0.dp),
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = null,
                    Modifier.size(48.dp)
                )
            }
            Button(
                onClick = { powerStateViewModel.setIsOn(!powerState.isOn!!) },
                shape = RoundedCornerShape(0.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp),
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_power_settings_new_24),
                    contentDescription = null
                )
            }

        }
        //        2nd line
        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                shape = RoundedCornerShape(0.dp),
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp)
            ) {}
            Button(
                onClick = { powerStateViewModel.sendSoapRequest(irccCodes["Up"] ?: "") },
                shape = RoundedCornerShape(0.dp),
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp),
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowUp,
                    contentDescription = null,
                    Modifier.size(48.dp)
                )
            }
            Button(
                onClick = { },
                shape = RoundedCornerShape(0.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp),
            ) {}

        }
        //        3rd line
        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Button(
                onClick = { powerStateViewModel.sendSoapRequest(irccCodes["Left"] ?: "") },
                shape = RoundedCornerShape(0.dp),
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = null,
                    Modifier.size(48.dp)
                )
            }
            Button(
                onClick = { powerStateViewModel.sendSoapRequest(irccCodes["Confirm"] ?: "") },
                shape = RoundedCornerShape(0.dp),
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp),
            ) {
                Icon(
                    painterResource(id = R.drawable.outline_point_scan_24),
                    contentDescription = null,
                    Modifier.size(48.dp)
                )
            }
            Button(
                onClick = { powerStateViewModel.sendSoapRequest(irccCodes["Right"] ?: "") },
                shape = RoundedCornerShape(0.dp),
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp),
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = null,
                    Modifier.size(48.dp)
                )
            }

        }
        //        4th line
        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Button(
                onClick = { },
                shape = RoundedCornerShape(0.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp)
            ) {}
            Button(
                onClick = { powerStateViewModel.sendSoapRequest(irccCodes["Down"] ?: "") },
                shape = RoundedCornerShape(0.dp),
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp),
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    Modifier.size(48.dp)
                )
            }
            Button(
                onClick = {},
                shape = RoundedCornerShape(0.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp),
            ) {}

        }
        //        5th line
        Spacer(modifier = Modifier.height(48.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Button(
                onClick = { powerStateViewModel.sendSoapRequest(irccCodes["Back"] ?: "") },
                shape = RoundedCornerShape(0.dp),
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    Modifier.size(48.dp)
                )
            }
            Button(
                onClick = { powerStateViewModel.sendSoapRequest(irccCodes["Play"] ?: "") },
                shape = RoundedCornerShape(0.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xff80ed99)),
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp),
            ) {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = null,
                    Modifier.size(48.dp)
                )
            }
            Button(
                onClick = { powerStateViewModel.sendSoapRequest(irccCodes["Pause"] ?: "") },
                shape = RoundedCornerShape(0.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp)
            ) {
                Icon(painterResource(id = R.drawable.baseline_pause_24), contentDescription = null)
            }

        }
    }
}

@Preview
@Composable
fun PowerStatePreview() {
    MyBraviaRemoteTheme {
        PowerStateView()
    }
}