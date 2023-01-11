package com.example.movee.feature

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.movee.base.BaseBottomSheet
import com.example.movee.databinding.BottomSheetNoInternetBinding

class NoInternetBottomSheet: BaseBottomSheet() {
    private lateinit var binding: BottomSheetNoInternetBinding

    override fun getLayoutView(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = BottomSheetNoInternetBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun networkListener(state: Boolean) {
        validateConnection(state)
    }
}