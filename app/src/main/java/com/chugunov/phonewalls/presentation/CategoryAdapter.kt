package com.chugunov.phonewalls.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.chugunov.phonewalls.domain.model.Category
import com.chugunov.phonewalls.databinding.CategoryItemCardBinding

class CategoryAdapter(private val categoryList: MutableLiveData<ArrayList<Category>>) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {


    class CategoryViewHolder(private val binding: CategoryItemCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(category: Category) {
            binding.categoryImage.setImageResource(category.imageId)
            binding.categoryName.text = category.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding =
            CategoryItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        val list = categoryList.value
        return list?.size ?: 0
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categoryList.value?.get(position)
        category?.let { holder.bind(it) }
    }
}