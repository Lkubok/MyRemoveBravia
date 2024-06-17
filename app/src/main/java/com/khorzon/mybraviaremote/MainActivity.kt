package com.khorzon.mybraviaremote

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.khorzon.mybraviaremote.ui.theme.MyBraviaRemoteTheme
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.internal.NoOpContinuation.context


class MainActivity : ComponentActivity() {

    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
//
//    val EXAMPLE_COUNTER = intPreferencesKey("example_counter")
//    val exampleCounterFlow: Flow<Int> = context.dataStore.data
//        .map { preferences ->
//            // No type safety.
//            preferences[EXAMPLE_COUNTER] ?: 0
//        }
//    suspend fun incrementCounter() {
//        Context.dataStore.edit { settings ->
//            val currentCounterValue = settings[EXAMPLE_COUNTER] ?: 0
//            settings[EXAMPLE_COUNTER] = currentCounterValue + 1
//        }
//    }

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