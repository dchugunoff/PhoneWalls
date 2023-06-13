package com.chugunov.phonewalls.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.chugunov.phonewalls.R
import com.chugunov.phonewalls.databinding.FragmentCategorySelectionBinding
import com.chugunov.phonewalls.domain.model.Category
import com.chugunov.phonewalls.presentation.ImagesFragment.Companion.newInstance

class CategorySelectionFragment : Fragment() {

    private var _binding: FragmentCategorySelectionBinding? = null
    private val binding: FragmentCategorySelectionBinding
        get() = _binding ?: throw RuntimeException("FragmentCategorySelectionBinding == null")

    private val categoryAdapter: CategoryAdapter by lazy { CategoryAdapter(viewModel.categoryList) }

    private val viewModel: MainViewModel by activityViewModels()

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
        categoryAdapter.setOnItemClickListener(object : CategoryAdapter.OnCategoryClickListener {
            override fun onItemClick(category: Category) {
                val fragment = ImagesFragment.newInstance(category)
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


}