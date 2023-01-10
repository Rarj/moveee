package com.example.movee.feature.detail

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.movee.BuildConfig
import com.example.movee.R
import com.example.movee.data.repository.caster.CasterResponse
import com.example.movee.data.repository.detail.DetailMovieResponse
import com.example.movee.databinding.ActivityDetailMovieBinding
import com.example.movee.feature.caster.CasterAdapter
import com.example.movee.feature.caster.CasterBottomSheet
import com.example.movee.feature.caster.CasterViewModel
import com.example.movee.feature.caster.review.ReviewBottomSheet
import com.example.movee.feature.video.VideoActivity
import com.example.movee.feature.video.VideoViewModel
import com.example.movee.formatDate
import com.example.movee.network.ViewState
import com.google.android.material.chip.Chip
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class DetailMovieActivity : AppCompatActivity() {

    private val videoViewModel by viewModel<VideoViewModel>()
    private val viewModel by viewModel<DetailMovieViewModel>()
    private val casterViewModel by viewModel<CasterViewModel>()
    private lateinit var binding: ActivityDetailMovieBinding
    private val movieId by lazy { intent.getIntExtra(MOVIE_ID, 0) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.detailMovie(movieId)
        casterViewModel.casters(movieId)

        binding.casterLabel.setOnClickListener {
            val modal = CasterBottomSheet(movieId)
            modal.show(supportFragmentManager, "CASTER_BOTTOM_SHEET")
        }
        binding.buttonTrailer.setOnClickListener {
            lifecycleScope.launch {
                videoViewModel.trailer(movieId)
            }
        }

        lifecycleScope.launchWhenStarted {
            videoViewModel.video.collectLatest { state ->
                when (state) {
                    is ViewState.Loading -> {
                        binding.progress.visibility = View.VISIBLE
                        binding.buttonTrailer.text = ""
                    }
                    is ViewState.Success -> {
                        val intent = Intent(this@DetailMovieActivity, VideoActivity::class.java)
                        intent.putExtra("youtube_key", state.data?.key)
                        startActivity(intent)

                        binding.progress.visibility = View.GONE
                        binding.buttonTrailer.text = "Watch Trailer"
                    }

                    is ViewState.Error -> {
                        binding.progress.visibility = View.GONE
                    }
                }
            }
        }

        setupObserver()
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.detailMovie.collect { state ->
                    when (state) {
                        is ViewState.Loading -> {}
                        is ViewState.Success -> {
                            setupUI(state.data)
                        }

                        is ViewState.Error -> {}
                    }
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                casterViewModel.casters.collect { state ->
                    when (state) {
                        is ViewState.Loading -> {}
                        is ViewState.Success -> setupCasterUi(state.data)
                        is ViewState.Error -> {}
                    }
                }
            }
        }
    }

    private fun setupCasterUi(casterResponse: CasterResponse) = with(binding) {
        val adapter = CasterAdapter(casterResponse.cast)
        recyclerCaster.apply {
            layoutManager = LinearLayoutManager(this@DetailMovieActivity)
            this.adapter = adapter
        }
    }

    private fun setupUI(movie: DetailMovieResponse) = with(binding) {
        toolbar.apply {
            title = movie.title
            setNavigationOnClickListener { finish() }
        }
        backdrop.load(buildString {
            append(BuildConfig.BASE_URL_IMAGE)
            append(movie.backdropPath)
        })
        releaseDate.text = formatDate(movie.releaseDate)
        rating.rating = (movie.rating * 5 / 10).toFloat()
        movie.genres.forEach { genre ->
            val random = Random()
            val backgroundColor =
                Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))

            val chip = Chip(this@DetailMovieActivity)
            chip.text = genre.name
            chip.chipBackgroundColor = ColorStateList.valueOf(backgroundColor)
            chip.setTextColor(
                ContextCompat.getColorStateList(
                    this@DetailMovieActivity, R.color.white
                )
            )
            chipGroupGenre.addView(chip)
        }
        description.text = movie.overview
        buttonReview.setOnClickListener {
            val bottomSheet = ReviewBottomSheet(movieId)
            bottomSheet.show(supportFragmentManager, "REVIEW_BOTTOM_SHEET")
        }
    }

    companion object {
        private const val MOVIE_ID = "movie_id"
        fun Context.gotoDetailActivity(id: Int) {
            val intent = Intent(this, DetailMovieActivity::class.java)
            intent.putExtra(MOVIE_ID, id)
            startActivity(intent)
        }
    }
}