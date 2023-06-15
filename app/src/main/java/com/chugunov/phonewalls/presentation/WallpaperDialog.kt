package com.chugunov.phonewalls.presentation

import android.app.AlertDialog
import android.app.WallpaperManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import com.chugunov.phonewalls.R
import com.chugunov.phonewalls.databinding.DialogWallpaperBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WallpaperDialog(private val context: Context) {

    private val wallpaperScope: CoroutineScope = CoroutineScope(Dispatchers.IO)

    fun showWallpaperDialog(image: Drawable) {
        val binding = DialogWallpaperBinding.inflate(LayoutInflater.from(context))
        binding.wallpaper.isChecked = true

        val builder = AlertDialog.Builder(context)
        builder.setTitle("Apply to")
            .setView(binding.root)
            .setPositiveButton("Apply") { dialog, _ ->
                val selectedOption = when (binding.radioGroup.checkedRadioButtonId) {
                    R.id.wallpaper -> WallpaperManager.FLAG_SYSTEM
                    R.id.lock_screen -> WallpaperManager.FLAG_LOCK
                    R.id.wallpaper_and_lockscreen -> 3
                    else -> throw IllegalStateException("No selected radiobutton")
                }
                wallpaperScope.launch {
                    setWallpaper(image, selectedOption)
                }
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
        builder.create().show()

    }

    private fun setWallpaper(image: Drawable, flag: Int) {
        val wallpaperManager = WallpaperManager.getInstance(context)
        val bitmap = drawableToBitmap(image)
        if (flag == WallpaperManager.FLAG_LOCK || flag == WallpaperManager.FLAG_SYSTEM) {
            wallpaperManager.setBitmap(bitmap, null, true, flag)
        } else {
            wallpaperManager.setBitmap(bitmap, null, true, WallpaperManager.FLAG_SYSTEM)
            wallpaperManager.setBitmap(bitmap, null, true, WallpaperManager.FLAG_LOCK)
        }
    }

    private fun drawableToBitmap(drawable: Drawable): Bitmap {
        if (drawable is BitmapDrawable) {
            return drawable.bitmap
        } else {
            throw RuntimeException("drawable != BitmapDrawable")
        }
    }
}