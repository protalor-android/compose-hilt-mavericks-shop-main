package com.shop.main.data.remote

import kotlinx.serialization.Serializable

@Serializable
data class BannerDto(
    val linkURL: String,
    val thumbnailURL: String,
    val title: String,
    val description: String,
    val keyword: String
)
