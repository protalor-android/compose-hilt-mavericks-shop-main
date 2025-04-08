package com.shop.main.domain.usecase

import com.shop.main.data.repository.ProductRepository
import com.shop.main.domain.mapper.toDomain
import com.shop.main.domain.model.HomeSectionModel
import javax.inject.Inject

class FetchHomeSectionsUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(): List<HomeSectionModel> {
        return repository.fetchHomeJson().flatMap { it.toDomain() }
    }
}