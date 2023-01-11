package com.example.movee.feature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.movee.databinding.BottomSheetNoInternetBinding
import com.example.movee.network.connectivity.NetworkManager
import com.example.movee.network.connectivity.NetworkStateManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class NoInternetBottomSheet: BottomSheetDialogFragment() {
    private lateinit var binding: BottomSheetNoInternetBinding
    private lateinit var networkManager: NetworkManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetNoInternetBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

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

    private fun registerListener() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            NetworkStateManager.state.collect { state ->
                if(state) dismissNow()
            }
        }
    }
}