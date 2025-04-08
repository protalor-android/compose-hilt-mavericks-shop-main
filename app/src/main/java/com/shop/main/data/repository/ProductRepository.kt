package com.shop.main.data.repository

import com.shop.main.data.remote.SectionDto

interface ProductRepository {
    suspend fun fetchHomeJson(): List<SectionDto>
}