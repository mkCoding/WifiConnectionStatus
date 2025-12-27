package com.example.wificonnectionstatus.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun WifiConnectionStatusScreen(
    wifiConnectedState:Boolean
){
    Column(
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding()
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            fontSize = 40.sp,
            text = when(wifiConnectedState){
                true -> "Online"
                false -> "Offline"
            }
        )
    }



}


@Preview(showBackground = true)
@Composable
fun WifiConnectionStatusScreenPreview(){
    WifiConnectionStatusScreen(false)
}