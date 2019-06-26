package com.zebra.isv.googlebookskotlinmvvm.MainShowCase

import androidx.lifecycle.ViewModel;
import com.zebra.isv.googlebookskotlinmvvm.data.internal.lazyDeferred
import com.zebra.isv.googlebookskotlinmvvm.data.reposotory.GoogleBooksRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainShowCaseViewModel(
    private val googleBooksRepository: GoogleBooksRepository
) : ViewModel() {

    private val query="Coffee"

    //Dont know how to use lazy coroutines. Hope this works.
    //Dont use GlobalScope as much
   val Books by lazyDeferred{
        googleBooksRepository.fetBooks(query)
    }



}
