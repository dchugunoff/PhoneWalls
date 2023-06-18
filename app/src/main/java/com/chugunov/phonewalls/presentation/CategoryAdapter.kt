package com.chugunov.phonewalls.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.chugunov.phonewalls.databinding.CategoryItemCardBinding
import com.chugunov.phonewalls.domain.model.Category

class CategoryAdapter : ListAdapter<Category, CategoryAdapter.CategoryViewHolder>(DiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = CategoryItemCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = getItem(position)
        holder.bind(category)
    }


    inner class CategoryViewHolder(private val binding: CategoryItemCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(category: Category) {
            binding.categoryName.text = category.name
            binding.categoryImage.load(category.imageId)
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val action =
                        CategorySelectionFragmentDirections.actionCategorySelectionFragmentToImagesFragment(
                            category.params
                        )
                    it.findNavController().navigate(action)
                }
            }
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Category>() {
            override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
                return oldItem.imageId == newItem.imageId
            }

            override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
                return oldItem == newItem
            }

        }
    }
}
