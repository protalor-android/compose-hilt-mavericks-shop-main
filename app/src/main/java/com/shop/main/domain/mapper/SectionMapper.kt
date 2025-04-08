package com.shop.main.domain.mapper

import com.shop.main.data.remote.SectionDto
import com.shop.main.domain.model.FooterType
import com.shop.main.domain.model.HomeItem
import com.shop.main.domain.model.HomeSectionModel
import com.shop.main.domain.model.SectionContents
import com.shop.main.domain.model.SectionType
import java.util.UUID

fun SectionDto.toDomain(): List<HomeSectionModel> {
    val sectionList = mutableListOf<HomeSectionModel>()
    val sectionId = UUID.randomUUID().toString()
    addHeader(sectionId, sectionList)
    when (contents.type) {
        "BANNER" -> addBanner(sectionId, sectionList)
        "GRID" -> addGrid(sectionId, sectionList)
        "SCROLL" -> addScroll(sectionId, sectionList)
        "STYLE" -> addStyle(sectionId, sectionList)
    }
    addFooter(sectionId, sectionList)
    return sectionList
}

private fun SectionDto.addHeader(
    sectionId: String,
    sectionList: MutableList<HomeSectionModel>
) {
    header?.let {
        sectionList += HomeSectionModel(
            sectionId = sectionId,
            itemId = UUID.randomUUID().toString(),
            contents = SectionContents(
                type = SectionType.HEADER,
                items = listOf(HomeItem.HeaderItem(it.title, it.iconURL, it.linkURL))
            )
        )
    }
}

private fun SectionDto.addFooter(
    sectionId: String,
    sectionList: MutableList<HomeSectionModel>
) {
    footer?.let {
        sectionList += HomeSectionModel(
            sectionId = sectionId,
            itemId = UUID.randomUUID().toString(),
            contents = SectionContents(
                type = SectionType.FOOTER,
                items = listOf(HomeItem.FooterItem(FooterType.valueOf(it.type), it.title, it.iconURL))
            )
        )
    }
}


private fun SectionDto.addBanner(
    sectionId: String,
    sectionList: MutableList<HomeSectionModel>
) {
    contents.banners?.map {
        HomeItem.BannerItem(it.thumbnailURL, it.linkURL, it.title, it.description, it.keyword)
    }?.let { banners ->
        sectionList += HomeSectionModel(
            sectionId = sectionId,
            itemId = UUID.randomUUID().toString(),
            contents = SectionContents(SectionType.BANNER, banners)
        )
    }
}

private fun SectionDto.addGrid(
    sectionId: String,
    sectionList: MutableList<HomeSectionModel>
) {
    contents.goods?.map {
        HomeItem.ProductItem(
            brandName = it.brandName,
            price = it.price,
            saleRate = it.saleRate,
            thumbnailUrl = it.thumbnailURL,
            hasCoupon = it.hasCoupon,
            linkURL = it.linkURL
        )
    }?.chunked(3)?.forEachIndexed { index, contents ->
        sectionList += HomeSectionModel(
            sectionId = sectionId,
            itemId = UUID.randomUUID().toString(),
            contents = SectionContents(SectionType.GRID, contents),
            viewable = index < 2
        )
    }
}

private fun SectionDto.addScroll(
    sectionId: String,
    sectionList: MutableList<HomeSectionModel>
) {
    contents.goods?.map {
        HomeItem.ProductItem(
            brandName = it.brandName,
            price = it.price,
            saleRate = it.saleRate,
            thumbnailUrl = it.thumbnailURL,
            hasCoupon = it.hasCoupon,
            linkURL = it.linkURL
        )
    }?.let {
        sectionList += HomeSectionModel(
            sectionId = sectionId,
            itemId = UUID.randomUUID().toString(),
            contents = SectionContents(SectionType.SCROLL, it)
        )
    }
}

private fun SectionDto.addStyle(
    sectionId: String,
    sectionList: MutableList<HomeSectionModel>
) {
    val items = contents.styles?.map {
        HomeItem.StyleItem(it.thumbnailURL, it.linkURL)
    } ?: emptyList()

    items.chunked(6).forEachIndexed { index, chunk ->
        val type = when {
            chunk.size >= 6 -> {
                if (index % 2 == 0) SectionType.STYLE_GRID_LEFT else SectionType.STYLE_GRID_RIGHT
            }
            else -> SectionType.STYLE_GRID
        }

        sectionList += HomeSectionModel(
            sectionId = sectionId,
            itemId = UUID.randomUUID().toString(),
            contents = SectionContents(type, chunk),
            viewable = index == 0
        )
    }
}