package com.shop.main.ui.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shop.main.domain.model.HomeItem
import com.shop.main.domain.model.HomeSectionModel
import com.shop.main.domain.model.SectionContents
import com.shop.main.domain.model.SectionType
import com.shop.main.ui.component.StyleCard
import com.shop.main.ui.theme.AppTheme

@Composable
fun StyleGridRightSection(section: HomeSectionModel, navigateDetail: (String) -> Unit) {
    val configuration = LocalConfiguration.current
    val items = section.contents.items.filterIsInstance<HomeItem.StyleItem>()
    val screenWidth = configuration.screenWidthDp.dp
    val horizontalPadding = 16.dp
    val largeImageAdjustWidth = horizontalPadding * 2 - 8.dp

    Column(
        modifier = Modifier.fillMaxWidth().padding(horizontal = horizontalPadding, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // 🔹 첫 줄: 왼쪽 1x1 x 2 + 오른쪽 2x2
        Row(
            modifier = Modifier.fillMaxWidth().aspectRatio(1f),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f).fillMaxHeight(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                StyleCard(
                    imageUrl = items.getOrNull(0)?.thumbnailUrl.orEmpty(),
                    linkUrl = items.getOrNull(0)?.linkUrl.orEmpty(),
                    navigateDetail = navigateDetail
                )
                StyleCard(
                    imageUrl = items.getOrNull(1)?.thumbnailUrl.orEmpty(),
                    linkUrl = items.getOrNull(1)?.linkUrl.orEmpty(),
                    navigateDetail = navigateDetail
                )
            }

            StyleCard(
                modifier = Modifier.width((screenWidth - largeImageAdjustWidth - screenWidth / 3)),
                imageUrl = items.getOrNull(2)?.thumbnailUrl.orEmpty(),
                linkUrl = items.getOrNull(2)?.linkUrl.orEmpty(),
                navigateDetail = navigateDetail
            )
        }

        // 🔹 두 번째 줄: 1x1 x 3
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items.drop(3).forEach {
                StyleCard(
                    modifier = Modifier.weight(1f),
                    imageUrl = it.thumbnailUrl,
                    linkUrl = it.linkUrl,
                    navigateDetail = navigateDetail
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun StyleRightLargeSectionPreview() {
    val previewItem = HomeItem.StyleItem(
        thumbnailUrl = "",
        linkUrl = "",
    )

    AppTheme {
        StyleGridRightSection(
            section = HomeSectionModel(
                sectionId = "",
                itemId = "",
                contents = SectionContents(
                    type = SectionType.STYLE_GRID_RIGHT,
                    items = listOf(
                        previewItem,
                        previewItem,
                        previewItem
                    )
                )
            ),
            navigateDetail = {}
        )
    }
}