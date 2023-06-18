package com.chugunov.phonewalls.data.downloader

interface Downloader {

    fun downloadFile(url: String): Long
}