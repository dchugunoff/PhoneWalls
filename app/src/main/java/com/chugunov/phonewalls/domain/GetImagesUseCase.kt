package com.chugunov.phonewalls.domain

import com.chugunov.phonewalls.domain.model.ImageResponse

class GetImagesUseCase(
    private val imageRepository: ImageRepository
) {
    suspend fun getImagesUseCase(query: String): ImageResponse {
        return imageRepository.getImages(query = query)
    }
}