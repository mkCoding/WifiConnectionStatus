package com.example.wificonnectionstatus.presentation

import android.net.ConnectivityManager
import android.net.Network
import androidx.lifecycle.ViewModel
import com.example.wificonnectionstatus.repository.WifiConnectionCheckRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class WifiConnectionStatusViewModel @Inject constructor(
    private val repo: WifiConnectionCheckRepo
) : ViewModel() {

    private val _isWifiConnected = MutableStateFlow(false)
    val isWifiConnected: StateFlow<Boolean> = _isWifiConnected

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            _isWifiConnected.value = true
        }

        override fun onLost(network: Network) {
            _isWifiConnected.value = false
        }
    }

    init {
        checkWifiConnectionStatus()
    }

    fun checkWifiConnectionStatus() {
        _isWifiConnected.value = repo.isWifiConnected()
        repo.getConnectivityManager()
            .registerDefaultNetworkCallback(networkCallback)
    }

    override fun onCleared() {
        repo.getConnectivityManager()
            .unregisterNetworkCallback(networkCallback)
        super.onCleared()
    }
}
