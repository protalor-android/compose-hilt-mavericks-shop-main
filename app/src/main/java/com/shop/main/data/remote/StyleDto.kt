package com.shop.main.data.remote

import kotlinx.serialization.Serializable

@Serializable
data class StyleDto(
    val linkURL: String,
    val thumbnailURL: String
)