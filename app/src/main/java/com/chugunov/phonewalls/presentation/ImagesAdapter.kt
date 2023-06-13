package com.chugunov.phonewalls.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.chugunov.phonewalls.databinding.ImagesCardBinding
import com.chugunov.phonewalls.domain.model.UnsplashPhoto

class ImagesAdapter :
    ListAdapter<UnsplashPhoto, ImagesAdapter.ImagesViewHolder>(ImagesDiffCallBack()) {

    class ImagesViewHolder(private val binding: ImagesCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(unsplashPhoto: UnsplashPhoto) {
            binding.ivImage.load(unsplashPhoto.urls.full)
        }

    }

    class ImagesDiffCallBack: DiffUtil.ItemCallback<UnsplashPhoto>() {
        override fun areItemsTheSame(oldItem: UnsplashPhoto, newItem: UnsplashPhoto): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UnsplashPhoto, newItem: UnsplashPhoto): Boolean {
            return oldItem.id == newItem.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewHolder {
        val binding = ImagesCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ImagesViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        val image = getItem(position)
        holder.bind(image)
    }

}