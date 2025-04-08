package com.shop.main.domain.model

data class SectionContents(
    val type: SectionType,
    val items: List<HomeItem>
)