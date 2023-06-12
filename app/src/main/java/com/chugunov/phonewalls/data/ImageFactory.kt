package com.chugunov.phonewalls.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ImageFactory {

    private const val BASE_URL = "https://pixabay.com"

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val imageService = retrofit.create(ImageService::class.java)
}