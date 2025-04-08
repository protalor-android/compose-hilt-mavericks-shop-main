package com.shop.main.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.shop.main.R
import com.shop.main.domain.model.HomeItem
import com.shop.main.domain.model.HomeSectionModel
import com.shop.main.domain.model.SectionContents
import com.shop.main.domain.model.SectionType
import com.shop.main.test.TEST_TAG_HEADER_LINK
import com.shop.main.ui.component.debug.DebugIconPlaceholder
import com.shop.main.ui.theme.AppTheme

@Composable
fun Header(
    section: HomeSectionModel,
    navigateDetail: (String) -> Unit = {}
) {
    val header = section.contents.items.filterIsInstance<HomeItem.HeaderItem>().first()
    val isDark = isSystemInDarkTheme()
    val tintColor = if (isDark) Color.White else Color.Black
    val imageInline = "iconUrl"

    val annotatedString = buildAnnotatedString {
        append(header.title)
        append(" ")
        appendInlineContent(imageInline, "[img]")
    }

    val inlineContent = mapOf(
        imageInline to InlineTextContent(
            placeholder = Placeholder(
                width = 18.sp,
                height = 18.sp,
                placeholderVerticalAlign = PlaceholderVerticalAlign.Center
            )
        ) {
            AsyncImage(
                model = header.iconUrl,
                placeholder = DebugIconPlaceholder()?.let {
                    rememberVectorPainter(image = it)
                },
                colorFilter = ColorFilter.tint(tintColor),
                contentDescription = null,
            )
        }
    )

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(start = 16.dp, top = 24.dp, end = 16.dp, bottom = 16.dp)
    ) {
        Text(
            text = annotatedString,
            inlineContent = inlineContent,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.weight(1f)
        )

        header.linkUrl?.let {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clickable {
                        navigateDetail(it)
                    }.testTag(TEST_TAG_HEADER_LINK)
            ) {
                Text(
                    text = stringResource(R.string.all),
                    textDecoration = TextDecoration.Underline,
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

        }

    }
}

@Preview(showBackground = true)
@Composable
fun HeaderPreview() {
    AppTheme {
        Header(
            section = HomeSectionModel(
                sectionId = "",
                itemId = "",
                contents = SectionContents(
                    type = SectionType.HEADER,
                    items = listOf(
                        HomeItem.HeaderItem(
                            title = "디스커버리 익스페디션 인기 스니커즈: 최대 50% 할인",
                            iconUrl = ""
                        )
                    )
                )
            )
        )
    }
}