package com.shop.main.data.remote

import kotlinx.serialization.Serializable

@Serializable
data class FooterDto(
    val type: String,
    val title: String,
    val iconURL: String? = null
)