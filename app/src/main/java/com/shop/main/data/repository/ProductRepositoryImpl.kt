package com.shop.main.data.repository

import com.shop.main.data.remote.SectionDto
import com.shop.main.data.remote.api.HomeApi

class ProductRepositoryImpl(private val api: HomeApi) : ProductRepository {
    override suspend fun fetchHomeJson(): List<SectionDto> {
        return api.getHomeSections().data
    }
}