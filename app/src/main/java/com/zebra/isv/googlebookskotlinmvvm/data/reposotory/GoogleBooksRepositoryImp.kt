package com.zebra.isv.googlebookskotlinmvvm.data.reposotory

import androidx.lifecycle.LiveData
import com.zebra.isv.googlebookskotlinmvvm.data.Response.GoogleBooksResponse
import com.zebra.isv.googlebookskotlinmvvm.data.network.GoogleBooksDataSource
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlin.concurrent.thread

class GoogleBooksRepositoryImp(private val googleBooksDataSource: GoogleBooksDataSource):GoogleBooksRepository {



    override suspend fun fetBooks(query: String):LiveData<GoogleBooksResponse> {
        googleBooksDataSource.fetchBooks(query)
        //Stavljeno samo da ne vraca error
        return googleBooksDataSource.downloadedGoogleBooks

    }


}