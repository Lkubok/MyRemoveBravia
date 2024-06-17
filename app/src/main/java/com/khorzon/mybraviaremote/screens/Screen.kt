package com.khorzon.mybraviaremote.screens

import androidx.annotation.DrawableRes
import com.khorzon.mybraviaremote.R

sealed class Screen(val title: String, val route: String) {
    sealed class DrawerScreen(val dTitle: String, val dRoute: String, @DrawableRes val icon: Int) :
        Screen(dTitle, dRoute) {
        data object Account : DrawerScreen(
            dTitle = "Settings",
            dRoute = "settings",
            R.drawable.ic_settings
        )

        data object Info : DrawerScreen(
            dTitle = "Info",
            dRoute = "info",
            R.drawable.ic_info
        )
    }


    sealed class BottomScreen(val bTitle: String, val bRoute: String, @DrawableRes val icon: Int) :
        Screen(bTitle, bRoute) {

        data object Remote : BottomScreen(
            bTitle = "Remote",
            bRoute = "remote",
            R.drawable.ic_remote
        )

        data object Status : BottomScreen(
            bTitle = "Status",
            bRoute = "status",
            R.drawable.ic_status
        )

        data object Coding : BottomScreen(
            bTitle = "Coding",
            bRoute = "coding",
            R.drawable.ic_code
        )
        data object Applications : BottomScreen(
            bTitle = "Applications",
            bRoute = "applications",
            R.drawable.ic_apps
        )
    }
}

val screensInBottom = listOf(
    Screen.BottomScreen.Remote,
    Screen.BottomScreen.Applications,
    Screen.BottomScreen.Status,
    Screen.BottomScreen.Coding
)

val screensInDrawer = listOf(
    Screen.DrawerScreen.Account,
    Screen.DrawerScreen.Info
)