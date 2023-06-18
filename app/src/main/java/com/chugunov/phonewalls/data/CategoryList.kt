package com.chugunov.phonewalls.data

import com.chugunov.phonewalls.R
import com.chugunov.phonewalls.domain.model.Category

class CategoryList {
    companion object {
        val categoryList = listOf(
            Category("Cars", R.drawable.cars_category, "car"),
            Category("Cyberpunk", R.drawable.cyberpunk_category, "cyberpunk"),
            Category("Cats", R.drawable.cats_category, "cats"),
            Category("Flowers", R.drawable.flowers_category, "flowers"),
            Category("Nature", R.drawable.nature_category, "nature"),
            Category("Music", R.drawable.music_category, "music"),
            Category("Dogs", R.drawable.dogs_category, "dog"),
            Category("Computers", R.drawable.computers_category, "computer")
        )
    }

}