package com.shop.main.data.remote

import kotlinx.serialization.Serializable

@Serializable
data class GoodsDto(
    val linkURL: String,
    val thumbnailURL: String,
    val brandName: String,
    val price: Int,
    val saleRate: Int,
    val hasCoupon: Boolean
)