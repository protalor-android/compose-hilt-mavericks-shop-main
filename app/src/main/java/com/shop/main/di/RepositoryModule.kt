package com.shop.main.di

import com.shop.main.data.remote.api.HomeApi
import com.shop.main.data.repository.ProductRepository
import com.shop.main.data.repository.ProductRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideProductRepository(api: HomeApi): ProductRepository {
        return ProductRepositoryImpl(api)
    }
}