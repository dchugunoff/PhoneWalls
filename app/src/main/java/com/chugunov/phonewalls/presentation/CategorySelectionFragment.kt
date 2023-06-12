package com.chugunov.phonewalls.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.chugunov.phonewalls.databinding.FragmentCategorySelectionBinding

class CategorySelectionFragment : Fragment() {

    private var _binding: FragmentCategorySelectionBinding? = null
    private val binding: FragmentCategorySelectionBinding
        get() = _binding ?: throw RuntimeException("FragmentCategorySelectionBinding == null")

    private val categoryAdapter: CategoryAdapter by lazy { CategoryAdapter(viewModel.categoryList) }

    private val viewModel by lazy {
        ViewModelProvider(this)[CategoryViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategorySelectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = categoryAdapter
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}