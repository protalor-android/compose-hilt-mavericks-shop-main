package com.shop.main.ui.component.common

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.shop.main.ui.screen.home.HomeState
import kotlinx.coroutines.delay

@Composable
fun AnchorEffect(
    state: HomeState,
    listState: LazyListState
) {
    var lastAnchorId: String? by rememberSaveable { mutableStateOf(null) }
    LaunchedEffect(state.anchorItemId) {
        if (state.anchorItemId == lastAnchorId) return@LaunchedEffect

        val index = state.sections.invoke()?.indexOfFirst {
            it.sectionId == state.anchorItemId || it.itemId == state.anchorItemId
        }?.takeIf { it >= 0 } ?: return@LaunchedEffect

        lastAnchorId = state.anchorItemId
        delay(100)
        listState.animateScrollToItem(index)
    }

}