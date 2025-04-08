package com.shop.main.ui.component.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.shop.main.test.TEST_TAG_APP_LOADING

@Composable
fun AppLoading() {
    Box(
        modifier = Modifier.fillMaxSize().testTag(TEST_TAG_APP_LOADING),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}