package com.khorzon.mybraviaremote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.khorzon.mybraviaremote.ui.theme.MyBraviaRemoteTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dynamicBaseUrl = "http://192.168.233.5/sony/"
        RetrofitClient.setBaseUrl(dynamicBaseUrl)
        SoapClient.setBaseUrl(dynamicBaseUrl)

//        val apiService = RetrofitClient.getService()

        setContent {
            MyBraviaRemoteTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    MainView()
                }
            }
        }
    }
}