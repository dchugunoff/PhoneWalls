package com.chugunov.phonewalls.data

import com.chugunov.phonewalls.data.ImageFactory.imageService
import com.chugunov.phonewalls.domain.ImageRepository
import com.chugunov.phonewalls.domain.model.Category
import com.chugunov.phonewalls.domain.model.UnsplashResponse

class ImageRepositoryImpl() : ImageRepository {

    override suspend fun getImages(query: String): UnsplashResponse {
        return imageService.getImages(query = query)
    }

    override fun getCategoryList(): List<Category> {
        return CategoryList.categoryList
    }
}