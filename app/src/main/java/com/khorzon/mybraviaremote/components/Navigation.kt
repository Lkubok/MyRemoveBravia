package com.khorzon.mybraviaremote.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.khorzon.mybraviaremote.screens.CodingScreen
import com.khorzon.mybraviaremote.screens.RemoteScreen
import com.khorzon.mybraviaremote.screens.Screen
import com.khorzon.mybraviaremote.screens.StatusScreen

@Composable
fun Navigation(navController: NavController, pd: PaddingValues) {
    NavHost(
        navController = navController as NavHostController,
        startDestination = Screen.BottomScreen.Remote.route,
        modifier = Modifier.padding(top =60.dp)
    ) {
        composable(Screen.BottomScreen.Remote.route) {
            RemoteScreen()
        }
        composable(Screen.BottomScreen.Status.route) {
            StatusScreen()
        }
        composable(Screen.BottomScreen.Coding.route) {
            CodingScreen()
        }
    }

}



