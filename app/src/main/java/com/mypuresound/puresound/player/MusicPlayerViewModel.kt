package com.mypuresound.puresound.player

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.mypuresound.puresound.player.mediastore.Song
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MusicPlayerViewModel @Inject constructor(
    private val playerManager: MusicPlayerManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(PlayerUiState())
    val uiState = _uiState.asStateFlow()

    private val _isPlaying = MutableStateFlow(false)
    val isPlaying = _isPlaying.asStateFlow()

    private var currentPlaylist: List<Uri> = emptyList()


    fun playSong(song: Song?, playlist: List<Uri>) {
        currentPlaylist = playlist
        val startIndex = playlist.indexOf(song?.uri).coerceAtLeast(0)
        playerManager.setPlaylist(playlist, startIndex)
        _isPlaying.value = true

        _uiState.update {
            it.copy(
                currentSong = song,
                isPlaying = true
            )
        }
    }

    fun pause() {
        playerManager.pause()
        _isPlaying.value = false
    }

    fun resume() {
        playerManager.resume()
        _isPlaying.value = true
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

data class PlayerUiState(
    val currentSong: Song? = null,
    val isPlaying: Boolean = false
)