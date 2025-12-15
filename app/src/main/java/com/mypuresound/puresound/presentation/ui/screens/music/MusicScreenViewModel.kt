package com.mypuresound.puresound.presentation.ui.screens.music

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.mypuresound.puresound.player.mediastore.Song
import com.mypuresound.puresound.player.mediastore.getAllSongs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MusicScreenViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application) {

    private val _song = MutableStateFlow<List<Song>>(emptyList())
    val songs: StateFlow<List<Song>> = _song.asStateFlow()


    fun loadSongs() {
        viewModelScope.launch {
            val list = getAllSongs(getApplication())
           _song.value = list
        }
    }


}