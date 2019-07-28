package com.zebra.isv.googlebookskotlinmvvm.data.reposotory

import androidx.lifecycle.LiveData
import com.zebra.isv.googlebookskotlinmvvm.data.Response.GoogleBooksResponse
import com.zebra.isv.googlebookskotlinmvvm.data.network.GoogleBooksDataSource

class GoogleBooksRepositoryImp(private val googleBooksDataSource: GoogleBooksDataSource) : GoogleBooksRepository {

    override suspend fun fetchBooks(query: String): GoogleBooksResponse =
        googleBooksDataSource.fetchBooks(query)
}