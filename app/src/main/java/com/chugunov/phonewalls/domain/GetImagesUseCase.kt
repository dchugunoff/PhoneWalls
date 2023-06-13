package com.chugunov.phonewalls.domain

import com.chugunov.phonewalls.domain.model.UnsplashResponse

class GetImagesUseCase(
    private val imageRepository: ImageRepository
) {
    suspend fun getImagesUseCase(query: String): UnsplashResponse {
        return imageRepository.getImages(query = query)
    }
}