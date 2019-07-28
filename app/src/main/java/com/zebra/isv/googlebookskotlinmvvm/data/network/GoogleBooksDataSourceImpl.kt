package com.zebra.isv.googlebookskotlinmvvm.data.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zebra.isv.googlebookskotlinmvvm.data.GoogleBooksApiService
import com.zebra.isv.googlebookskotlinmvvm.data.Response.GoogleBooksResponse

class GoogleBooksDataSourceImpl(
    private val googleBooksApiService: GoogleBooksApiService) : GoogleBooksDataSource {

    override suspend fun fetchBooks(query:String) : GoogleBooksResponse =
         googleBooksApiService.getBooks(query).await()

}