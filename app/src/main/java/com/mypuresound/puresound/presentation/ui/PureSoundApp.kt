package com.mypuresound.puresound.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mypuresound.puresound.presentation.ui.screens.home.HomeScreen
import com.mypuresound.puresound.presentation.ui.screens.music.MusicScreen
import com.mypuresound.puresound.presentation.ui.screens.settings.SettingsScreen
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun PureSoundApp() {

    val scope = rememberCoroutineScope()
    val tabs = listOf("Início", "Músicas", "Config")
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { tabs.size }
    )

    Scaffold(
        topBar = {
            Column {
            TopAppBar(
                title = { Text("PureSound") }
            )
            SecondaryTabRow(
                selectedTabIndex = pagerState.currentPage,
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = pagerState.currentPage == index,
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        }
                    ) {
                        Text(title)
                    }
                }
            }
        }
        }
    ) { innerPadding ->
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .padding(innerPadding)
        ) { page ->
            when(page) {
                0 -> HomeScreen()
                1 -> MusicScreen()
                2 -> SettingsScreen()
            }
        }
    }

}

