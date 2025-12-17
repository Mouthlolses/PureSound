package com.mypuresound.puresound.player.ui

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.mypuresound.puresound.player.MusicPlayerViewModel

@Composable
fun MiniMusicPlayerScreen(
    onClick: () -> Unit
) {
    val viewModel: MusicPlayerViewModel = hiltViewModel()
    val isPlaying by viewModel.isPlaying.collectAsState()

    val listUri = listOf<Uri>(Uri.EMPTY, Uri.EMPTY)


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Minha Música",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(Modifier.height(24.dp))

        PlayerControls(
            isPlaying = isPlaying,
            onPlay = { viewModel.playSong(Uri.EMPTY, listUri) },
            onPause = { viewModel.pause() },
            onPrevious = { viewModel.previousMediaItem() },
            onNext = { viewModel.nextMediaItem() }
        )
    }
}