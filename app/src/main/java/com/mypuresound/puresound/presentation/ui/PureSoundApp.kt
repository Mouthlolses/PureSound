package com.mypuresound.puresound.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.mypuresound.puresound.R
import com.mypuresound.puresound.player.MusicPlayerViewModel
import com.mypuresound.puresound.player.ui.MusicPlayer
import com.mypuresound.puresound.presentation.ui.components.PureSoundTabRow
import com.mypuresound.puresound.presentation.ui.screens.home.HomeScreen
import com.mypuresound.puresound.presentation.ui.screens.music.MusicScreen
import com.mypuresound.puresound.presentation.ui.screens.playlist.PlayListScreen
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun PureSoundApp() {

    val viewModel: MusicPlayerViewModel = hiltViewModel()
    val isPlaying by viewModel.isPlaying.collectAsState()
    val state by viewModel.uiState.collectAsState()

    val scope = rememberCoroutineScope()
    val tabs = listOf("Início", "Músicas", "Playlist")
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { tabs.size }
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Scaffold(
            topBar = {
                Column {
                    TopAppBar(
                        title = { Text("PureSound") },
                        actions = {
                            IconButton(
                                onClick = {

                                }
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.ic_action_filled_settings),
                                    contentDescription = "settings"
                                )
                            }

                        }
                    )
                    HorizontalDivider()
                    PureSoundTabRow(
                        tabs = tabs,
                        selectedIndex = pagerState.currentPage
                    ) { index ->
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    }
                    HorizontalDivider()
                }
            }
        ) { innerPadding ->
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .padding(innerPadding)
            ) { page ->
                when (page) {
                    0 -> HomeScreen()
                    1 -> MusicScreen()
                    2 -> PlayListScreen()
                }
            }
        }
        MusicPlayer(
            modifier = Modifier
                .navigationBarsPadding()
                .align(Alignment.BottomCenter),
            isPlaying = isPlaying,
            onPlay = viewModel::resume,
            onPause = viewModel::pause,
            onPrevious = viewModel::previousMediaItem,
            onNext = viewModel::nextMediaItem
        )
    }
}
