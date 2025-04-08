package com.shop.main.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shop.main.ui.theme.AppTheme

@Composable
fun PagerPageNumberIndicator(
    currentPage: Int,
    totalPages: Int
) {
    Surface(
        shape = RoundedCornerShape(4.dp),
        color = MaterialTheme.colorScheme.surface.copy(alpha = 0.8f)
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
            text = "${(currentPage % totalPages) + 1} / $totalPages",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PagerPageNumberIndicatorPreview() {
    AppTheme {
        PagerPageNumberIndicator(
            currentPage = 1,
            totalPages = 99
        )
    }
}