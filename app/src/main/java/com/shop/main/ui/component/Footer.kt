package com.shop.main.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.shop.main.util.ResolveShow
import com.shop.main.domain.model.FooterType
import com.shop.main.domain.model.HomeItem
import com.shop.main.domain.model.HomeSectionModel
import com.shop.main.domain.model.SectionContents
import com.shop.main.domain.model.SectionType
import com.shop.main.test.TEST_TAG_FOOTER_PREFIX
import com.shop.main.ui.theme.AppTheme

@Composable
fun Footer(
    section: HomeSectionModel,
    onMore: (String) -> Unit = {},
    onShuffle: (String) -> Unit = {}
) {
    val footer = section.contents.items.filterIsInstance<HomeItem.FooterItem>().first()
    val imageInline = "iconUrl"
    val color = MaterialTheme.colorScheme.outline

    val annotatedString = buildAnnotatedString {
        appendInlineContent(imageInline, "[img]")
        append(" ")
        append(footer.title)
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
                model = footer.iconUrl,
                colorFilter = ColorFilter.tint(color),
                contentDescription = null,
            )
        }
    )
    OutlinedButton(
        shape = CircleShape,
        border = BorderStroke(1.dp, color),
        onClick = {
            when (footer.type) {
                FooterType.NONE -> return@OutlinedButton
                FooterType.MORE -> onMore(section.sectionId)
                FooterType.REFRESH -> onShuffle(section.sectionId)
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .testTag("$TEST_TAG_FOOTER_PREFIX${footer.type}"),
        contentPadding = PaddingValues(10.dp)
    ) {
        footer.iconUrl.isNullOrEmpty().ResolveShow(
            trueContent = {
                Text(
                    text = footer.title,
                    color = color,
                    style = MaterialTheme.typography.labelLarge
                )
            },
            falseContent = {
                Text(
                    text = annotatedString,
                    color = color,
                    inlineContent = inlineContent,
                    style = MaterialTheme.typography.labelLarge
                )
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FooterPreview() {
    AppTheme {
        Footer(
            section = HomeSectionModel(
                sectionId = "",
                itemId = "",
                contents = SectionContents(
                    type = SectionType.FOOTER,
                    items = listOf(
                        HomeItem.FooterItem(
                            type = FooterType.MORE,
                            title = "더보기",
                            iconUrl = ""
                        )
                    )
                )
            )
        )
    }
}