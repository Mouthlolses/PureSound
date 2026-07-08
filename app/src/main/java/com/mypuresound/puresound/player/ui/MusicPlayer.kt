package com.mypuresound.puresound.player.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mypuresound.puresound.R

@Composable
fun MusicPlayer(
    modifier: Modifier = Modifier,
    isPlaying: Boolean,
    onPlay: () -> Unit,
    onPause: () -> Unit,
    onPrevious: () -> Unit,
    onNext: () -> Unit
) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    val height by animateDpAsState(
        targetValue = if (expanded) 700.dp else 64.dp,
        animationSpec = tween(400),
        label = ""
    )

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .clickable {
                expanded = !expanded
            },
        color = MaterialTheme.colorScheme.surface,
        shadowElevation = 8.dp
    ) {

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            AnimatedVisibility(
                visible = !expanded,
                enter = fadeIn(),
                exit = fadeOut()
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .background(Color.Gray, RoundedCornerShape(8.dp))
                    )

                    Spacer(Modifier.width(12.dp))

                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Bring Me The Horizon")
                        Text(
                            "Sleepwalking",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }

                    if (isPlaying) {
                        IconButton(onClick = onPause) {
                            Icon(painter = painterResource(R.drawable.ic_action_stop), null)
                        }
                    } else {
                        IconButton(onClick = onPlay) {
                            Icon(painter = painterResource(R.drawable.ic_action_play), null)

                        }
                    }
                }
            }

            // EXPANDED PLAYER
            AnimatedVisibility(
                visible = expanded,
                enter = fadeIn(),
                exit = fadeOut()
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp)
                ) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {

                        IconButton(
                            onClick = {
                                expanded = false
                            }
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_action_keyboard_arrow_down),
                                null
                            )
                        }
                    }

                    Spacer(Modifier.height(24.dp))

                    Box(
                        modifier = Modifier
                            .size(280.dp)
                            .align(Alignment.CenterHorizontally)
                            .background(Color.Gray, RoundedCornerShape(16.dp))
                    )

                    Spacer(Modifier.height(32.dp))

                    Text(
                        "Sleepwalking",
                        style = MaterialTheme.typography.headlineSmall
                    )

                    Text(
                        "Bring Me The Horizon",
                        style = MaterialTheme.typography.titleMedium
                    )

                    Spacer(Modifier.height(24.dp))

                    LinearProgressIndicator(
                        progress = { 0.35f },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(Modifier.height(32.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {

                        IconButton(onClick = onPrevious) {
                            Icon(
                                painter = painterResource(R.drawable.ic_action_skip_previous),
                                null
                            )
                        }

                        if (isPlaying) {
                            IconButton(onClick = onPause) {
                                Icon(
                                    painterResource(R.drawable.ic_action_stop),
                                    null,
                                    modifier = Modifier.size(72.dp)
                                )
                            }
                        } else {
                            IconButton(onClick = onPlay) {
                                Icon(
                                    painterResource(R.drawable.ic_action_play),
                                    null,
                                    modifier = Modifier.size(72.dp)
                                )
                            }
                        }

                        IconButton(onClick = onNext) {
                            Icon(painter = painterResource(R.drawable.ic_action_skip_next), null)
                        }
                    }
                }
            }
        }
    }
}