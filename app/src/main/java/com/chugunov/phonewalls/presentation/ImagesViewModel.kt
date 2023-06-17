package com.chugunov.phonewalls.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chugunov.phonewalls.data.ImageRepositoryImpl
import com.chugunov.phonewalls.domain.GetImagesUseCase
import com.chugunov.phonewalls.domain.model.UnsplashPhoto

class ImagesViewModel: ViewModel() {

    private val repository = ImageRepositoryImpl()

    private val getImagesUseCase = GetImagesUseCase(repository)

    private val _imagesList = MutableLiveData<List<UnsplashPhoto>>()
    val imagesList: LiveData<List<UnsplashPhoto>> = _imagesList

    suspend fun getImages(params: String) {
        val response = getImagesUseCase.getImagesUseCase(params)
        _imagesList.value = response.results
    }
}