package com.shop.main.ui.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shop.main.domain.model.HomeItem
import com.shop.main.domain.model.HomeSectionModel
import com.shop.main.domain.model.SectionContents
import com.shop.main.domain.model.SectionType
import com.shop.main.ui.component.ProductCard
import com.shop.main.ui.theme.AppTheme

@Composable
fun GridSection(
    section: HomeSectionModel,
    navigateDetail: (String) -> Unit
) {
    val products = section.contents.items.filterIsInstance<HomeItem.ProductItem>()

    if (products.isEmpty()) return

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val itemWidthHeight = (screenWidth - 48.dp) / 3

    Row(
        modifier = Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        products.forEach { item ->
            ProductCard(
                item = item,
                imageWidth = itemWidthHeight,
                imageHeight = itemWidthHeight,
                navigateDetail
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Grid3SectionPreview() {
    val previewData = HomeItem.ProductItem(
        brandName = "아스트랄 프로젝트 아스트랄 프로젝트 아스트랄 프로젝트",
        thumbnailUrl = "",
        price = 10000,
        saleRate = 10,
        linkURL = "",
        hasCoupon = true
    )

    AppTheme {
        GridSection(
            section = HomeSectionModel(
                sectionId = "",
                itemId = "",
                contents = SectionContents(
                    type = SectionType.GRID,
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