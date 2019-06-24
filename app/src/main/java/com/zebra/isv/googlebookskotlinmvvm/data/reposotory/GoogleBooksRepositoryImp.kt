package com.zebra.isv.googlebookskotlinmvvm.data.reposotory

import com.zebra.isv.googlebookskotlinmvvm.data.network.GoogleBooksDataSource

class GoogleBooksRepositoryImp(private val googleBooksDataSource: GoogleBooksDataSource):GoogleBooksRepository {
    override suspend fun fetBooks(query: String) {
        googleBooksDataSource.fetchBooks(query)

    }


}