package com.shop.main.ui.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shop.main.domain.model.HomeItem
import com.shop.main.domain.model.HomeSectionModel
import com.shop.main.domain.model.SectionContents
import com.shop.main.domain.model.SectionType
import com.shop.main.ui.component.StyleCard
import com.shop.main.ui.theme.AppTheme

@Composable
fun StyleGridSection(section: HomeSectionModel, navigateDetail: (String) -> Unit) {
    val items = section.contents.items.filterIsInstance<HomeItem.StyleItem>()

    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items.forEach {
            StyleCard(
                modifier = Modifier.weight(1f),
                imageUrl = it.thumbnailUrl,
                linkUrl = it.linkUrl,
                navigateDetail = navigateDetail
            )
        }

        repeat(3 - items.size) {
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StyleRow3SectionPreview() {
    val previewItem = HomeItem.StyleItem(
        thumbnailUrl = "",
        linkUrl = "",
    )

    AppTheme {
        StyleGridSection(
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