package com.shop.main.data.remote

import kotlinx.serialization.Serializable

@Serializable
data class ContentsDto(
    val type: String,
    val banners: List<BannerDto>? = null,
    val goods: List<GoodsDto>? = null,
    val styles: List<StyleDto>? = null
)