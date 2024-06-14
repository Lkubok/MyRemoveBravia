package com.khorzon.mybraviaremote.screens

import androidx.annotation.DrawableRes
import com.khorzon.mybraviaremote.R

sealed class Screen(val title: String, val route: String) {
    sealed class DrawerScreen(val dTitle: String, val dRoute: String, @DrawableRes val icon: Int) :
        Screen(dTitle, dRoute) {
        object Account : DrawerScreen(
            dTitle = "Settings",
            dRoute = "settings",
            R.drawable.ic_settings
        )

        object Info : DrawerScreen(
            dTitle = "Info",
            dRoute = "info",
            R.drawable.ic_info
        )
    }


    sealed class BottomScreen(val bTitle: String, val bRoute: String, @DrawableRes val icon: Int) :
        Screen(bTitle, bRoute) {

        object Remote : BottomScreen(
            bTitle = "Remote",
            bRoute = "remote",
            R.drawable.ic_remote
        )

        object Status : BottomScreen(
            bTitle = "Status",
            bRoute = "status",
            R.drawable.ic_status
        )

        object Coding : BottomScreen(
            bTitle = "Coding",
            bRoute = "coding",
            R.drawable.ic_code
        )
    }
}

val screensInBottom = listOf(
    Screen.BottomScreen.Remote,
    Screen.BottomScreen.Status,
    Screen.BottomScreen.Coding
)

val screensInDrawer = listOf(
    Screen.DrawerScreen.Account,
    Screen.DrawerScreen.Info
)