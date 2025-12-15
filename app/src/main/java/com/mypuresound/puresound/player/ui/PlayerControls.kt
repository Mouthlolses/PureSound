package com.mypuresound.puresound.player.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mypuresound.puresound.R


@Composable
fun PlayerControls(
    isPlaying: Boolean,
    onPlay: () -> Unit,
    onPause: () -> Unit,
    onPrevious: () -> Unit,
    onNext: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        IconButton(
            onClick = {
                onPrevious()
            }
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_action_skip_previous),
                contentDescription = "previous"
            )
        }
        Spacer(modifier = Modifier.padding(horizontal = 6.dp))
        IconButton(
            onClick = {
                if (isPlaying) onPause() else onPlay()
            }
        ) {
            Icon(
                painter = if (isPlaying)
                    painterResource(R.drawable.ic_action_stop)
                else
                    painterResource(R.drawable.ic_action_play),
                contentDescription = "play/pause",
                modifier = Modifier.size(48.dp)
            )
        }
        Spacer(modifier = Modifier.padding(horizontal = 6.dp))
        IconButton(
            onClick = {
                onNext()
            }
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_action_skip_next),
                contentDescription = "next"
            )
        }
    }
}