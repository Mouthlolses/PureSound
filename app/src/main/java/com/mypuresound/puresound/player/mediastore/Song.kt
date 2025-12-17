package com.mypuresound.puresound.player.mediastore

import android.net.Uri

data class Song(
    val id: Long = 0L,
    val title: String = "",
    val artist: String = "",
    val duration: Long = 0L,
    val uri: Uri = Uri.EMPTY
)
