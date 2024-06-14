package com.khorzon.mybraviaremote.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.khorzon.mybraviaremote.R
import com.khorzon.mybraviaremote.screens.CodingScreen
import com.khorzon.mybraviaremote.screens.RemoteScreen
import com.khorzon.mybraviaremote.screens.Screen
import com.khorzon.mybraviaremote.screens.StatusScreen
import com.khorzon.mybraviaremote.screens.screensInBottom
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun Navigation(navController: NavController, pd: PaddingValues) {
    NavHost(
        navController = navController as NavHostController,
        startDestination = Screen.BottomScreen.Remote.route
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



