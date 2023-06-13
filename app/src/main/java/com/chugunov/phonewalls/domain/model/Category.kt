package com.chugunov.phonewalls.domain.model

import com.chugunov.phonewalls.R


data class Category(
    val name: String,
    val imageId: Int,
    val params: String
) {
    companion object {
        val categories = arrayListOf(
            Category("Cars", R.drawable.cars_category, "cars"),
            Category("Cats", R.drawable.cats_category, "cat"),
            Category("Cyberpunk", R.drawable.cyberpunk_category, "cyberpunk"),
        )
    }
}
