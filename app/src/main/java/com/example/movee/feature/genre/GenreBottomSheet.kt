package com.example.movee.feature.genre

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movee.base.BaseBottomSheet
import com.example.movee.data.repository.genre.Genre
import com.example.movee.databinding.BottomSheetGenreBinding
import com.example.movee.databinding.LoadingBinding
import com.example.movee.network.ViewState
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class GenreBottomSheet(
    private  val onGenreSelected: (id: Int, name: String) -> Unit
) : BaseBottomSheet() {

    private val viewModel by viewModel<GenreViewModel>()
    private lateinit var binding: BottomSheetGenreBinding
    private lateinit var progressBinding: LoadingBinding
    private lateinit var adapter: GenreAdapter

    override fun networkListener(state: Boolean) {
        validateConnection(state)
    }

    override fun getLayoutView(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = BottomSheetGenreBinding.inflate(inflater, container, false)
        progressBinding = LoadingBinding.bind(binding.root)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonClose.setOnClickListener { dismiss() }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.genreResponse.collect { state ->
                    when (state) {
                        is ViewState.Loading -> {
                            progressBinding.progressBar.visibility = View.VISIBLE
                        }
                        is ViewState.Success -> {
                            progressBinding.progressBar.visibility = View.GONE
                            if (state.data.genres.isNotEmpty()) setupAdapter(state.data.genres)
                        }
                        is ViewState.Error -> {
                            progressBinding.progressBar.visibility = View.GONE
                        }
                    }
                }
            }
        }
    }

    private fun setupAdapter(list: List<Genre>) = with(binding) {
        adapter = GenreAdapter(list) { id, name ->
            this@GenreBottomSheet.onGenreSelected.invoke(id, name)
            dismiss()
        }

        recyclerGenre.apply {
            layoutManager = LinearLayoutManager(this@GenreBottomSheet.context)
            adapter = this@GenreBottomSheet.adapter
        }
    }

}