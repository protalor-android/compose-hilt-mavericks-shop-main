package com.shop.main.ui.screen.home

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.airbnb.mvrx.Fail
import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.Uninitialized
import com.airbnb.mvrx.compose.collectAsState
import com.airbnb.mvrx.compose.mavericksViewModel
import com.shop.main.ui.component.common.AnchorEffect
import com.shop.main.ui.component.common.AppLoading
import com.shop.main.ui.component.common.ErrorText

@Composable
fun HomeScreen(navigateDetail: (String) -> Unit) {
    val viewModel: HomeViewModel = mavericksViewModel()
    val state by viewModel.collectAsState()
    val listState = rememberLazyListState()

    Scaffold { innerPadding ->
        when (val sections = state.sections) {
            is Uninitialized, is Loading -> AppLoading()
            is Success -> {
                HomeContent(
                    sections = { sections() },
                    listState = listState,
                    innerPadding = innerPadding,
                    onExpandGroup = { expanded -> viewModel.expandGroup(expanded) },
                    onShuffleGroup = { shuffle -> viewModel.shuffleGroup(shuffle) },
                    navigateDetail = navigateDetail,
                )
            }
            is Fail -> {
                ErrorText(sections.error.message)
            }
        }
    }

    AnchorEffect(state, listState)
}