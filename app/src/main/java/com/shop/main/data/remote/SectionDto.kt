package com.shop.main.data.remote

import kotlinx.serialization.Serializable

@Serializable
data class SectionDto(
    val header: HeaderDto? = null,
    val contents: ContentsDto,
    val footer: FooterDto? = null
)