package com.example.movee.network.connectivity

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.util.Log
import androidx.lifecycle.LifecycleCoroutineScope
import kotlinx.coroutines.launch

class NetworkManager(private val scope: LifecycleCoroutineScope) :
    ConnectivityManager.NetworkCallback() {
    private lateinit var networkRequest: NetworkRequest
    private lateinit var connectivityManager: ConnectivityManager
    private lateinit var context: Context

    fun initialize(context: Context) {
        this.context = context
        networkRequest =
            NetworkRequest.Builder().addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR).build()
        connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    override fun onAvailable(network: Network) {
        super.onAvailable(network)
        Log.e("onAvailable: ", true.toString())
        scope.launch {
            NetworkStateManager.updateNetworkState(true)
        }
    }

    override fun onLost(network: Network) {
        super.onLost(network)
        Log.e("onLost: ", true.toString())
        scope.launch {
            NetworkStateManager.updateNetworkState(false)
        }
    }

    fun registerCallback() {
        connectivityManager.registerNetworkCallback(networkRequest, this@NetworkManager)
    }
}