package com.zebra.isv.googlebookskotlinmvvm.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zebra.isv.googlebookskotlinmvvm.data.reposotory.GoogleBooksRepository

class SearchViewModelFactory (
    private val googleBooksRepository: GoogleBooksRepository
) : ViewModelProvider.NewInstanceFactory(){
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SearchViewModel(googleBooksRepository) as T
    }
}