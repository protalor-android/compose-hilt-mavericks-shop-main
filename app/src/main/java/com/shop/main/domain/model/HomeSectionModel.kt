package com.shop.main.domain.model

data class HomeSectionModel(
    val sectionId: String,
    val itemId: String,
    val contents: SectionContents,
    var viewable: Boolean = true,
    var shuffleKey: Long = 0L
)