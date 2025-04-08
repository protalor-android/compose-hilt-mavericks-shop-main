package com.shop.main.di

import com.shop.main.data.repository.ProductRepository
import com.shop.main.domain.usecase.FetchHomeSectionsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object HomeModule {

    @Provides
    fun provideUseCase(repository: ProductRepository): FetchHomeSectionsUseCase {
        return FetchHomeSectionsUseCase(repository)
    }
}