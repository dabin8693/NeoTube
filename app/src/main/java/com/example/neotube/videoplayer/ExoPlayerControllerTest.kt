package com.example.neotube.videoplayer

import android.net.Uri
import androidx.annotation.OptIn
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import javax.inject.Inject

class ExoPlayerControllerTest @Inject constructor(private val player: ExoPlayer) {

    fun initialize(playerView: PlayerView, mediaUri: Uri) {
        player.also { exo ->
            playerView.player = exo
            exo.setMediaItem(MediaItem.fromUri(mediaUri))
            exo.prepare()
            exo.playWhenReady = true
            exo.addListener(eventListener)
        }
    }

    @OptIn(UnstableApi::class)
    fun play() {
        if(!player.isReleased()){
            player.play()
        }
    }

    @OptIn(UnstableApi::class)
    fun pause() {
        if(!player.isReleased()){
            player.pause()
        }
    }
    fun release() {
        player.release()
    }

    private val eventListener = object : Player.Listener {

    }
}