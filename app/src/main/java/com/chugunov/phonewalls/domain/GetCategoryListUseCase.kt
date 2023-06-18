package com.chugunov.phonewalls.domain

import com.chugunov.phonewalls.domain.model.Category

class GetCategoryListUseCase(
    private val imageRepository: ImageRepository
) {

    fun getCategoryListUseCase(): List<Category> {
        return imageRepository.getCategoryList()
    }
}