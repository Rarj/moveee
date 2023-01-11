package com.example.movee.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.movee.feature.NoInternetBottomSheet
import com.example.movee.network.connectivity.NetworkManager
import com.example.movee.network.connectivity.NetworkStateManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomSheet : BottomSheetDialogFragment() {

    private lateinit var networkManager: NetworkManager
    private val noConnection by lazy { NoInternetBottomSheet() }

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

        networkManager = NetworkManager(viewLifecycleOwner.lifecycleScope)
        context?.let { networkManager.initialize(it) }
    }

    override fun onStart() {
        super.onStart()
        networkManager.registerCallback()
        registerListener()
    }

    protected fun validateConnection(state: Boolean) {
        noConnection.isCancelable = false

        if (state) {
            if (noConnection.isVisible) noConnection.dismiss()
        } else {
            if (noConnection.isVisible.not()) {
                activity?.supportFragmentManager?.let {
                    noConnection.show(it, "NO_INTERNET_CONNECTION")
                }
            }
        }
    }

    private fun registerListener() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            NetworkStateManager.state.collect { state ->
                networkListener(state)
            }
        }
    }
}