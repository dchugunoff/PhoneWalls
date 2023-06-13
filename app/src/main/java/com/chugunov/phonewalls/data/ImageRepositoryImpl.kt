package com.chugunov.phonewalls.data

import androidx.lifecycle.MutableLiveData
import com.chugunov.phonewalls.data.ImageFactory.imageService
import com.chugunov.phonewalls.domain.ImageRepository
import com.chugunov.phonewalls.domain.model.Category
import com.chugunov.phonewalls.domain.model.UnsplashResponse

class ImageRepositoryImpl() : ImageRepository {

    override suspend fun getImages(query: String): UnsplashResponse {
        return imageService.getImages(query = query)
    }

    override fun getCategoryList(): MutableLiveData<ArrayList<Category>> {
        val categoryList = MutableLiveData<ArrayList<Category>>()
        categoryList.value = Category.categories
        return categoryList
    }
}