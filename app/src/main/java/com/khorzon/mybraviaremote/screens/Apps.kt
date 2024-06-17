package com.khorzon.mybraviaremote.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.khorzon.mybraviaremote.PowerViewModel
import com.khorzon.mybraviaremote.components.AppItem

@Composable
fun AppsScreen() {
    val powerStateViewModel: PowerViewModel = viewModel()
    val apps by powerStateViewModel.apps
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier.padding(bottom = 60.dp)
    ) {
        items(apps) { app ->
            AppItem(app, onItemClick = {
                powerStateViewModel.setActiveApp(app.uri)
            })
        }
    }
}