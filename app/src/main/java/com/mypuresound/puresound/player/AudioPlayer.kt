package com.mypuresound.puresound.player

import android.content.Context
import android.net.Uri
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class MusicPlayerManager @Inject constructor(
    @ApplicationContext context: Context
) {
    private val player = ExoPlayer.Builder(context).build()
    private val mediaItems = mutableListOf<MediaItem>()

    fun setPlaylist(urls: List<Uri>, startIndex: Int = 0) {
        mediaItems.clear()
        mediaItems.addAll(urls.map { MediaItem.fromUri(it) })
        player.setMediaItems(mediaItems, startIndex, 0L)
        player.prepare()
        player.play()
    }

    fun pause() = player.pause()

    fun resume() = player.play()

    fun stop() = player.stop()
    fun release() = player.release()

    fun seekToNextMediaItem() = player.seekToNextMediaItem()

    fun seekToPreviousMediaItem() = player.seekToPreviousMediaItem()

    fun isPlaying(): Boolean = player.isPlaying

}