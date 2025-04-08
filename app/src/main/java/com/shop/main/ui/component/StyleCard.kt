package com.shop.main.ui.component

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.shop.main.test.TEST_TAG_STYLE_CARD
import com.shop.main.ui.component.debug.DebugStyleImagePlaceholder
import com.shop.main.ui.theme.AppTheme

@Composable
fun StyleCard(
    modifier: Modifier = Modifier,
    imageUrl: String,
    linkUrl: String,
    scaleEffect: Float = 1.1f,
    navigateDetail: (String) -> Unit
) {
    val inspectMode = LocalInspectionMode.current
    val imageAspectRatio = 1.5f
    var enableEffect by rememberSaveable { mutableStateOf(false) }
    val effectDuration = 1500

    val scale by animateFloatAsState(
        targetValue = if (enableEffect) 1f else scaleEffect,
        animationSpec = tween(durationMillis = effectDuration, easing = FastOutSlowInEasing),
        label = "scaleAnimation"
    )

    val alpha by animateFloatAsState(
        targetValue = if (enableEffect) 1f else 0f,
        animationSpec = tween(durationMillis = effectDuration),
        label = "fadeInAlpha"
    )


    LaunchedEffect(Unit) {
        if (!inspectMode)
            enableEffect = true
    }

    BoxWithConstraints(
        modifier = modifier
    ) {
        AsyncImage(
            model = imageUrl,
            placeholder = DebugStyleImagePlaceholder(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .width(maxWidth)
                .height((maxWidth + 8.dp) * imageAspectRatio)
                .graphicsLayer {
                    if (enableEffect) {
                        this.alpha = alpha
                        this.scaleX = scale
                        this.scaleY = scale
                    }
                }
                .clickable {
                    navigateDetail(linkUrl)
                }
                .testTag(TEST_TAG_STYLE_CARD)
        )
    }

}


@Preview(showBackground = true)
@Composable
fun StyleCardPreview() {
    AppTheme {
        StyleCard(
            imageUrl = "https://example.com/image.jpg",
            linkUrl = "https://example.com",
            navigateDetail = {}
        )
    }
}