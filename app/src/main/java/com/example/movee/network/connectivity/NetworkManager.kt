package com.example.movee.network.connectivity

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import androidx.lifecycle.LifecycleCoroutineScope

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
        scope.launchWhenStarted {
            NetworkStateManager.updateNetworkState(true)
        }
    }

    override fun onLost(network: Network) {
        super.onLost(network)
        scope.launchWhenStarted {
            NetworkStateManager.updateNetworkState(false)
        }
    }

    fun registerCallback() {
        connectivityManager.registerNetworkCallback(networkRequest, this@NetworkManager)
    }

    fun isConnected(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val isWifiEnabled =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                    ?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
            val isCellularEnabled =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                    ?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)

            isWifiEnabled != null || isCellularEnabled != null
        } else {
            connectivityManager.activeNetworkInfo?.isConnected ?: false
        }
    }
}