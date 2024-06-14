package com.khorzon.mybraviaremote.components

import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.khorzon.mybraviaremote.screens.Screen
import com.khorzon.mybraviaremote.screens.screensInBottom

@Composable
fun BottomBar(currentRoute: String? = Screen.BottomScreen.Remote.route, controller: NavController) {
    BottomNavigation(
        modifier = Modifier.wrapContentSize(),
        backgroundColor = Color(0xFF90e0ef)
    ) {
        screensInBottom.forEach { item ->
            val isSelected = currentRoute == item.bRoute
            BottomNavigationItem(
                selected = currentRoute == item.bRoute,
                onClick = {
                    controller.navigate(item.bRoute)
                },
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.bTitle,
                        tint = if (isSelected) Color(0xFF03045e) else Color.White
                    )
                },
                label = {
                    Text(
                        text = item.bTitle,
                        color = if (isSelected) Color(0xFF03045e) else Color.White
                    )
                },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.Black,
            )
        }
    }
}