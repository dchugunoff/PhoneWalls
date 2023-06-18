package com.chugunov.phonewalls.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chugunov.phonewalls.data.ImageRepositoryImpl
import com.chugunov.phonewalls.domain.GetCategoryListUseCase
import com.chugunov.phonewalls.domain.model.Category

class CategoryViewModel : ViewModel() {

    private val repository = ImageRepositoryImpl()

    private val getCategoryListUseCase = GetCategoryListUseCase(repository)

    private val _categoryList = MutableLiveData<List<Category>>()
    val categoryList: MutableLiveData<List<Category>> = _categoryList


    fun getCategories() {
        val categories = getCategoryListUseCase.getCategoryListUseCase()
        _categoryList.value = categories
    }

    init {
        getCategories()
    }

}