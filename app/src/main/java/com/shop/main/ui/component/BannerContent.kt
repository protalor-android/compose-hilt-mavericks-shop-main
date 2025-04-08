package com.shop.main.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.shop.main.test.TEST_TAG_BANNER_CONTENT
import com.shop.main.ui.component.debug.DebugBannerImagePlaceholder
import com.shop.main.ui.theme.AppTheme
import kotlin.math.absoluteValue

@Composable
fun BannerContent(
    imageUrl: String,
    title: String,
    description: String,
    keyword: String,
    detailUrl: String,
    parallaxFraction: Float = 0f,
    currentPage: Int = 0,
    currentPageOffsetFraction: Float = 0f,
    targetPage: Int = 0,
    navigateDetail: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .graphicsLayer {
                val pageOffset = (
                        (currentPage - targetPage) + currentPageOffsetFraction
                        ).absoluteValue

                val scale = 1f - (pageOffset * .1f)
                scaleX = scale
                scaleY = scale
            }
            .clip(RoundedCornerShape(16.dp))
            .clickable { navigateDetail(detailUrl) }
            .testTag(TEST_TAG_BANNER_CONTENT)
    ) {
        AsyncImage(
            model = imageUrl,
            placeholder = DebugBannerImagePlaceholder(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.5f)
                        )
                    )
                )
                .padding(vertical = 40.dp)
        ) {
            Text(
                text = title,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                color = Color.White,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.graphicsLayer { translationX = -parallaxFraction * 500f },
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier.graphicsLayer { translationX = -parallaxFraction * 1000f }
            ) {
                Text(
                    text = keyword,
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.White.copy(alpha = 0.7f)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview
@Composable
fun BannerContentPreview() {
    AppTheme {
        BannerContent(
            imageUrl = "",
            title = "Sample Title",
            description = "Sample Description",
            keyword = "Sample Keyword",
            detailUrl = "https://example.com/detail",
            navigateDetail = {}
        )
    }
}