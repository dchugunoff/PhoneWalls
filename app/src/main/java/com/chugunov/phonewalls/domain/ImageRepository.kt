package com.chugunov.phonewalls.domain

import androidx.lifecycle.MutableLiveData
import com.chugunov.phonewalls.domain.model.Category
import com.chugunov.phonewalls.domain.model.UnsplashResponse

interface ImageRepository {

    suspend fun getImages(query: String): UnsplashResponse

    fun getCategoryList(): MutableLiveData<ArrayList<Category>>

}