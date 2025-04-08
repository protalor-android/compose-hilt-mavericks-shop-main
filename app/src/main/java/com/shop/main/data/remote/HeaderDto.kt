package com.shop.main.data.remote

import kotlinx.serialization.Serializable

@Serializable
data class HeaderDto(
    val title: String,
    val iconURL: String? = null,
    val linkURL: String? = null
)