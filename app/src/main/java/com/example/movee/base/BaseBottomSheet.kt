package com.example.movee.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.movee.network.connectivity.NetworkManager
import com.example.movee.network.connectivity.NetworkStateManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.flow.collectLatest

abstract class BaseBottomSheet: BottomSheetDialogFragment() {

    private lateinit var networkManager: NetworkManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return getLayoutView(inflater, container)
    }

    abstract fun getLayoutView(inflater: LayoutInflater, container: ViewGroup?): View
    abstract fun networkListener(state: Boolean)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        networkManager = NetworkManager(lifecycleScope)
        context?.let { networkManager.initialize(it) }
    }

    override fun onStart() {
        super.onStart()
        networkManager.registerCallback()
        registerListener()
    }

    private fun registerListener() {
        lifecycleScope.launchWhenStarted {
            NetworkStateManager.state.collectLatest { state ->
                networkListener(state)
            }
        }
    }
}