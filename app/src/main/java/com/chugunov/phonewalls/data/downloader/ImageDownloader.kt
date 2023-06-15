package com.chugunov.phonewalls.data.downloader

import android.app.DownloadManager
import android.content.Context
import android.os.Environment
import androidx.core.net.toUri

class ImageDownloader(context: Context) : Downloader {

    private val downloadManager = context.getSystemService(DownloadManager::class.java)

    override fun downloadFile(url: String): Long {
        val request = DownloadManager.Request(url.toUri())
            .setMimeType("image.jpeg")
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setTitle("image.jpeg")
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "image.jpg")
        return downloadManager.enqueue(request)
    }
}