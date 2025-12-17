package com.mypuresound.puresound.player.ui

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.mypuresound.puresound.R
import com.mypuresound.puresound.player.MusicPlayerViewModel

@Composable
fun ExpandedMusicPlayerScreen(onClose: () -> Unit) {
    val viewModel: MusicPlayerViewModel = hiltViewModel()
    val isPlaying by viewModel.isPlaying.collectAsState()
    val listUri = listOf<Uri>(Uri.EMPTY, Uri.EMPTY)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        IconButton(onClick = onClose) {
            Icon(painter = painterResource(R.drawable.ic_action_keyboard_arrow_down), contentDescription = "Fechar")
        }

        Text(
            text = "Minha Música - Título Completo",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(Modifier.height(32.dp))

        PlayerControls(
            isPlaying = isPlaying,
            onPlay = { viewModel.playSong(Uri.EMPTY, listUri) },
            onPause = { viewModel.pause() },
            onPrevious = { viewModel.previousMediaItem() },
            onNext = { viewModel.nextMediaItem() }
        )
    }
}