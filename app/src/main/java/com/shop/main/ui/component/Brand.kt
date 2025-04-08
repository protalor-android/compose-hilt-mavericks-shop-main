package com.shop.main.ui.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import com.shop.main.test.TEST_TAG_BRAND
import com.shop.main.ui.theme.AppTheme
import com.shop.main.util.useNonBreakingSpace

@Composable
fun Brand(text: String) {
    Text(
        text = text.useNonBreakingSpace(),
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onBackground,
        maxLines = 2,
        modifier = Modifier.testTag(TEST_TAG_BRAND)
    )
}

@Preview(showBackground = true)
@Composable
fun BrandPreview() {
    AppTheme {
        Brand(
            text = "아스트랄 프로젝트"
        )
    }
}