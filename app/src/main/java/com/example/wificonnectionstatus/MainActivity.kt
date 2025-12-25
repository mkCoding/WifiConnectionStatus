package com.example.wificonnectionstatus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.wificonnectionstatus.presentation.WifiConnectionStatusScreen
import com.example.wificonnectionstatus.presentation.WifiConnectionStatusViewModel
import com.example.wificonnectionstatus.ui.theme.WifiConnectionStatusTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WifiConnectionStatusTheme {

                val viewModel: WifiConnectionStatusViewModel = hiltViewModel()
                val state by viewModel.isMobileDataON.collectAsState()

//                LaunchedEffect(Unit) {
//                    viewModel.checkMobileDataStatus()
//                }
                WifiConnectionStatusScreen(state)

            }
        }
    }
}

