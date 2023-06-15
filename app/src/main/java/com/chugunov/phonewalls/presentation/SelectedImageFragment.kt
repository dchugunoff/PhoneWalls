package com.chugunov.phonewalls.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        val imageUrl = args.imageArgs.urls.full
        binding.selectedImage.load(imageUrl) {
            crossfade(true)
        }
        binding.setWallpaperButton.setOnClickListener {
            val wallpaperDialog = WallpaperDialog(requireContext())
            val imageDrawable = binding.selectedImage.drawable
            wallpaperDialog.showWallpaperDialog(imageDrawable)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
