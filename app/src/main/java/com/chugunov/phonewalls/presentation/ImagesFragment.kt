package com.chugunov.phonewalls.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.chugunov.phonewalls.R
import com.chugunov.phonewalls.databinding.FragmentImagesBinding
import com.chugunov.phonewalls.domain.model.Category
import com.chugunov.phonewalls.domain.model.UnsplashPhoto
import kotlinx.coroutines.launch

class ImagesFragment : Fragment() {

    private var _binding: FragmentImagesBinding? = null
    private val binding: FragmentImagesBinding
        get() = _binding ?: throw RuntimeException("FragmentImagesBinding == null")

    private val viewModel: MainViewModel by activityViewModels()

    private lateinit var params: String

    private val imagesAdapter: ImagesAdapter by lazy {
        ImagesAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            params = it.getString(PARAMS_KEY) ?: ""
        }
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
                val fragment = SelectedImageFragment.newInstance(image)
                val transaction = parentFragmentManager.beginTransaction()
                transaction.replace(R.id.nav_host_fragment_container, fragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }

        })
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val PARAMS_KEY = "params"
        fun newInstance(category: Category): ImagesFragment {
            val fragment = ImagesFragment()
            val args = Bundle()
            args.putString(PARAMS_KEY, category.params)
            fragment.arguments = args
            return fragment
        }
    }
}