package com.chugunov.phonewalls.presentation

import android.app.AlertDialog
import android.app.WallpaperManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable

class WallpaperDialog(private val context: Context) {

    fun showWallpaperDialog(image: Drawable) {
        val options = arrayOf("Wallpaper", "Lock Screen", "Wallpaper and lock screen")

        val builder = AlertDialog.Builder(context)
        builder.setTitle("Apply to")
            .setItems(options) { dialog, which ->
                when (which) {
                    0 -> setWallpaper(image, WallpaperManager.FLAG_SYSTEM)
                    1 -> setWallpaper(image, WallpaperManager.FLAG_LOCK)
                    2 -> {
                        setWallpaper(image, WallpaperManager.FLAG_SYSTEM)
                        setWallpaper(image, WallpaperManager.FLAG_LOCK)
                    }
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
        wallpaperManager.setBitmap(bitmap, null, true, flag)
    }

    private fun drawableToBitmap(drawable: Drawable): Bitmap {
        if (drawable is BitmapDrawable) {
            return drawable.bitmap
        } else {
            throw RuntimeException("drawable != BitmapDrawable")
        }
    }
}