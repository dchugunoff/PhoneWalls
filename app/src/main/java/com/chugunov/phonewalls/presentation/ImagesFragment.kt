package com.chugunov.phonewalls.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.chugunov.phonewalls.databinding.FragmentImagesBinding
import com.chugunov.phonewalls.domain.model.UnsplashPhoto
import kotlinx.coroutines.launch

class ImagesFragment : Fragment() {


    private lateinit var params: String

    private var _binding: FragmentImagesBinding? = null
    private val binding: FragmentImagesBinding
        get() = _binding ?: throw RuntimeException("FragmentImagesBinding == null")

    private val viewModel: MainViewModel by activityViewModels()


    private val imagesAdapter: ImagesAdapter by lazy {
        ImagesAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            params = it.getString("params", "")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentImagesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imagesRecyclerView.adapter = imagesAdapter
        viewModel.setParams(params)
        viewModel.imagesList.observe(viewLifecycleOwner) { newList ->
            imagesAdapter.submitList(newList)
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getImages(params).let {
                imagesAdapter.submitList(it)
            }
        }
        imagesAdapter.setOnImageClickListener(object : ImagesAdapter.OnImageClickListener {
            override fun onImageClick(image: UnsplashPhoto) {
                val action =
                    ImagesFragmentDirections.actionImagesFragmentToSelectedImageFragment(image)
                findNavController().navigate(action)
            }

        })
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}