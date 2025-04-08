package com.shop.main.data.remote

import kotlinx.serialization.Serializable

@Serializable
data class HomeResponse(
    val data: List<SectionDto>
)