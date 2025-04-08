package com.shop.main.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.shop.main.data.remote.api.HomeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideJson(): Json = Json { ignoreUnknownKeys = true }

    @Provides
    fun provideOkHttp(): OkHttpClient = OkHttpClient.Builder().build()

    @Provides
    fun provideRetrofit(json: Json, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://meta.musinsa.com")
            .client(client)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    @Provides
    fun provideHomeApi(retrofit: Retrofit): HomeApi {
        return retrofit.create(HomeApi::class.java)
    }
}