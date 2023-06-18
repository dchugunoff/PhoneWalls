package com.chugunov.phonewalls.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.chugunov.phonewalls.databinding.FragmentImagesBinding
import kotlinx.coroutines.launch

class ImagesFragment : Fragment() {


    private lateinit var params: String

    private var _binding: FragmentImagesBinding? = null
    private val binding: FragmentImagesBinding
        get() = _binding ?: throw RuntimeException("FragmentImagesBinding == null")

    private val viewModel: ImagesViewModel by lazy {
        ViewModelProvider(this)[ImagesViewModel::class.java]
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
        val adapter = ImagesAdapter()
        binding.imagesRecyclerView.adapter = adapter
        viewModel.viewModelScope.launch {
            viewModel.getImages(params, requireContext())
        }
        viewModel.imagesList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}