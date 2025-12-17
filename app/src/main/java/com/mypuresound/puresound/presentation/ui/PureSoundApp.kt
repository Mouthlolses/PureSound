package com.mypuresound.puresound.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.mypuresound.puresound.R
import com.mypuresound.puresound.player.ui.ExpandedMusicPlayerScreen
import com.mypuresound.puresound.player.ui.MiniMusicPlayerScreen
import com.mypuresound.puresound.presentation.ui.components.PureSoundTabRow
import com.mypuresound.puresound.presentation.ui.screens.home.HomeScreen
import com.mypuresound.puresound.presentation.ui.screens.music.MusicScreen
import com.mypuresound.puresound.presentation.ui.screens.playlist.PlayListScreen
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun PureSoundApp() {

    val scope = rememberCoroutineScope()
    val tabs = listOf("Início", "Músicas", "Playlist")
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { tabs.size }
    )

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showSheet by remember { mutableStateOf(false) }


    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                Column {
                    TopAppBar(
                        title = { Text("PureSound") },
                        actions = {
                            Icon(
                                painter = painterResource(R.drawable.ic_action_filled_settings),
                                contentDescription = "settings",
                                modifier = Modifier
                                    .clickable(
                                        onClick = {

                                        }
                                    )
                            )
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
            },
            bottomBar = {
                BottomAppBar {
                    MiniMusicPlayerScreen(
                        onClick = {
                            showSheet = true
                        }
                    )
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

        if (showSheet) {
            ModalBottomSheet(
                onDismissRequest = { showSheet = false },
                sheetState = sheetState,
                dragHandle = null
            ) {
                ExpandedMusicPlayerScreen(
                    onClose = {
                        showSheet = false
                    }
                )
            }
        }
    }
}
