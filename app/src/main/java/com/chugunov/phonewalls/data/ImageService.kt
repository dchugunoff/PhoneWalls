package com.chugunov.phonewalls.data

import com.chugunov.phonewalls.domain.model.ImageResponse
import retrofit2.http.GET
import retrofit2.http.Query

const val ORIENTATION = "vertical"
const val KEY = "37203402-b96c117d858bb4a5e18a643de"
const val ORDER = "popular"
const val CATEGORY = "wallpaper"

interface ImageService {

    @GET("/api/")
    suspend fun getImages(
        @Query("orientation") orientation: String = ORIENTATION,
        @Query("key") key: String = KEY,
        @Query("order") order: String = ORDER,
        @Query("category") category: String = CATEGORY,
        @Query("q") query: String
    ): ImageResponse

}