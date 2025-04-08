package com.shop.main.ui.section

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shop.main.domain.model.HomeItem
import com.shop.main.domain.model.HomeSectionModel
import com.shop.main.domain.model.SectionContents
import com.shop.main.domain.model.SectionType
import com.shop.main.test.TEST_TAG_SCROLL_SECTION
import com.shop.main.ui.component.ProductCard
import com.shop.main.ui.theme.AppTheme

@Composable
fun ScrollSection(
    section: HomeSectionModel,
    navigateDetail: (String) -> Unit
) {
    val products = section.contents.items.filterIsInstance<HomeItem.ProductItem>()
    val shuffledProducts = rememberSaveable(section.shuffleKey) { products.shuffled() }
    var isVisible by rememberSaveable(section.shuffleKey) { mutableStateOf(section.shuffleKey == 0L) }
    val alphaState by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0f,
        animationSpec = tween(durationMillis = if (section.shuffleKey > 0 && isVisible) 1000 else 0),
        label = "fadeAlpha"
    )

    val imageWidth = 140.dp
    val imageRatio = 427f / 320f
    val imageHeight = 140.dp * imageRatio

    LaunchedEffect(section.shuffleKey) {
        isVisible = false
        isVisible = true
    }

    Box(
        modifier = Modifier
            .graphicsLayer(alpha = alphaState)
            .fillMaxWidth()
    ) {
        LazyRow(
            modifier = Modifier.heightIn(min = 280.dp).testTag(TEST_TAG_SCROLL_SECTION),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (isVisible) {
                items(shuffledProducts) { item ->
                    ProductCard(
                        item = item,
                        imageWidth = imageWidth,
                        imageHeight = imageHeight,
                        navigateDetail = navigateDetail
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ScrollSectionPreview() {
    val previewData = HomeItem.ProductItem(
        brandName = "아스트랄 프로젝트 아스트랄 프로젝트 아스트랄 프로젝트",
        thumbnailUrl = "",
        price = 10000,
        saleRate = 10,
        linkURL = "",
        hasCoupon = true
    )

    AppTheme {
        ScrollSection(
            section = HomeSectionModel(
                sectionId = "",
                itemId = "",
                contents = SectionContents(
                    type = SectionType.SCROLL,
                    items = listOf(
                        previewData,
                        previewData.copy(price = 20000, saleRate = 0),
                        previewData.copy(
                            hasCoupon = false,
                            brandName = "아스트랄",
                            price = 12345678
                        ),
                    )
                )
            ),
            navigateDetail = {}
        )
    }
}