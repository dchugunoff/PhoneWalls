package com.chugunov.phonewalls.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import com.chugunov.phonewalls.databinding.FragmentSelectedImageBinding
import com.chugunov.phonewalls.domain.model.UnsplashPhoto

class SelectedImageFragment : Fragment() {

    private var _binding: FragmentSelectedImageBinding? = null
    private val binding: FragmentSelectedImageBinding
        get() = _binding ?: throw RuntimeException("FragmentSelectedImageBinding == null")

    private lateinit var imageArgs: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            imageArgs = it.getString(IMAGE_KEY) ?: ""
        }
    }

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
        binding.selectedImage.load(imageArgs)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val IMAGE_KEY = "image_key"
        fun newInstance(image: UnsplashPhoto): SelectedImageFragment {
            val fragment = SelectedImageFragment()
            val args = Bundle()
            args.putString(IMAGE_KEY, image.urls.full)
            fragment.arguments = args
            return fragment
        }
    }
}