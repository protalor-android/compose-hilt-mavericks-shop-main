package com.shop.main.domain.model

sealed interface HomeItem {
    data class HeaderItem(
        val title: String,
        val iconUrl: String? = null,
        val linkUrl: String? = null
    ) : HomeItem

    data class FooterItem(
        val type: FooterType,
        val title: String,
        val iconUrl: String? = null
    ) : HomeItem

    data class BannerItem(
        val imageUrl: String,
        val linkUrl: String,
        val title: String,
        val description: String,
        val keyword: String
    ) : HomeItem

    data class ProductItem(
        val brandName: String,
        val price: Int,
        val saleRate: Int,
        val thumbnailUrl: String,
        val hasCoupon: Boolean,
        val linkURL: String
    ) : HomeItem

    data class StyleItem(
        val thumbnailUrl: String,
        val linkUrl: String
    ) : HomeItem
}