package com.example.movee.feature.video

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

                    override fun onError(p0: YouTubePlayer.ErrorReason?) {}
                })
                player?.setOnFullscreenListener { isFullscreen ->
                    if (isFullscreen.not()) finish()
                }
                player?.loadVideo(intent.getStringExtra("youtube_key"))
                player?.play()
            }

            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?
            ) {
                // TODO: finish activity and show youtube website using key
            }
        })
    }

    private fun playerStateChangeListener(player: YouTubePlayer) =
        object : PlayerStateChangeListener {
            override fun onLoading() { player.setFullscreen(true) }

            override fun onLoaded(p0: String?) {}

            override fun onAdStarted() {}

            override fun onVideoStarted() {}

            override fun onVideoEnded() {}

            override fun onError(p0: YouTubePlayer.ErrorReason?) {}
        }
}