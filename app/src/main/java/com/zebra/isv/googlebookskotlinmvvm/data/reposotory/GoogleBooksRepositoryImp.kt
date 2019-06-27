package com.zebra.isv.googlebookskotlinmvvm.data.reposotory

import androidx.lifecycle.LiveData
import com.zebra.isv.googlebookskotlinmvvm.data.Response.GoogleBooksResponse
import com.zebra.isv.googlebookskotlinmvvm.data.network.GoogleBooksDataSource
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlin.concurrent.thread

class GoogleBooksRepositoryImp(private val googleBooksDataSource: GoogleBooksDataSource):GoogleBooksRepository {



    override suspend fun fetchBooks(query: String):LiveData<GoogleBooksResponse> {
        googleBooksDataSource.fetchBooks(query)


        //Stavljeno samo da ne vraca error ne
        //nevirujen da je pravilni nacin dohvacanja podataka.
        return googleBooksDataSource.downloadedGoogleBooks

    }


}