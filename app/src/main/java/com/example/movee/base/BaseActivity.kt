package com.example.movee.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.movee.feature.NoInternetBottomSheet
import com.example.movee.network.connectivity.NetworkManager
import com.example.movee.network.connectivity.NetworkStateManager
import kotlinx.coroutines.flow.collectLatest

abstract class BaseActivity : AppCompatActivity() {

    private lateinit var networkManager: NetworkManager
    private val noConnection by lazy { NoInternetBottomSheet() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bindLayout())

        networkManager = NetworkManager(lifecycleScope)
        networkManager.initialize(this)
    }

    override fun onStart() {
        super.onStart()
        networkManager.registerCallback()
        registerListener()

        validateConnection(networkManager.isConnected())
    }

    protected fun validateConnection(state: Boolean) {
        noConnection.isCancelable = false

        if (state) {
            if (noConnection.isVisible) noConnection.dismiss()
        } else {
            if (noConnection.isVisible.not()) {
                noConnection.show(supportFragmentManager, "NO_INTERNET_CONNECTION")
            }
        }
    }

    abstract fun bindLayout(): View
    abstract fun networkListener(state: Boolean)

    private fun registerListener() {
        lifecycleScope.launchWhenStarted {
            NetworkStateManager.state.collectLatest { state ->
                networkListener(state)
            }
        }
    }

}