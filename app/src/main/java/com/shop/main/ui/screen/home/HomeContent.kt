package com.shop.main.ui.screen.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.shop.main.domain.model.HomeSectionModel
import com.shop.main.domain.model.SectionType
import com.shop.main.test.TEST_TAG_HOME_LAZY_COLUMN
import com.shop.main.ui.component.Footer
import com.shop.main.ui.component.Header
import com.shop.main.ui.component.common.AppHeader
import com.shop.main.ui.section.BannerSection
import com.shop.main.ui.section.GridSection
import com.shop.main.ui.section.ScrollSection
import com.shop.main.ui.section.StyleGridLeftSection
import com.shop.main.ui.section.StyleGridRightSection
import com.shop.main.ui.section.StyleGridSection
import com.shop.main.util.ShowIfTrue


@Composable
fun HomeContent(
    sections: () -> List<HomeSectionModel>,
    listState: LazyListState = rememberLazyListState(),
    innerPadding: PaddingValues = PaddingValues(0.dp),
    onExpandGroup: (String) -> Unit,
    onShuffleGroup: (String) -> Unit,
    navigateDetail: (String) -> Unit,
) {
    LazyColumn(
        state = listState,
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .testTag(TEST_TAG_HOME_LAZY_COLUMN)
    ) {
        itemsIndexed(sections()) { index, section ->
            (index == 0).ShowIfTrue { AppHeader() }
            if (section.viewable) {
                when (section.contents.type) {
                    SectionType.HEADER -> Header(section, navigateDetail)
                    SectionType.FOOTER -> {
                        Footer(
                            section = section,
                            onMore = { sectionId -> onExpandGroup(sectionId) },
                            onShuffle = { sectionId -> onShuffleGroup(sectionId) }
                        )
                    }
                    SectionType.BANNER -> BannerSection(section, navigateDetail)
                    SectionType.GRID -> GridSection(section, navigateDetail)
                    SectionType.SCROLL -> ScrollSection(section, navigateDetail)
                    SectionType.STYLE_GRID_LEFT -> StyleGridLeftSection(section, navigateDetail)
                    SectionType.STYLE_GRID_RIGHT -> StyleGridRightSection(section, navigateDetail)
                    SectionType.STYLE_GRID -> StyleGridSection(section, navigateDetail)
                }
            }
        }
    }
}