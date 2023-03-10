package com.example.movee

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.movee.base.BaseActivity
import com.example.movee.databinding.ActivityMainBinding
import com.example.movee.feature.detail.DetailMovieActivity.Companion.gotoDetailActivity
import com.example.movee.feature.discovermovie.DiscoverMovieAdapter
import com.example.movee.feature.discovermovie.DiscoverMovieViewModel
import com.example.movee.feature.genre.GenreBottomSheet
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    private val discoverMovieViewModel by viewModel<DiscoverMovieViewModel>()
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: DiscoverMovieAdapter

    override fun bindLayout(): View {
        binding = ActivityMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun networkListener(state: Boolean) {
        validateConnection(state)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupUI()
        setupAdapter()
        setupObserver()
    }

    private fun setupAdapter() {
        adapter = DiscoverMovieAdapter { id -> this.gotoDetailActivity(id) }
        binding.recyclerMovies.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }
    }

    private fun setupUI() = with(binding) {
        imageViewState.load(R.drawable.ic_empty_state)
        textViewState.text = getString(R.string.genre_not_picked_yet)

        dropDownPickGenre.setOnClickListener { showGenrePicker() }
        buttonPickGenre.setOnClickListener { showGenrePicker() }
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                discoverMovieViewModel.discoverMovie.collect { pagingData ->
                    binding.containerViewState.visibility =
                        if (discoverMovieViewModel.isGenreHasSelected()) View.GONE else View.VISIBLE

                    if (pagingData == null) {
                        adapter.submitData(lifecycle, PagingData.empty())
                    } else {
                        binding.recyclerMovies.visibility = View.VISIBLE
                        adapter.submitData(lifecycle, pagingData)
                    }
                }
            }
        }
    }

    private fun showGenrePicker() {
        val modal = GenreBottomSheet(::discoverMovies)
        modal.show(supportFragmentManager, "GENRE_PICKER")
    }

    private fun discoverMovies(id: Int, name: String) {
        binding.dropDownPickGenre.text = name
        discoverMovieViewModel.discoverMovie(id)
    }
}