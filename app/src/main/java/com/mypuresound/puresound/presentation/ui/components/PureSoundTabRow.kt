package com.mypuresound.puresound.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun PureSoundTabRow(
    tabs: List<String> = listOf("Início", "Músicas","Configurações","Mais"),
    selectedIndex: Int = 0,
    onTabSelected: (Int) -> Unit = {}
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(tabs) { index, title ->
            PureSoundTab(
                title = title,
                selected = selectedIndex == index,
                onClick = { onTabSelected(index) }
            )
        }
    }
}