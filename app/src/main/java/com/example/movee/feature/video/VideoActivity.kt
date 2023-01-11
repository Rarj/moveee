package com.example.movee.feature.video

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.example.movee.BuildConfig
import com.example.movee.databinding.ActivityVideoBinding
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener
import com.google.android.youtube.player.YouTubePlayer.PlayerStateChangeListener

class VideoActivity : YouTubeBaseActivity() {

    private lateinit var binding: ActivityVideoBinding
    private val youtubeId by lazy { intent.getStringExtra("youtube_key") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.player.initialize(BuildConfig.YT_KEY, object : OnInitializedListener {
            override fun onInitializationSuccess(
                p0: YouTubePlayer.Provider?,
                player: YouTubePlayer?,
                p2: Boolean
            ) {
                player?.setPlayerStateChangeListener(object : PlayerStateChangeListener {
                    override fun onLoading() {}

                    override fun onLoaded(p0: String?) {}

                    override fun onAdStarted() {}

                    override fun onVideoStarted() { player.setFullscreen(true) }

                    override fun onVideoEnded() {}

                    override fun onError(p0: YouTubePlayer.ErrorReason?) {
                        openYoutubeWeb()
                    }
                })
                player?.setOnFullscreenListener { isFullscreen ->
                    if (isFullscreen.not()) finish()
                }
                player?.loadVideo(youtubeId)
                player?.play()
            }

            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?
            ) {
                openYoutubeWeb()
            }
        })
    }

    private fun openYoutubeWeb() {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("http://www.youtube.com/watch?v=${youtubeId}")
            )
        )
    }
}