package com.zebra.isv.googlebookskotlinmvvm.MainShowCase

import androidx.lifecycle.ViewModel;
import com.zebra.isv.googlebookskotlinmvvm.data.internal.lazyDeferred
import com.zebra.isv.googlebookskotlinmvvm.data.reposotory.GoogleBooksRepository

class MainShowCaseViewModel(
    private val googleBooksRepository: GoogleBooksRepository
) : ViewModel() {

    private val query="Coffee"


   val books by lazyDeferred{
        googleBooksRepository.fetchBooks(query)
    }



}
