package com.chugunov.phonewalls.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImageUrls(
    val raw: String,
    val full: String,
    val regular: String,
    val small: String,
    val thumb: String
): Parcelable