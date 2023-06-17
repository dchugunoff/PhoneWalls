package com.chugunov.phonewalls.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.chugunov.phonewalls.databinding.ImagesCardBinding
import com.chugunov.phonewalls.domain.model.UnsplashPhoto

class ImagesAdapter : ListAdapter<UnsplashPhoto, ImagesAdapter.ImageViewHolder>(DiffCallBack) {


    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val unsplashPhoto = getItem(position)
        holder.bind(unsplashPhoto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ImagesCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ImageViewHolder(binding)
    }


    inner class ImageViewHolder(private val binding: ImagesCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val unsplashPhoto = getItem(position)
                    val action =
                        ImagesFragmentDirections.actionImagesFragmentToSelectedImageFragment(
                            unsplashPhoto
                        )
                    it.findNavController().navigate(action)
                }
            }
        }

        fun bind(unsplashPhoto: UnsplashPhoto) {
            binding.ivImage.load(unsplashPhoto.urls.full)
        }

    }

    companion object {
        private val DiffCallBack = object : DiffUtil.ItemCallback<UnsplashPhoto>() {
            override fun areItemsTheSame(oldItem: UnsplashPhoto, newItem: UnsplashPhoto): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: UnsplashPhoto,
                newItem: UnsplashPhoto
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
