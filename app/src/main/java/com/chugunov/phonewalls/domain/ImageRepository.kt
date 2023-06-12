package com.chugunov.phonewalls.domain

import androidx.lifecycle.MutableLiveData
import com.chugunov.phonewalls.domain.model.Category
import com.chugunov.phonewalls.domain.model.ImageResponse

interface ImageRepository {

    suspend fun getImages(query: String): ImageResponse

    fun getCategoryList(): MutableLiveData<ArrayList<Category>>

}