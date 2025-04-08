package com.shop.main.ui.component

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.shop.main.R
import com.shop.main.test.TEST_TAG_PRICE
import com.shop.main.ui.theme.AppTheme
import com.shop.main.util.toCommaString

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Price(
    price: Int,
    saleRate: Int
) {
    FlowRow(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = stringResource(R.string.won, price.toCommaString()),
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.testTag(TEST_TAG_PRICE)
        )
        Spacer(Modifier.weight(1f))
        if (saleRate > 0) {
            Text(
                text = "$saleRate%",
                style = MaterialTheme.typography.labelLarge,
                color = Color.Red,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PricePreview() {
    AppTheme {
        Price(
            price = 100000,
            saleRate = 10
        )
    }
}