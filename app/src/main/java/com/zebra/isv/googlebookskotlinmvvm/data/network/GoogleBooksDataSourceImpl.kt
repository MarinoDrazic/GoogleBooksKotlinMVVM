package com.zebra.isv.googlebookskotlinmvvm.data.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zebra.isv.googlebookskotlinmvvm.data.GoogleBooksApiService
import com.zebra.isv.googlebookskotlinmvvm.data.Response.GoogleBooksResponse

class GoogleBooksDataSourceImpl(
    private val googleBooksApiService: GoogleBooksApiService) : GoogleBooksDataSource {


    private val  _downloadedGoogleBooks = MutableLiveData<GoogleBooksResponse>()
    override val downloadedGoogleBooks: LiveData<GoogleBooksResponse>
        get() = _downloadedGoogleBooks

    override suspend fun fetchBooks(query:String) {
        //Could crash if no internet maybe?
        val fetchedBooks= googleBooksApiService
            .getBooks(query).await()
        _downloadedGoogleBooks.postValue(fetchedBooks)

        //sta ako stavin da ova metoda vraca livedata?

    }
}