package com.khorzon.mybraviaremote

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun PowerStateView() {

    val powerStateViewModel: PowerViewModel = viewModel()
    val powerState by powerStateViewModel.powerState
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {

        Text(text = "TV is: ${powerState.isOn}")
        Text(text = "Any error occurred? : ${powerState.error}")
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {

                Spacer(modifier = Modifier.height(128.dp))
                Button(onClick = { powerStateViewModel.setIsOn(true) }) {
                    Text(text = "Turn on")
                }
                Spacer(modifier = Modifier.height(64.dp))
                Button(onClick = { powerStateViewModel.setIsOn(false) }) {
                    Text(text = "Turn off")
                }
            }
        }
    }
}