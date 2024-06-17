package com.khorzon.mybraviaremote

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.khorzon.mybraviaremote.components.BottomBar
import com.khorzon.mybraviaremote.components.DrawerContent
import com.khorzon.mybraviaremote.components.Navigation
import com.khorzon.mybraviaremote.components.TopBar
import kotlinx.coroutines.CoroutineScope

@Composable
fun MainView() {
    val controller: NavController = rememberNavController()
    val navBackStackEntry by controller.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope: CoroutineScope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(
                currentRoute = currentRoute,
                drawerState = drawerState,
                scope = scope
            )
        },
    ) {
        Scaffold(
            topBar = { TopBar(drawerState = drawerState, scope = scope) },
            bottomBar = { BottomBar(currentRoute = currentRoute, controller = controller) },
        ) {
            Navigation(navController = controller, pd = it)
        }
    }


}







