package com.chugunov.phonewalls.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chugunov.phonewalls.data.ImageRepositoryImpl
import com.chugunov.phonewalls.domain.GetCategoryListUseCase
import com.chugunov.phonewalls.domain.model.Category

class MainViewModel : ViewModel() {

    private val repository = ImageRepositoryImpl()

    private val getCategoryListUseCase = GetCategoryListUseCase(repository)

    private val _categoryList = MutableLiveData<ArrayList<Category>>()
    val categoryList: MutableLiveData<ArrayList<Category>> = _categoryList


    init {
        _categoryList.value = getCategoryListUseCase.getCategoryListUseCase().value
    }

}