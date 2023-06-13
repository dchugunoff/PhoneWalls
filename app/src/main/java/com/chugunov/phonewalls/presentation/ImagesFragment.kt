package com.chugunov.phonewalls.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.chugunov.phonewalls.databinding.FragmentImagesBinding

class ImagesFragment : Fragment() {

    private var _binding: FragmentImagesBinding? = null
    private val binding: FragmentImagesBinding
        get() = _binding ?: throw RuntimeException("FragmentImagesBinding == null")

    private val viewModel: MainViewModel by activityViewModels()

    private val imagesAdapter: ImagesAdapter by lazy {
        ImagesAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentImagesBinding.inflate(inflater, container, false)
        Log.d("Response", "Fragment OnCreateView()")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imagesRecyclerView.adapter = imagesAdapter
        viewModel.imagesList.observe(viewLifecycleOwner) { newList ->
            imagesAdapter.submitList(newList)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}