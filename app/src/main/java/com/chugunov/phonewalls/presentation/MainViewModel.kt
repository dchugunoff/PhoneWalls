package com.chugunov.phonewalls.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chugunov.phonewalls.domain.model.Category
import com.chugunov.phonewalls.data.ImageRepositoryImpl
import com.chugunov.phonewalls.domain.GetCategoryListUseCase
import com.chugunov.phonewalls.domain.GetImagesUseCase
import com.chugunov.phonewalls.domain.model.UnsplashPhoto
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val repository = ImageRepositoryImpl()

    private val getCategoryListUseCase = GetCategoryListUseCase(repository)
    private val getImagesUseCase = GetImagesUseCase(repository)

    private val _categoryList = MutableLiveData<ArrayList<Category>>()
    val categoryList: MutableLiveData<ArrayList<Category>> = _categoryList

    private val _paramsCategory = MutableLiveData<String>()
    val paramsCategory: LiveData<String> = _paramsCategory

    private var _imagesList = MutableLiveData<List<UnsplashPhoto>>()
    val imagesList: LiveData<List<UnsplashPhoto>> = _imagesList

    fun setParams(params: String) {
        _paramsCategory.value = params
    }
    init {
        _categoryList.value = getCategoryListUseCase.getCategoryListUseCase().value
//        viewModelScope.launch {
//            _imagesList.value = getImages()
//        }
    }

//    fun getParams(params: String) {
//        _paramsCategory.value = params
//    }

//    private suspend fun getImages(): List<UnsplashPhoto> {
//        val response = getImagesUseCase.getImagesUseCase(_paramsCategory.value.toString())
//        Log.d("Response", "Response: $response")
//        return response.results
//    }
    suspend fun getImages(params: String): List<UnsplashPhoto> {
        val response = getImagesUseCase.getImagesUseCase(params)
        Log.d("Response", "Response: $response")
        return response.results
    }
}