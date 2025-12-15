package com.mypuresound.puresound.player

import android.net.Uri
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MusicPlayerViewModel @Inject constructor(
    private val playerManager: MusicPlayerManager
) : ViewModel() {


    private val _isPlaying = MutableStateFlow(false)
    val isPlaying = _isPlaying.asStateFlow()

    private var currentPlaylist: List<Uri> = emptyList()


    fun playSong(url: Uri, playlist: List<Uri>) {
        currentPlaylist = playlist
        val startIndex = playlist.indexOf(url).coerceAtLeast(0)
        playerManager.setPlaylist(playlist, startIndex)
        _isPlaying.value = true
    }

    fun pause() {
        playerManager.pause()
        _isPlaying.value = false
    }

    fun nextMediaItem() {
        playerManager.seekToNextMediaItem()
    }

    fun previousMediaItem() {
        playerManager.seekToPreviousMediaItem()
    }

    override fun onCleared() {
        super.onCleared()
        playerManager.release()
    }

}