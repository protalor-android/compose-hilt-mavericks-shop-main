package com.shop.main.data.remote.api

import com.shop.main.data.remote.HomeResponse
import retrofit2.http.GET

interface HomeApi {
    @GET("interview/list.json")
    suspend fun getHomeSections(): HomeResponse
}