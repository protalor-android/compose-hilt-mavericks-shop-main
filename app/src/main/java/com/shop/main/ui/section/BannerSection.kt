package com.shop.main.ui.section

import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shop.main.domain.model.HomeItem
import com.shop.main.domain.model.HomeSectionModel
import com.shop.main.domain.model.SectionContents
import com.shop.main.domain.model.SectionType
import com.shop.main.ui.component.BannerContent
import com.shop.main.ui.component.PagerPageNumberIndicator
import com.shop.main.ui.theme.AppTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest

@Composable
fun BannerSection(
    section: HomeSectionModel,
    navigateDetail: (String) -> Unit
) {
    val bannerItems = section.contents.items.filterIsInstance<HomeItem.BannerItem>()
    if (bannerItems.isEmpty()) return

    val realPageCount = bannerItems.size
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { Int.MAX_VALUE }
    )

    val pagerContentPadding = 30.dp
    val indicatorHorizontalPadding = pagerContentPadding * 1.6f

    val isDraggedState = pagerState.interactionSource.collectIsDraggedAsState()
    LaunchedEffect(key1 = isDraggedState) {
        snapshotFlow { isDraggedState.value }
            .collectLatest { isDragged ->
                if (isDragged) return@collectLatest
                while (true) {
                    delay(3_000)
                    pagerState.animateScrollToPage(pagerState.currentPage.inc())
                }
            }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
    ) {
        fun calculatePageOffset(state: PagerState, currentPage: Int): Float {
            return (state.currentPage + state.currentPageOffsetFraction - currentPage).coerceIn(-1f, 1f)
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
            contentPadding = PaddingValues(horizontal = pagerContentPadding),
        ) { page ->
            val item = bannerItems[page % realPageCount]
            val parallaxOffset = calculatePageOffset(pagerState, page)

            BannerContent(
                imageUrl = item.imageUrl,
                title = item.title,
                description = item.description,
                keyword = item.keyword,
                detailUrl = item.linkUrl,
                parallaxFraction = parallaxOffset,
                currentPage = pagerState.currentPage,
                currentPageOffsetFraction = pagerState.currentPageOffsetFraction,
                targetPage = page,
                navigateDetail = navigateDetail
            )
        }

        Box(
            modifier = Modifier.fillMaxSize().padding(horizontal = indicatorHorizontalPadding, vertical = indicatorHorizontalPadding / 4),
            contentAlignment = Alignment.BottomEnd
        ) {
            PagerPageNumberIndicator(
                currentPage = pagerState.currentPage,
                totalPages = realPageCount
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BannerSectionPreview() {
    AppTheme {
        BannerSection(
            section = HomeSectionModel(
                sectionId = "",
                itemId = "",
                contents = SectionContents(
                    type = SectionType.BANNER,
                    items = listOf(
                        HomeItem.BannerItem(
                            imageUrl = "",
                            title = "타이틀",
                            description = "설명",
                            keyword = "키워드",
                            linkUrl = ""
                        )
                    )
                )
            ),
            navigateDetail = {}
        )
    }
}