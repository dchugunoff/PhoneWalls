package com.chugunov.phonewalls.domain

import com.chugunov.phonewalls.domain.model.Category
import com.chugunov.phonewalls.domain.model.UnsplashResponse

interface ImageRepository {

    suspend fun getImages(query: String): UnsplashResponse

    fun getCategoryList(): List<Category>

}