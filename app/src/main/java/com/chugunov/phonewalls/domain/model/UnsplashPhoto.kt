package com.chugunov.phonewalls.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UnsplashPhoto(
    val id: String,
    val urls: ImageUrls
): Parcelable