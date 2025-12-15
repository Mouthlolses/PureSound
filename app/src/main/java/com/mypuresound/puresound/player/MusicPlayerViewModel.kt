package com.mypuresound.puresound.player

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MusicPlayerViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application) {

    private val playerManager =
        MusicPlayerManager(application.applicationContext)

    private val _isPlaying = MutableStateFlow(false)
    val isPlaying = _isPlaying.asStateFlow()

    fun play(url: String) {
        playerManager.play(url)
        _isPlaying.value = true
    }

    fun pause() {
        playerManager.pause()
        _isPlaying.value = false
    }


    override fun onCleared() {
        super.onCleared()
        playerManager.release()
    }

}