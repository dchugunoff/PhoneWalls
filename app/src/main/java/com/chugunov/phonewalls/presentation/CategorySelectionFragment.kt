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


    private val viewModel: CategoryViewModel by lazy {
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
        val adapter = CategoryAdapter()
        binding.recyclerView.adapter = adapter
        viewModel.categoryList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}