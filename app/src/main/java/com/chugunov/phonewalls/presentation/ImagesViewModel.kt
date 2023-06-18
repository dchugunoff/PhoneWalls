package com.chugunov.phonewalls.presentation

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chugunov.phonewalls.R
import com.chugunov.phonewalls.data.ImageRepositoryImpl
import com.chugunov.phonewalls.domain.GetImagesUseCase
import com.chugunov.phonewalls.domain.model.UnsplashPhoto
import java.net.SocketTimeoutException

class ImagesViewModel : ViewModel() {

    private val repository = ImageRepositoryImpl()

    private val getImagesUseCase = GetImagesUseCase(repository)

    private val _imagesList = MutableLiveData<List<UnsplashPhoto>>()
    val imagesList: LiveData<List<UnsplashPhoto>> = _imagesList

    suspend fun getImages(params: String, context: Context) {
        if (isInternetAvailable(context)) {
            try {
                val response = getImagesUseCase.getImagesUseCase(params)
                _imagesList.value = response.results
            } catch (e: SocketTimeoutException) {
                Toast.makeText(
                    context,
                    context.getString(R.string.request_timeout),
                    Toast.LENGTH_LONG
                ).show()
            } catch (e: Exception) {
                Toast.makeText(
                    context,
                    context.getString(R.string.request_failed),
                    Toast.LENGTH_LONG
                ).show()
            }
        } else {
            Toast.makeText(
                context,
                context.getString(R.string.no_internet_connection),
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        val capabilities =
            connectivityManager.getNetworkCapabilities(network)
        return capabilities != null && capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
}