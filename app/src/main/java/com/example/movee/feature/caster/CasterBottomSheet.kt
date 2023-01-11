package com.example.movee.feature.caster

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movee.base.BaseBottomSheet
import com.example.movee.data.repository.caster.CasterResponse
import com.example.movee.databinding.BottomSheetCasterBinding
import com.example.movee.databinding.LoadingBinding
import com.example.movee.network.ViewState
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CasterBottomSheet(private val movieId: Int) : BaseBottomSheet() {

    private val viewModel by viewModel<CasterViewModel>()
    private lateinit var binding: BottomSheetCasterBinding
    private lateinit var progressBinding: LoadingBinding

    override fun getLayoutView(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = BottomSheetCasterBinding.inflate(inflater, container, false)
        progressBinding = LoadingBinding.bind(binding.root)
        return binding.root
    }

    override fun networkListener(state: Boolean) {
        validateConnection(state)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.detailCasters(movieId)

        binding.buttonClose.setOnClickListener { dismiss() }
        setupObserver()
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.casters.collect { state ->
                    when (state) {
                        is ViewState.Loading -> {
                            progressBinding.progressBar.visibility = View.VISIBLE
                        }

                        is ViewState.Success -> {
                            progressBinding.progressBar.visibility = View.GONE
                            setupCasterUi(state.data)
                        }

                        is ViewState.Error -> {
                            progressBinding.progressBar.visibility = View.GONE
                        }
                    }
                }
            }
        }
    }

    private fun setupCasterUi(casterResponse: CasterResponse) = with(binding) {
        val adapter = CasterAdapter(casterResponse.cast)
        recyclerCaster.apply {
            layoutManager = LinearLayoutManager(this@CasterBottomSheet.context)
            this.adapter = adapter
        }
    }

}