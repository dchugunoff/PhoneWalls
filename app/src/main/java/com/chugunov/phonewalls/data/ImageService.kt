package com.chugunov.phonewalls.data

import com.chugunov.phonewalls.domain.model.UnsplashResponse
import retrofit2.http.GET
import retrofit2.http.Query

const val ORIENTATION = "portrait"
const val KEY = "_MaEinDAy4KoPDkbm-L-0LXxoaIfmztTO5omFx7GN2Y"
const val PER_PAGE = 30

interface ImageService {

    @GET("search/photos")
    suspend fun getImages(
        @Query("client_id") clientId: String = KEY,
        @Query("orientation") orientation: String = ORIENTATION,
        @Query("per_page") perPage: Int = PER_PAGE,
        @Query("query") query: String
    ): UnsplashResponse

}