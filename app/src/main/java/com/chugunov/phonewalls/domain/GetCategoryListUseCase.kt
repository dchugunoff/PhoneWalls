package com.chugunov.phonewalls.domain

import androidx.lifecycle.MutableLiveData
import com.chugunov.phonewalls.domain.model.Category

class GetCategoryListUseCase(
    private val imageRepository: ImageRepository
) {

    fun getCategoryListUseCase(): MutableLiveData<ArrayList<Category>> {
        return imageRepository.getCategoryList()
    }
}