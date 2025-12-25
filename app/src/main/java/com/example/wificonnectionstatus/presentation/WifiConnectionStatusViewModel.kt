package com.example.wificonnectionstatus.presentation

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wificonnectionstatus.repository.WifiConnectionCheckRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WifiConnectionStatusViewModel @Inject constructor(
    private val repo: WifiConnectionCheckRepo
) : ViewModel() {

    private val _isMobileDataON = MutableStateFlow(false)
    val isMobileDataON: StateFlow<Boolean> = _isMobileDataON

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            _isMobileDataON.value = true
        }

        override fun onLost(network: Network) {
            _isMobileDataON.value = false
        }
    }

    init {
        checkMobileDataStatus()
    }

    fun checkMobileDataStatus() {
        _isMobileDataON.value = repo.isMobileDataConnected()
        repo.getConnectivityManager()
            .registerDefaultNetworkCallback(networkCallback)
    }

    override fun onCleared() {
        repo.getConnectivityManager()
            .unregisterNetworkCallback(networkCallback)
        super.onCleared()
    }
}
