package com.shop.main.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.shop.main.domain.model.HomeItem
import com.shop.main.test.TEST_TAG_PRODUCT_CARD
import com.shop.main.ui.component.debug.DebugGoodsImagePlaceholder
import com.shop.main.ui.theme.AppTheme

@Composable
fun ProductCard(
    item: HomeItem.ProductItem,
    imageWidth: Dp,
    imageHeight: Dp,
    navigateDetail: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .clickable {
                navigateDetail(item.linkURL)
            }
            .width(imageWidth)
            .testTag(TEST_TAG_PRODUCT_CARD)
    ) {
        Box {
            AsyncImage(
                model = item.thumbnailUrl,
                placeholder = DebugGoodsImagePlaceholder(),
                contentDescription = null,
                modifier = Modifier
                    .width(imageWidth)
                    .height(imageHeight)
            )

            if (item.hasCoupon) {
                Text(
                    text = "쿠폰",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSecondary,
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.secondary)
                        .align(Alignment.BottomStart)
                        .padding(horizontal = 10.dp, vertical = 3.dp)
                )
            }
        }

        Spacer(Modifier.height(8.dp))

        Column(
            modifier = Modifier.padding(5.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Brand(text = item.brandName)
            Price(price = item.price, saleRate = item.saleRate)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductCardPreview() {
    AppTheme {
        ProductCard(
            item = HomeItem.ProductItem(
                brandName = "아스트랄 프로젝트 아스트랄 프로젝트 아스트랄 프로젝트",
                thumbnailUrl = "",
                price = 10000,
                saleRate = 10,
                linkURL = "",
                hasCoupon = true
            ),
            imageWidth = 150.dp,
            imageHeight = 150.dp,
            navigateDetail = {}
        )
    }
}