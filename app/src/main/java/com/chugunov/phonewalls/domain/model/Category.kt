package com.chugunov.phonewalls.domain.model

import com.chugunov.phonewalls.R


data class Category(
    val name: String,
    val imageId: Int,
    val params: String
) {
    companion object {
        const val CONST_PARAMS = "wallpapers+phone"
        val categories = arrayListOf(
            Category("Cars", R.drawable.cars_category, "cars+$CONST_PARAMS"),
            Category("Cats", R.drawable.cats_category, "cat+$CONST_PARAMS"),
            Category("Cyberpunk", R.drawable.cyberpunk_category, "cyberpunk+$CONST_PARAMS"),
        )
    }
}
