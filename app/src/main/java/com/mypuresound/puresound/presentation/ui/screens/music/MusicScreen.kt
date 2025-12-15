package com.mypuresound.puresound.presentation.ui.screens.music

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.mypuresound.puresound.player.MusicPlayerViewModel
import com.mypuresound.puresound.player.mediastore.Song

@Composable
fun MusicScreen(
    musicScreenViewModel: MusicScreenViewModel = hiltViewModel(),
    musicPlayerViewModel: MusicPlayerViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val songs by musicScreenViewModel.songs.collectAsState()
    val playerMusicState by musicPlayerViewModel.isPlaying.collectAsState()

    val permission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        Manifest.permission.READ_MEDIA_AUDIO
    } else {
        Manifest.permission.READ_EXTERNAL_STORAGE
    }

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) {
            musicScreenViewModel.loadSongs()
        }
    }

    LaunchedEffect(Unit) {
        if (ContextCompat.checkSelfPermission(
                context,
                permission
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            launcher.launch(permission)
        } else {
            musicScreenViewModel.loadSongs()
        }
    }


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

