package com.playground.modmelonskins.data.manager.downloader

interface Downloader {
    fun downloadFile(url: String): Long
}