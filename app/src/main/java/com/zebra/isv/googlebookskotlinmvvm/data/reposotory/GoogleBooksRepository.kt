package com.zebra.isv.googlebookskotlinmvvm.data.reposotory

import androidx.lifecycle.LiveData
import com.zebra.isv.googlebookskotlinmvvm.data.Response.GoogleBooksResponse

interface GoogleBooksRepository {
     suspend fun fetBooks(query:String):LiveData<GoogleBooksResponse>
}