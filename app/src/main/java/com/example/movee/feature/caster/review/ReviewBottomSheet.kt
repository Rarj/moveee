package com.example.movee.feature.caster.review

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.movee.R
import com.example.movee.base.BaseBottomSheet
import com.example.movee.databinding.BottomSheetReviewBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReviewBottomSheet(private val movieID: Int) : BaseBottomSheet() {

    private val viewModel by viewModel<ReviewViewModel>()
    private lateinit var binding: BottomSheetReviewBinding
    private lateinit var adapter: ReviewAdapter
    override fun getLayoutView(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = BottomSheetReviewBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun networkListener(state: Boolean) {
        validateConnection(state)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.reviews(movieID)

        binding.buttonClose.setOnClickListener { dismiss() }
        setupAdapter()
        setupObserver()
    }

    private fun setupAdapter() {
        adapter = ReviewAdapter()
        binding.recyclerReview.apply {
            layoutManager = LinearLayoutManager(this@ReviewBottomSheet.context)
            adapter = this@ReviewBottomSheet.adapter
        }

        adapter.addLoadStateListener { _ ->
            if (adapter.snapshot().isEmpty()) {
                setEmptyState()
            } else {
                hideEmptyState()
            }
        }
    }

    private fun hideEmptyState() {
        binding.containerEmptyState.visibility = View.GONE
    }

    private fun setEmptyState() = with(binding) {
        imageViewState.load(R.drawable.ic_empty_state)

        buttonCloseEmpty.setOnClickListener { dismiss() }

        containerEmptyState.visibility = View.VISIBLE
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.reviewMovie.collect { pagingData ->
                    pagingData?.let {
                        adapter.submitData(it)
                    }
                }
            }
        }
    }
}