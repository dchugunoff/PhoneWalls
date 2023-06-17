package com.chugunov.phonewalls.presentation

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.ImageLoader
import coil.load
import coil.request.CachePolicy
import com.chugunov.phonewalls.R
import com.chugunov.phonewalls.data.downloader.ImageDownloader
import com.chugunov.phonewalls.databinding.FragmentSelectedImageBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class SelectedImageFragment : Fragment() {

    private val args by navArgs<SelectedImageFragmentArgs>()

    private var _binding: FragmentSelectedImageBinding? = null
    private val binding: FragmentSelectedImageBinding
        get() = _binding ?: throw RuntimeException("FragmentSelectedImageBinding == null")


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as AppCompatActivity).findViewById<BottomNavigationView>(R.id.bottom_nav_view)?.visibility =
            View.GONE
        _binding = FragmentSelectedImageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageUrl = args.imageArgs.urls.regular
        val imgLoader = ImageLoader.Builder(requireContext())
            .memoryCachePolicy(CachePolicy.ENABLED)
            .diskCachePolicy(CachePolicy.ENABLED)
            .build()
        binding.selectedImage.load(imageUrl, imgLoader) {
            crossfade(false)
            diskCachePolicy(CachePolicy.ENABLED)
        }
        binding.setWallpaperButton.setOnClickListener {
            val wallpaperDialog = WallpaperDialog(requireContext())
            val imageDrawable = binding.selectedImage.drawable
            wallpaperDialog.showWallpaperDialog(imageDrawable)
        }
        binding.downloadButton.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q || isWriteExternalStoragePermissionGranted()) {
                val downloader = ImageDownloader(requireContext())
                downloader.downloadFile(imageUrl)
            } else {
                requestWriteExternalStoragePermission()
            }
        }
    }

    private fun isWriteExternalStoragePermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestWriteExternalStoragePermission() {
        requireActivity().requestPermissions(
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
            PERMISSION_REQUEST_CODE
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        (activity as AppCompatActivity).findViewById<BottomNavigationView>(R.id.bottom_nav_view)?.visibility =
            View.VISIBLE
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == PERMISSION_REQUEST_CODE && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            val imageUrl = args.imageArgs.urls.full
            val downloader = ImageDownloader(requireContext())
            downloader.downloadFile(imageUrl)
        }
    }

    companion object {
        private const val PERMISSION_REQUEST_CODE = 2
    }
}