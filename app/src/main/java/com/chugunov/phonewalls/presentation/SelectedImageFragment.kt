package com.chugunov.phonewalls.presentation

import android.Manifest
import android.app.WallpaperManager
import android.content.pm.PackageManager
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import com.chugunov.phonewalls.databinding.FragmentSelectedImageBinding

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
        _binding = FragmentSelectedImageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.selectedImage.load(args.imageArgs.urls.full)

        binding.setWallpaperButton.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.SET_WALLPAPER
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                setWallpaper()
                Toast.makeText(requireContext(), "Wallpaper edited!", Toast.LENGTH_SHORT).show()
            } else {
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(Manifest.permission.SET_WALLPAPER),
                    WALLPAPER_PERMISSION_REQUEST_CODE
                )
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setWallpaper() {
        try {
            val wallpaperManager = WallpaperManager.getInstance(requireContext())
            val drawable = binding.selectedImage.drawable
            if (drawable is BitmapDrawable) {
                val bitmap = drawable.bitmap
                wallpaperManager.setBitmap(bitmap)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    companion object {
        private const val WALLPAPER_PERMISSION_REQUEST_CODE = 100
    }
}


