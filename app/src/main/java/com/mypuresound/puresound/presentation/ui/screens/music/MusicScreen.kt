package com.mypuresound.puresound.presentation.ui.screens.music

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.mypuresound.puresound.player.MusicPlayerViewModel
import com.mypuresound.puresound.player.mediastore.Song

@Composable
fun MusicScreen(
    musicScreenViewModel: MusicScreenViewModel = hiltViewModel(),
    musicPlayerViewModel: MusicPlayerViewModel = hiltViewModel()
) {

    val songs by musicScreenViewModel.songs.collectAsState()


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(songs) { song ->
            SongItem(
                onClick = {
                    musicPlayerViewModel.play(song.uri.toString())
                },
                song = song
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SongItem(
    onClick: () -> Unit = { },
    song: Song = Song(id = 0, title = "Musica 1", "Overcooking", 3, Uri.EMPTY)
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            }
            .padding(16.dp)
    ) {
        Text(text = song.title, style = MaterialTheme.typography.bodyLarge)
        Text(text = song.artist, style = MaterialTheme.typography.bodyMedium)
    }
}

