package com.zebra.isv.googlebookskotlinmvvm.data.network

import androidx.lifecycle.LiveData
import com.zebra.isv.googlebookskotlinmvvm.data.Response.GoogleBooksResponse

interface GoogleBooksDataSource {
    val downloadedGoogleBooks :LiveData<GoogleBooksResponse>
    suspend fun fetchBooks(query :String)
}