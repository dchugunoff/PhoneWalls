package com.chugunov.phonewalls.domain.model

data class ImageResponse(
    val images: List<Image>,
    val total: Int,
    val totalHits: Int
)