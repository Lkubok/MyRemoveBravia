package com.khorzon.mybraviaremote.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.khorzon.mybraviaremote.screens.screensInDrawer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun DrawerContent(currentRoute: String?, drawerState: DrawerState, scope: CoroutineScope)
{
    ModalDrawerSheet(
        drawerContainerColor = MaterialTheme.colors.background
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(16.dp)
        ) {
            items(screensInDrawer) { item ->
                DrawerItem(selected = currentRoute == item.dRoute, item = item) {
                    scope.launch {
                        drawerState.apply {
                            if (isClosed) open() else close()
                        }
                    }
                }
            }
        }
    }
}